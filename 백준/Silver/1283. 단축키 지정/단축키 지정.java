import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static String[] options;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        options = new String[n];
        for (int i = 0; i < n; i++) {
            options[i] = br.readLine();
        }

        boolean[] alphabet = new boolean[26];
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            boolean shortcut = false;
            // 1. 단어의 첫 글자가 단축키로 지정되었는지 확인
            String[] words = options[i].split(" ");
            for (int j = 0; j < words.length; j++) {
                int firstCharIndex = getIndex(words[j].charAt(0));
                if (!alphabet[firstCharIndex]) {
                    answer.append(makeFirstAnswer(words, j)).append("\n");
                    alphabet[firstCharIndex] = true;
                    shortcut = true;
                    break;
                }
            }
            if (shortcut) {
                continue;
            }
            // 2. 차례대로 단축키 지정 안된 것이 있으면 지정
            words = options[i].split("");
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(" ")) {
                    continue;
                }
                int charIndex = getIndex(words[j].charAt(0));
                if (!alphabet[charIndex]) {
                    answer.append(makeSecondAnswer(words, j)).append("\n");
                    alphabet[charIndex] = true;
                    shortcut = true;
                    break;
                }
            }
            if (!shortcut) {
                answer.append(options[i]).append("\n");
            }
        }
        System.out.println(answer);
    }

    private static int getIndex(char alphabet) {
        if (alphabet < 'a') {   // 대문자
            return alphabet - 'A';
        }
        return alphabet - 'a';
    }

    private static String makeFirstAnswer(String[] words, int index) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == index) {
                result.append("[").append(words[i].charAt(0)).append("]").append(words[i].substring(1))
                        .append(" ");
                continue;
            }
            result.append(words[i]).append(" ");
        }
        return result.toString();
    }

    private static String makeSecondAnswer(String[] words, int index) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == index) {
                result.append("[").append(words[i]).append("]");
                continue;
            }
            result.append(words[i]);
        }
        return result.toString();
    }
}
