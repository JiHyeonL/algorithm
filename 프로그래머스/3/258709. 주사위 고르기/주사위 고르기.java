import java.util.*;

class Solution {
    
    public Map<String, Dices> combination = new HashMap<>();
    public int n;
    public boolean[][] visited;
    public int[][] dice;
        
    public int[] solution(int[][] dice) {
        n = dice.length;
        this.dice = dice;
        makeDiceComb(0, 0, new ArrayList<>());
        for (Dices dices : combination.values()) {
            // dices의 모든 점수의 합 경우의 수 찾기
            visited = new boolean[n/2][6];
            makeScoreComb(0, dices);
        }
        // 승 비교
        for (String dicesName : combination.keySet()) {
            List<Integer> aScores = combination.get(dicesName).getScores();
            List<Integer> aDices = combination.get(dicesName).getDices();
            List<Integer> bDices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!aDices.contains(i)) {
                    bDices.add(i);
                }
            }
            Collections.sort(bDices);
            StringBuilder bName = new StringBuilder();
            for (int bDice : bDices) {
                bName.append(bDice);
            }
            List<Integer> bScores = combination.get(bName.toString()).getScores();
            int winCount = calculateWinCount(aScores, bScores);
            combination.get(dicesName).setWinCount(winCount);
        }
        int[] answer = new int[n/2];
        int maxWin = -1;
        Dices maxDices = null;
        for (Dices dices : combination.values()) {
            if (maxWin < dices.getWinCount()) {
                maxWin = dices.getWinCount();
                maxDices = dices;
            }
        }
        List<Integer> ans = maxDices.getDices();
        Collections.sort(ans);
        for (int i = 0; i < n/2; i++) {
            answer[i] = ans.get(i) + 1;
        }
        return answer;
    }
    
    private void makeDiceComb(int count, int now, List<Integer> dices) {
        if (count == n/2) {
            Collections.sort(dices);
            StringBuilder key = new StringBuilder();
            for (int dice : dices) {
                key.append(dice);
            }
            combination.put(key.toString(), new Dices(dices));
            return;
        }
        
        for (int i = now; i < n; i++) {
            dices.add(i);
            makeDiceComb(count + 1, i + 1, dices);
            dices.remove(dices.size()-1);
        }
    }
    
    private void makeScoreComb(int count, Dices dices) {
        if (count == n/2) {
            int sum = 0;
            List<Integer> pickDices = dices.getDices();
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j]) {
                        sum += dice[pickDices.get(i)][j];
                    }
                }
            }
            dices.addScore(sum);
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            if (!visited[count][i]) {
                visited[count][i] = true;
                makeScoreComb(count + 1, dices);
                visited[count][i] = false;
            }
        }
    }
    
    private int calculateWinCount(List<Integer> aScores, List<Integer> bScores) {
        Collections.sort(bScores);
        int count = 0;
        for (int aScore : aScores) {
            count += binarySearch(aScore, bScores);
        }
        return count;
    }
    
    private int binarySearch(int target, List<Integer> bScores) {
        int start = 0;
        int end = bScores.size() - 1;
        int result = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (bScores.get(mid) < target) {
                start = mid + 1;
                result = mid;
            } else {
                end = mid - 1;
            }
        }
        return result + 1;
    }
    
    static class Dices {
        List<Integer> dices;
        List<Integer> scoreCombination = new ArrayList<>();
        int winCount = 0;
        
        public Dices(List<Integer> dices) {
            this.dices = new ArrayList<>(dices);
        }
        
        public void addScore(int score) {
            scoreCombination.add(score);
        }
        
        public void setWinCount(int winCount) {
            this.winCount = winCount;
        }
        
        public List<Integer> getDices() {
            return dices;
        }
        
        public List<Integer> getScores() {
            return scoreCombination;
        }
        
        public int getWinCount() {
            return winCount;
        }
        
        public void toPrint() {
            dices.stream().forEach(a -> System.out.print(a + ", "));
        }
        
        public void toPrintScore() {
            scoreCombination.stream().forEach(a -> System.out.print(a + ", "));
        }
    }
}

// A: n/2개 -> 먼저 가져감
// B: n/2개
// 주사위 n/2개를 굴린 뒤 수를 모두 합함.

// 최대 10개 중 5개 선택 -> 줄일 수 없음
// A: 주사위 5개 굴림, 주사위 하나 당 6개. 6^5
// B: 주사위 5개 굴림, 6^5
// 10C5 * 6^5
// 1,2: 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10
// 3,4: 2, 2, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9
