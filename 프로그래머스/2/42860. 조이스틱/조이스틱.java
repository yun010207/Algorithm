class Solution {
    int n, best = Integer.MAX_VALUE;

    public int solution(String name) {
        n = name.length();
        boolean[] todo = new boolean[n];
        int upDown = 0, cnt = 0;

        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            upDown += Math.min(c - 'A', 'Z' - c + 1);
            if (c != 'A') { todo[i] = true; cnt++; }
        }
        // 시작 위치는 이동 없이 처리
        if (todo[0]) { todo[0] = false; cnt--; }

        dfs(0, cnt, 0, todo);
        return upDown + best;
    }

    void dfs(int pos, int remain, int moved, boolean[] todo) {
        if (moved >= best) return;               // 가지치기
        if (remain == 0) { best = moved; return; }

        for (int dir = -1; dir <= 1; dir += 2) { // 왼쪽, 오른쪽
            int p = pos, d = 0;
            while (!todo[p]) { p = (p + dir + n) % n; d++; }

            todo[p] = false;
            dfs(p, remain - 1, moved + d, todo);
            todo[p] = true;                      // 백트래킹
        }
    }
}