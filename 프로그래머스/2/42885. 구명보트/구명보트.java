import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int endIndex = 0;
        for (int i = people.length - 1; i >= endIndex; i--) {
            if (people[i] + people[endIndex] <= limit) {
               endIndex++;
            }
            answer++;
        }
        
        return answer;
    }
}

// 최대 2명씩 탈 수 있음, 무게 제한
// 모든 사람을 구출하기 위해 필요한 구명보트 개수 최소값
// 그럼 최대한 무게 제한에 맞춰서 두명 묶기.

// 50 50 70 80
