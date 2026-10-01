import java.util.function.Function;


class Solution {
    static int dp[][];
    static int k[][];
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        
        dp = new int[depth.length + 2][depth.length + 2];
        k = new int[depth.length + 2][depth.length + 2];
        
        int left = 1;
        int right = depth.length;
        
        fillDP(depth, right);
        
        while(left <= right) {
            int nk = k[left][right];
            int dir = excavate.apply(nk);
            
            if(dir == 0) {
                return nk;
            }
            else if(dir > 0) {
                left = nk + 1;
            }
            else {
                right = nk - 1;
            }
        }
            
        return 0;
    }
    
    static public void fillDP(int[] depth, int w) {
        for(int i = 1; i <= w; i++) {
            dp[i][i] = depth[i - 1];
            k[i][i] = i;
        }
        
        for(int len = 2; len <= w; len++) {
            for(int left = 1; left + len - 1 <= w; left++) {
                int right = left + len - 1;
                dp[left][right] = Integer.MAX_VALUE;
                
                for(int mid = left; mid <= right; mid++) {
                    int leftCost = (mid > left) ? dp[left][mid - 1] : 0;
                    int rightCost = (mid < right) ? dp[mid + 1][right] : 0;
                    
                    int maxCost = depth[mid - 1] + Math.max(leftCost, rightCost);
                    
                    if(maxCost < dp[left][right]) {
                        dp[left][right] = maxCost;
                        k[left][right] = mid;
                    }
                }
                
                
            }
        }
        
        
    }
}