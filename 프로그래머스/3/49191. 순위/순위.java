import java.util.*;

class Solution {
    
    private int n;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        this.n = n;
        int[][] dp = new int[n+1][n+1];
        for (int[] result : results) {
            dp[result[0]][result[1]] = 1;
            dp[result[1]][result[0]] = -1;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                    } else if (dp[i][k] == -1 && dp[k][j] == -1) {
                        dp[i][j] = -1;
                        dp[j][i] = 1;
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                if (dp[i][j] != 0) {
                    count++;
                }
            }
            if (count == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
}

// 4는 3, 2이김
// 3은 2 이김
// 1은 2이김
// 2는 5 이김

// 5
// 5 < 2 -> 5는 2에게 짐
// 1 > 2 > 5 -> 5는 1에게 짐

// 4 > 3 > 2 > 5 -> 5는 4, 3에게 짐
//     1 > 2
//     4 > 2

// 1
// 1 > 2 -> 1은 2한테 짐
// 2 > 3

