class Solution {
	static int maxScore;
	static int[] hit, maxHit;
    public int[] solution(int n, int[] info) {
    	maxScore = 0;
    	hit = new int[11];
    	maxHit = new int[11];
    	dfs(n, info, 10, 0, 0);
    	
        if(maxScore == 0) return new int[] {-1};
        return maxHit;
    }
    
    public void dfs(int n, int[] info, int depth, int score, int enemy) {
    	
    	if(depth == -1) {
			// for(int i = 0; i <= 10; i++) {
			// 	System.out.print(hit[i] + " ");
			// }
			// System.out.println();
    		if(maxScore < (score - enemy)) {
    			
    			maxScore = (score - enemy);
    			maxHit = hit.clone();
    		}
    		return;
    	}
    	
    	for(int i = n; i >= 0; i--) {
    		hit[depth] = i;
            if(info[depth] == 0 && i == 0) dfs(n - i, info, depth - 1, score, enemy);
        	if(i > info[depth])
        		dfs(n - i, info, depth - 1, score + (10 - depth), enemy);
        	else
        		dfs(n - i, info, depth - 1, score, enemy + (10 - depth));
    	}
    }
    
}