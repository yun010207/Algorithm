import java.util.*;

class Solution {
    class Street {
        int r, c, dir, cost;
        Street(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }

    // 0: 우, 1: 하, 2: 좌, 3: 상
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] visited = new int[4][n][n];

        // visited 배열을 큰 값으로 초기화
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Street> q = new LinkedList<>();

        // (0, 0)에서 출발하여 오른쪽(0) 또는 아래쪽(1)으로 시작
        if (board[0][1] == 0) {
            q.add(new Street(0, 1, 0, 100)); // (row=0, col=1), 방향=우(0)
            visited[0][0][1] = 100;
        }
        if (board[1][0] == 0) {
            q.add(new Street(1, 0, 1, 100)); // (row=1, col=0), 방향=하(1)
            visited[1][1][0] = 100;
        }

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Street cur = q.poll();

            if (cur.r == n - 1 && cur.c == n - 1) {
                answer = Math.min(answer, cur.cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 1) {
                    continue;
                }

                // 기존 방향과 같으면 100원, 꺾이면 600원
                int nextCost = cur.cost + (cur.dir == i ? 100 : 600);

                // 더 작은 비용으로 방문할 수 있는 경우만 갱신
                if (nextCost < visited[i][nr][nc]) {
                    visited[i][nr][nc] = nextCost;
                    q.add(new Street(nr, nc, i, nextCost));
                }
            }
        }

        return answer;
    }
}