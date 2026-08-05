import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
    	
    	PriorityQueue<Integer> min_q = new PriorityQueue<>();
    	PriorityQueue<Integer> max_q = new PriorityQueue<>(Collections.reverseOrder());
    	for(String str:operations) {
    		String[] tmp = str.split(" ");
    		char cmd = tmp[0].charAt(0);
    		if(cmd == 'I') {
        		min_q.add(Integer.parseInt(tmp[1]));
        		max_q.add(Integer.parseInt(tmp[1]));
    		}
    		else {
                if(min_q.isEmpty()) continue;
                
    			if(Integer.parseInt(tmp[1]) == 1) {
    				min_q.remove(max_q.remove());
    			}
    			else {
    				max_q.remove(min_q.remove());
    			}
    		}
    	}
    	
    	
        int[] answer = new int[2];
    	if(!min_q.isEmpty()) {
    		answer[0] = max_q.poll();
    		answer[1] = min_q.poll();
    	}
        return answer;
    }
}