import java.io.*;
import java.util.*;

public class Solution {
    static final long MOD = 1_000_000_007L;
    static long[][] C = new long[51][51];

    public static void main(String[] args) throws IOException {
        // 조합 C(n, r) 미리 계산 (파스칼 삼각형)
        for (int i = 0; i <= 50; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            long ans = 0;
            for (int i = 0; i <= K; i++) {
                long term = C[K][i] * P(N, i) % MOD;
                if ((K - i) % 2 == 0) ans = (ans + term) % MOD;
                else ans = (ans - term + MOD) % MOD;
            }
            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }
    

    static long pow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    // 학생 j명 이하로 원형 N조각을 인접하게 겹치지 않게 칠하는 경우의 수
    static long P(int n, int j) {
        if (j == 0) return 0;
        long a = pow(j - 1, n);
        long b = (n % 2 == 0) ? (j - 1) : -(j - 1);
        return ((a + b) % MOD + MOD) % MOD;
    }
}