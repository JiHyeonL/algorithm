import java.util.*;

class Solution {
    
    public int solution(String name) {
        int answer = 0;
        int goCount = name.length() - 1;
        for (int i = 0; i < name.length(); i++) {
            answer += convertAlp(name.charAt(i));
            int zeroIndex = i+1;
            while (zeroIndex < name.length() && name.charAt(zeroIndex) == 'A') {
                zeroIndex++;
            }
            // ->로 갔다가 i 타이밍에서 돌아온 뒤 <-
            goCount = Math.min(goCount, i * 2 + name.length() - zeroIndex);
            // <- 로 갔다가 i 타이밍에서 돌아온 뒤 ->
            goCount = Math.min(goCount, (name.length() - zeroIndex) * 2 + i);
        }
        answer += goCount;
        return answer;
    }
    
    private int convertAlp(char alp) {
        return Math.min(Math.abs('A' - alp), Math.abs('Z' - alp) + 1);
    }
}

// A 시작
// 최소로 만드는 수
// AAA
// JAA
// JAZ

// 1. 어디로 갈지
// 2. 알파벳 선택

// AAABBBBAAAC -> 이
// AB

// 경우의 수 세기.
// 좌우 좌우 이런 경우는 없음.
// 1. -> or <- : BBBBB
// 2. -> <- : BBAAAAB
// 3. <- -> : ABBBBAAAC
// - 처음이 A이고 뒤가 