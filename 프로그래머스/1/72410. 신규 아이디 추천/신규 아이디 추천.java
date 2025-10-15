import java.util.*;

class Solution {
    
    private List<Character> specialWords = List.of('-', '_', '.');
    private List<Character> intWords = List.of('0','1','2','3','4','5','6','7','8','9');
    
    public String solution(String new_id) {
        List<Character> answer = new ArrayList<>();
        // 1, 2단계
        for (int i = 0; i < new_id.length(); i++) {
            char word = new_id.charAt(i);
            if (isAlp(word)) {
                if (word >= 'A' && word <= 'Z') {
                    answer.add((char)(word + 32));
                } else {
                    answer.add(word);
                }
            }
            else if (isInteger(word)) {
                answer.add(word);
            }
            else if (specialWords.contains(word)) {
                answer.add(word);
            }
        }
        // 3단계
        int index = 0;
        for (int i = answer.size() - 1; i > 0; i--) {
            if (answer.get(i) == '.' && answer.get(i - 1) == '.') {
                answer.remove(i);
            }
        }
        // 4단계
        if (!answer.isEmpty() && answer.get(0) == '.') {
            answer.remove(0);
        }
        if (!answer.isEmpty() && answer.get(answer.size()-1) == '.') {
            answer.remove(answer.size()-1);
        }
        // 5단계
        if (answer.isEmpty()) {
            answer.add('a');
        }
        // 6단계
        if (answer.size() >= 16) {
            while (answer.size() > 15) {
                answer.remove(answer.size()-1);
            }
            if (answer.get(answer.size()-1) == '.') {
                answer.remove(answer.size()-1);
            }
        }
        // 7단계
        if (answer.size() <= 2) {
            char addChar = answer.get(answer.size()-1);
            while (answer.size() < 3) {
                answer.add(addChar);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (char c : answer) {
            ans.append(c);
        }
        return ans.toString();
    }
    
    private boolean isInteger(char word) {
        if (intWords.contains(word)) {
            return true;
        }
        return false;
    }
    
    private boolean isAlp(char word) {
        if (word >= 'A' && word <= 'Z') {
            return true;
        }
        if (word >= 'a' && word <= 'z') {
            return true;
        }
        return false;
    }
}

// 아이디 규칙에 맞지않는 아이디를 유사 + 규칙에 맞도로 추천.
// 규칙
// 아이디 길이: 3~15
// 구성: 알파벳 소문자, 숫자, -, _, .만 가능. .는 연속x처음과끝x
// 97 - 65 = 32

// ...