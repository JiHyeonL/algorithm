import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> friendsIndex = new HashMap<>();
        int n = friends.length;
        for (int i = 0; i < n; i++) {
            friendsIndex.put(friends[i], i);
        }
        int[][] giftInfo = new int[n][n];
        for (String gift : gifts) {
            String[] g = gift.split(" ");
            giftInfo[friendsIndex.get(g[0])][friendsIndex.get(g[1])]++;
        }
        int[] giftScore = new int[n];
        for (String friend : friends) {
            int giveSum = 0;
            int takeSum = 0;
            int index = friendsIndex.get(friend);
            for (int i = 0; i < n; i++) {
                giveSum += giftInfo[index][i];
                takeSum += giftInfo[i][index];
            }
            giftScore[index] = giveSum - takeSum;
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (giftInfo[i][j] > giftInfo[j][i]) {
                    result[i]++;
                } else if (giftInfo[i][j] == giftInfo[j][i] && giftScore[i] > giftScore[j]) {
                    result[i]++;
                }
            }
        }
        int answer = -1;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
}