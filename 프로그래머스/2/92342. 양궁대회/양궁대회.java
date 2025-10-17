import java.util.*;

class Solution {
    
    private int n;
    private int[] info;
    private int[] answer = new int[11];
    private int compareMax = -1;
    
    // n = 화살 개수
    // info = 10점부터 0점까지 맞춘 화살 개수
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        dfs(0, n, new int[11]);
        if (compareMax == -1) {
            return new int[]{-1};
        }
        return answer;
    }
    
    private void dfs(int score, int count, int[] lionInfo) {
        if (score == 11) {
            int compare = calculate(lionInfo);
            if (compare == 0) {
                return;
            }
            if (compare > compareMax) { // 결과가 같거나(0) 어피치 점수가 더 크면 반영x
                compareMax = compare;
                for (int i = 0; i < 11; i++) {
                    answer[i] = lionInfo[i];
                }
            }
            else if (compare > 0 && compare == compareMax) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] < lionInfo[i]) {
                        for (int j = 0; j < 11; j++) {
                            answer[j] = lionInfo[j];
                        }
                        break;
                    } else if (answer[i] > lionInfo[i]) {
                        break;
                    }
                }
             }
            return;
        }
        
        if (score == 10 && count > 0) {
            lionInfo[10] = count;
            dfs(score + 1, 0, lionInfo);
            lionInfo[10] = 0;
        }
        if (info[score] > 0) {  // 어피치가 맞춤.
            if (count >= info[score] + 1) {
                lionInfo[score] = info[score] + 1;  // 라이언 점수
                dfs(score + 1, count - lionInfo[score], lionInfo);
                lionInfo[score] = 0;
            
                dfs(score + 1, count, lionInfo);  // 어피치 점수
            } else {
                dfs(score + 1, count, lionInfo);  // 어피치 점수
            }
        } else {    // 라이언이 맞춰서 점수 받거나 안받거나
            if (count >= 1) {
                lionInfo[score] = 1;  // 라이언 점수
                dfs(score + 1, count - 1, lionInfo);
                lionInfo[score] = 0;
                
                dfs(score + 1, count, lionInfo);  // 아무도 점수 x
            } else {
                dfs(score + 1, count, lionInfo);
            }
        }
    }
    
    // 라이언 점수 - 어피치 점수
    private int calculate(int[] lionInfo) {
        int apeachScore = 0;
        int lionScore = 0;
        for (int i = 0; i < 11; i++) {
            if (lionInfo[i] > info[i]) {
                lionScore += 10-i;
            } else if (info[i] != 0 && lionInfo[i] <= info[i]) {
                apeachScore += 10-i;
            }
        }
        return lionScore - apeachScore;
    }
}

// 1. 어피치가 n발 쏨 -> 그 후 라이언 n발 쏨
// 2. k점(0제외)을 어퍼치가 a발 맞추고 라이언이 b발 맟혔을 경우 더 많이 맞춘사람이 k점 가져감
// 2-1. a=b일 경우 어피치가 가져감
// 2-2. k점을 여러번 맞혀도 k점만 가져감
// 3. 모든 과녁의 점수에 대해 각 선수의 최종 점수 계산
// 4. 최종점수가 높은 사람이 우승자. 같을 경우 어피치가 우승자


// n이 작아서 dfs bfs 가능성 있음
// 점수를 따려면 어피치보다 +1 더 맞추면 됨.
// 1부터 10까지 
// 어피치 최대 점수 계산. 0
// 각 점수는 어피치가 갖거나 라이언이 갖거나 아무도 x
// 이미 어피치가 쏜 점수일 경우는 2가지 경우의 수
// 10 ->
// 9 ->
// 8 ->
// 7 -> 
// 6
// 5
// 4
// 3
// 2
// 1
