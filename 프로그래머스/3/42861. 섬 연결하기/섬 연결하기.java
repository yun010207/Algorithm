import java.util.*;

class Solution {

	static int parents[];
	
    public int solution(int n, int[][] costs) {
        parents = new int[n];
        Arrays.fill(parents, -1);
        
        return kruskal(n, costs);
    }
    
    public int find(int x) {
    	if(parents[x] < 0) return x;
    	
    	return parents[x] = find(parents[x]);
    }
    
    public boolean union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	
    	if(x == y) return false;
    	if(parents[x] > parents[y]) {
    		int tmp = x;
    		x = y;
    		y = tmp;
    	}
    	parents[x] += parents[y];
    	
    	parents[y] = x;
    	
    	return true;
    }
    
    
    public int kruskal(int n, int[][] costs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> {
        	return o1[2] - o2[2];
        });
        
        for(int i = 0; i < costs.length; i++) {
        	pq.add(costs[i].clone());
        }
        
        int cnt = 0;
        int dist = 0;
        while(!pq.isEmpty()) {
        	int[] now = pq.poll();
        	if(!union(now[0], now[1])) continue;
        	
        	dist += now[2];
        	cnt++;
        	
        	if(cnt == n - 1)break;
        }
        
        return dist;
    }
    
    
}