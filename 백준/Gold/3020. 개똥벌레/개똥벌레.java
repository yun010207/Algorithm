import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	int N = Integer.parseInt(st.nextToken());
    	int H = Integer.parseInt(st.nextToken());

    	// bot[len] / top[len] : 길이가 len인 석순 / 종유석 개수
    	int[] bot = new int[H + 2];
    	int[] top = new int[H + 2];

    	for(int i = 0; i < N; i++) {
    		int len = Integer.parseInt(br.readLine());
    		if(i % 2 == 0) bot[len]++;
    		else top[len]++;
    	}

    	// 뒤에서부터 누적 → bot[h] : 길이가 h 이상인 석순 개수 (top도 동일)
    	for(int h = H - 1; h >= 1; h--) {
    		bot[h] += bot[h + 1];
    		top[h] += top[h + 1];
    	}

    	int minCnt = Integer.MAX_VALUE;
    	int count = 0;
    	for(int i = 1; i <= H; i++) { // 아래에서부터 i번째 구간
    		int hit = bot[i] + top[H - i + 1];

    		if(hit < minCnt) {
    			minCnt = hit;
    			count = 1;
    		}
    		else if(hit == minCnt) count++;
    	}

    	System.out.println(minCnt + " " + count);
    }
}
