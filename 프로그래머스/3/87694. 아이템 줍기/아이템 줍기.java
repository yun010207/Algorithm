import java.util.*;

class Solution {
    class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] r : rectangle)
    for (int j = 0; j < 4; j++) r[j] *= 2;

characterX *= 2; characterY *= 2;
itemX *= 2;      itemY *= 2;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[102][102];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(characterX, characterY, 0));
        while(!q.isEmpty()) {
            Point nowP = q.poll();
            if(nowP.x == itemX && nowP.y == itemY) return nowP.dist / 2;
            
            for(int d = 0; d < 4; d++) {
                int nx = nowP.x + dx[d];
                int ny = nowP.y + dy[d];
                
                if(nx < 1 || nx > 100 || ny < 1 || ny > 100) continue;
                if(visited[nx][ny]) continue;
                if(!isOnLine(rectangle, nx, ny)) continue;
                
                visited[nx][ny] = true;
                q.add(new Point(nx, ny, nowP.dist + 1));
            }
        }
        
        
        int answer = 0;
        return answer;
    }
    
    public static boolean isOnLine(int[][] rectangle, int x, int y) {
        for(int i = 0; i < rectangle.length; i++) {
            if(x > rectangle[i][0] && x < rectangle[i][2] &&
                   y > rectangle[i][1] && y < rectangle[i][3]) return false;
        }
        
        for(int i = 0; i < rectangle.length; i++) {
            if(x == rectangle[i][0] &&
               (y >= rectangle[i][1] && y <= rectangle[i][3])) return true;
            else if(x == rectangle[i][2] && 
                (y >= rectangle[i][1] && y <= rectangle[i][3])) return true;
            else if(y == rectangle[i][1] &&
                   (x >= rectangle[i][0] && x <= rectangle[i][2])) return true;
            else if(y == rectangle[i][3] &&
                   (x >= rectangle[i][0] && x <= rectangle[i][2])) return true;
        }
        
        return false;
    }
}