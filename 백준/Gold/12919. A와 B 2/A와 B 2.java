import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    private static String[] s;
    private static String[] t;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine().split("");
        t = br.readLine().split("");
        List<String> word = new ArrayList<>();
        for (String tt : t) {
            word.add(tt);
        }
        dfs(word);
        System.out.println(answer);
    }

    private static void dfs(List<String> word) {
        if (word.size() == s.length) {
            if (isSame(word)) {
                answer = 1;
            }
            return;
        }

        if (answer == 1 || word.isEmpty()) {
            return;
        }
        if (word.get(word.size() - 1).equals("A")) {
            List<String> nWord = new ArrayList<>(word);
            nWord.remove(nWord.size() - 1);
            dfs(nWord);
        }
        if (word.get(0).equals("B")) {
            List<String> nWord = new ArrayList<>(word);
            nWord.remove(0);
            Collections.reverse(nWord);
            dfs(nWord);
        }
    }

    private static boolean isSame(List<String> word) {
        for (int i = 0; i < s.length; i++) {
            if (!word.get(i).equals(s[i])) {
                return false;
            }
        }
        return true;
    }
}
