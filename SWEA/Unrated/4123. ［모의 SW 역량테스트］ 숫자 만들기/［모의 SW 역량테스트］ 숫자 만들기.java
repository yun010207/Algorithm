import java.util.*;
import java.io.*;

/*
[문제해석]
- N개의 숫자가 적힌 카드가 있고 그사이 N - 1개 칸에 사칙연산을 끼워넣어 다양한 결과 값을 구해보기로 함
- 연산자 우선 순위는 고려하지 않고 왼쪽부터 계산
- 연산 결과 최댓값과 최솟값을 찾고 그 차이를 출력
[제약조건]
1. 시간 제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초

2. 게임 판에 적힌 숫자의 개수 N 은 3 이상 12 이하의 정수이다. ( 3 ≤ N ≤ 12 )

3. 연산자 카드 개수의 총 합은 항상 N - 1 이다.

4. 게임 판에 적힌 숫자는 1 이상 9 이하의 정수이다.

5. 수식을 완성할 때 각 연산자 카드를 모두 사용해야 한다..

6. 숫자와 숫자 사이에는 연산자가 1 개만 들어가야 한다.

7. 완성된 수식을 계산할 때 연산자의 우선 순위는 고려하지 않고, 왼쪽에서 오른쪽으로 차례대로 계산한다.

8. 나눗셈을 계산 할 때 소수점 이하는 버린다.

9. 입력으로 주어지는 숫자의 순서는 변경할 수 없다.

10. 연산 중의 값은 -100,000,000 이상 100,000,000 이하임이 보장된다.
[이슈사항]
[해결프로세스]
순열로 해결하기
 */

public class Solution {
	
	static int N, minSum, maxSum;
	static int[] numbers, opers;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			minSum = Integer.MAX_VALUE;
			maxSum = Integer.MIN_VALUE;
			
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			opers = new int[N - 1];
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] tmp = new int[4];
			for(int i = 0; i < 4; i++) {
				tmp[i] = Integer.parseInt(st.nextToken());
			}
			
			int cnt = 0;
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < tmp[i]; j++) {
					opers[cnt++] = i;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			getSum(0, numbers[0], 1 << (N - 1));
			
			sb.append("#").append(test_case).append(" ");
			sb.append(maxSum - minSum);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void getSum(int depth, int sum, int flag) {
		if(depth == N - 1) {
			minSum = Math.min(minSum, sum);
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for(int i = 0; i < N - 1; i++) {
			if((flag & 1 << i) != 0) continue;
			getSum(depth + 1, calc(sum, numbers[depth + 1], opers[i]), flag | 1 << i);
		}
	}
	
	public static int calc(int a, int b, int oper) {
		if(oper == 0) return a + b;
		else if(oper == 1) return a - b;
		else if(oper == 2) return a * b;
		else return a / b;
	}
}





