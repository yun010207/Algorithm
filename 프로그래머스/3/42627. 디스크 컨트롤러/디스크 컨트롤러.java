import java.util.*;

class Solution {
    static class Job {
        int num, start, time;
        Job(int num, int start, int time) {
            this.num = num;
            this.start = start;
            this.time = time;
        }
    }
    
    public int solution(int[][] jobs) {
        
        PriorityQueue<Job> waitQ = new PriorityQueue<>((o1, o2) -> {
            return o1.start - o2.start;
        });
        for(int i = 0; i < jobs.length; i++) {
            waitQ.add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        PriorityQueue<Job> jobQ = new PriorityQueue<>((o1, o2) -> {
            if(o1.time == o2.time) {
                if(o1.start == o2.start) {
                    return o1.num - o2.num;
                }
                else return o1.start - o2.start;
            }
            else return o1.time - o2.time;
        });
        
        ArrayList<Integer> jobTimes = new ArrayList<>();
        
        Job cur = null;
        int time = 0, endTime = 0;
        while (!waitQ.isEmpty() || !jobQ.isEmpty() || cur != null) {
            if (cur != null && endTime == time) {
                jobTimes.add(time - cur.start);
                cur = null;
            }
            while (!waitQ.isEmpty() && waitQ.peek().start == time) {
                jobQ.add(waitQ.poll());
            }
            if (cur == null && !jobQ.isEmpty()) {
                cur = jobQ.poll();
                endTime = time + cur.time;
            }
            time++;
        }
        
        
        
        int answer = 0;
        for(int t:jobTimes) answer += t;
        answer /= jobs.length;
        
        
        return answer;
    }
}