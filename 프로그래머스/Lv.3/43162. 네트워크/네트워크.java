class Solution {
	
	boolean visited[] = new boolean[201];
	
	public class Computer {
		int n;
		boolean[] linked = new boolean[201];
		public Computer(int n) {
			super();
			this.n = n;
		}
	}
	
	static Computer computerArr[] = new Computer[201];
	
	public void DFS(int n, Computer computerArr[], int cur_i) {
		
		visited[cur_i] = true;
		
		for(int i = 0; i < n; i++) {
			if(computerArr[cur_i].linked[i] == true && !visited[i]) {
				DFS(n, computerArr, i);
			}
		}
	}
	
    public int solution(int n, int[][] computers) {
    	for(int i = 0; i < n; i++) {
    		computerArr[i] = new Computer(i);
    	}
    	
    	for(int i = 0; i < computers.length; i++) {
    		for(int j = 0; j < n; j++) {
    			if(computers[i][j] == 1) {
    				computerArr[j].n = j;
    				computerArr[i].linked[j] = true;
    			}
    		}
    	}
        int answer = 0;
    	
    	for(int i = 0; i < n; i++) {
    		if(!visited[i]) {
    			DFS(n, computerArr, i);
    			answer++;
    		}
    	}
    	
        return answer;
    }
}