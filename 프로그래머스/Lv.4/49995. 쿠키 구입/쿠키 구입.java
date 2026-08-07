class Solution {
    public int solution(int[] cookie) {
        int maxCookies = 0;
        int n = cookie.length;
        
        // m: 첫째 아들의 마지막 바구니 인덱스 (기준점)
        for (int m = 0; m < n - 1; m++) {
            int left = m;
            int right = m + 1;
            
            int leftSum = cookie[left];
            int rightSum = cookie[right];
            
            while (true) {
                // 두 아들의 과자 수가 같을 때 최댓값 갱신
                if (leftSum == rightSum) {
                    maxCookies = Math.max(maxCookies, leftSum);
                }
                
                // 더 적은 쪽의 바구니를 추가로 가져오며 양옆으로 확장
                if (leftSum <= rightSum && left > 0) {
                    // 왼쪽 합이 작거나 같으면 왼쪽 바구니 추가
                    left--;
                    leftSum += cookie[left];
                } else if (leftSum > rightSum && right < n - 1) {
                    // 오른쪽 합이 작으면 오른쪽 바구니 추가
                    right++;
                    rightSum += cookie[right];
                } else {
                    // 어느 쪽으로도 더 이상 확장할 수 없으면 반복 종료
                    break;
                }
            }
        }
        
        return maxCookies;
    }
}