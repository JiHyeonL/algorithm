import java.util.*;

class Solution {
    
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long start = 0;
        long end = times[times.length-1] * (long)n;
        long mid = (start + end) / 2;
        
        while (start <= end) {
            mid = (start + end) / 2;
            
            long sum = 0;
            // 각 심사관이 mid 시간동안 처리할 수 있는 인원
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }
            if (sum >= n) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
}

// n명 심사.
// 심사관마다 걸리는 시간 다름
// 한 심사대 -> 한명만 심사 받음
// 비어있지 않은 심사대를 기다렸다가 거기서 심사받을수도 있음.

// 7 10
// 1  2
// 공통점, 중복 계산 없음. dp 아님
// 그리디? 아니면 이분-투포인터.
// 둘 중 뭘 써야할까