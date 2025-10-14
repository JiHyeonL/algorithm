class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;
        int pickup = 0;
        for (int i = n-1; i >= 0; i--) {
            int count = 0;
            delivery += deliveries[i];
            pickup += pickups[i];
            
            while (delivery > 0 || pickup > 0) {
                delivery -= cap;
                pickup -= cap;
                count++;
            }
            answer += (i+1) * 2 * count;
        }
        return answer;
    }
}

// 집 n개
// 물건을 택배 상자에 담아 배달 후 빈 택배 수거
// 트럭에는 cap개 실음.
// 한번에 모든 배달과 수거를 마치고 돌아올 수 있는 최소 이동 거리 구하기


// cap개
// 1 3 3 5 2
// 5
// 1 3 3 [3] 0
// 5
// 1 3 1 0 0
// 