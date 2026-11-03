import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        final int INF = Integer.MAX_VALUE;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int[] item : info) {
            int a = item[0], b = item[1];
            int[] next = new int[m];
            Arrays.fill(next, INF);

            for (int j = 0; j < m; j++) {
                if (dp[j] == INF) continue;
                // A가 훔치는 경우
                next[j] = Math.min(next[j], dp[j] + a);
                // B가 훔치는 경우
                if (j + b < m) {
                    next[j + b] = Math.min(next[j + b], dp[j]);
                }
            }
            dp = next;
        }

        int answer = INF;
        for (int j = 0; j < m; j++) answer = Math.min(answer, dp[j]);
        return answer >= n ? -1 : answer;
    }
}