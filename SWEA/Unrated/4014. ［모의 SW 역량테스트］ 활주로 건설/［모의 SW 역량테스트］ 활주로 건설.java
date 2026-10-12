import java.io.*;
import java.util.*;
/*
[문제해석]
- N * N 크기의 절벽지대에 활주로를 건설하려고 한다.
- 높이가 다른 구간은 1*X크기의 경사로를 설치해야함
- 활주로를 건설할 수 있는 경우의 수 계산
[제약조건]
1. 시간제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초

2. N 의 크기는 6 이상 20 이하의 정수이다. ( 6 ≤ N ≤ 20 )

3. 경사로의 높이는 항상 1 이고, 길이 X 는 2 이상 4 이하의 정수이다. ( 2 ≤ X ≤ 4 )

4. 지형의 높이는 1 이상 6 이하의 정수이다.

5. 동일한 셀에 두 개 이상의 경사로를 겹쳐서 사용할 수 없다.
[이슈사항]
-경사로가 설치가능한지 어떻게 알지?
	->가로 OR 세로 방향으로 경사로 길이만큼 1차이 나면 설치 가능
-경사로를 설치한 걸 어떻게 표시하지?
	->경사로를 포함 내려가는 방향에 높이를 +1
[해결프로세스]
-각 행, 열 별로 검사
-행이면 좌,우 열이면 상,하 방향으로 슬라이딩 윈도우를 사용해 경사로 설치 여부 검사
N*N*N*X*N->20*20*20*4*20->640,000
 */
public class Solution {
	
	static final int ROW = 0, COL = 1;
	
	static int N, X;
	static int heights[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			heights = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					heights[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				if(canBuild(ROW, i)) cnt++;
				if(canBuild(COL, i)) cnt++;
			}
			
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean canBuild(int dir, int idx) {
		int tempHeights[] = cpyHeights1D(dir, idx);
		boolean built[] = new boolean[N];
		
		
		for(int i = N - 1; i >= X; i--) {//Row면 좌, col이면 상 방향으로 내려가는 경사로 설치
			if(tempHeights[i] - 1 != tempHeights[i - 1]) continue; //1칸 차이 나는 경우만 처리
			if(!isFlatten(tempHeights, built, i - 1, i - X)) continue; //X칸이 평평할 때만 처리
			upHeights(tempHeights, built, i - 1, 0, X);
			checkBuilt(built, i - 1, i - X);
		}
		
		
		for(int i = 0; i < N - X; i++) {
			if(tempHeights[i] - 1 != tempHeights[i + 1]) continue; //1칸 차이 나는 경우만 처리
			if(!isFlatten(tempHeights, built, i + 1, i + X)) continue; //X칸이 평평할 때만 처리
			upHeights(tempHeights, built, i + 1, N - 1, X);
			checkBuilt(built, i + 1, i + X);
		}
		
		
		
		for(int i = 1; i < N; i++) {
			if(tempHeights[i] != tempHeights[i - 1]) return false;
		}
		
		return true;
	}
	
	static int[] cpyHeights1D(int dir, int idx) {
		if(dir == ROW) {
			return heights[idx].clone();
		}
		else {
			int[] tmp = new int[N];
			for(int i = 0; i < N; i++) {
				tmp[i] = heights[i][idx];
			}
			return tmp;
		}
	}
	
	static boolean isFlatten(int[] tempHeights, boolean[] built, int i, int j) {
		if(i > j) {
			int temp = i;
			i = j;
			j = temp;
		}
		
		while(i < j) {
			if(tempHeights[i] != tempHeights[i + 1]) return false;
			if(built[i]) return false;
			i++;
		}
		if(built[i]) return false;
		
		return true;
	}
	
	static void upHeights(int[] tempHeights, boolean[] built, int i, int j, int X) {
		if(i > j) {
			int temp = i;
			i = j;
			j = temp;
		}
		
		while(i <= j) {
			tempHeights[i]++;
			i++;
		}
	}
	static void checkBuilt(boolean[] built, int i, int j) {
		if(i > j) {
			int temp = i;
			i = j;
			j = temp;
		}
		
		while(i <= j) {
			built[i] = true;
			i++;
		}
	}
}
