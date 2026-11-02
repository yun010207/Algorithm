import java.io.*;
import java.util.*;
/*
25,764 kb
메모리
75 ms
실행시간
 */
public class Solution {
	
	static long Y, M;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            Y = Long.parseLong(st.nextToken());
            M = Long.parseLong(st.nextToken());
            
            long sum = (Y - 2016l) * 12 + (M - 1);
            
            long year13 = sum / 13;
            long month13 = sum % 13;
            
            sb.append('#').append(test_case).append(' ');
            sb.append(2016 + year13).append(' ').append(month13 + 1);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}