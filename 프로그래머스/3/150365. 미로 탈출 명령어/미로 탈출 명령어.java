import java.io.*;
import java.util.*;

class Solution {
    static int dr[] = {1, 0, 0, -1};
    static int dc[] = {0, -1, 1, 0};
    static char dir[] = {'d', 'l', 'r', 'u'};
    
    static String minPass;
    static char pass[];
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    	
    	minPass = null;
    	
    	pass = new char[k];
    	
        int dist = getDist(x, y, r, c);
        if(dist > k || (k - dist) % 2 == 1) return "impossible";
        
        dfs(n, m, x, y, r, c, k, 0);
        
        if(minPass == null) return "impossible";
        return minPass;
    }
    
    public void dfs(int n, int m, int x, int y, int r, int c, int k, int depth) {
        if(minPass != null) return;

    	if(depth == k) {
    		if(x != r || y != c) return;
    		minPass = new String(pass);
    		
    		return;
    	}
    	
    	for(int d = 0; d < 4; d++) {
    		int nr = x + dr[d];
    		int nc = y + dc[d];
    		
    		if(nr < 1 || nr > n || nc < 1 || nc > m) continue;
    		if(getDist(nr, nc, r, c) > k - (depth + 1)) continue;
    		
    		pass[depth] = dir[d];
    		dfs(n, m, nr, nc, r, c, k, depth + 1);
    	}
    }
    
    
    public int getDist(int x, int y, int r, int c) {
    	return Math.abs(x - r) + Math.abs(y - c);
    }
}