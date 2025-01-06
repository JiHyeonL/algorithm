import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static int t;
    public static String w;
    public static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            w = br.readLine();
            k = Integer.parseInt(br.readLine());
            System.out.println(answer());
        }
    }

    public static String answer() {
        List<List<Integer>> alphabets = new ArrayList<>();
        for (int i = 0 ; i < 26; i++) {
            alphabets.add(new ArrayList<>());
        }
        char[] ww = w.toCharArray();
        // 1. 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
        for (int i = 0; i < ww.length; i++) {
            alphabets.get(ww[i] - 'a').add(i);
        }
        int firstAnswer = 10001;
        for (int i = 0; i < 26; i++) {
            List<Integer> alp = alphabets.get(i);
            if (alp.size() >= k) {
                for (int j = 0; j < alp.size() - k + 1; j++) {
                    firstAnswer = Math.min(firstAnswer, alp.get(j + k -1) - alp.get(j) + 1);
                }
            }
        }
        // 2. 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
        if (firstAnswer == 10001) {
            return "-1";
        }
        int secondAnswer = -1;
        for (int i = 0; i < 26; i++) {
            List<Integer> alp = alphabets.get(i);
            if (alp.size() >= k) {
                for (int j = 0; j < alp.size() - k + 1; j++) {
                    secondAnswer = Math.max(secondAnswer, alp.get(j + k -1) - alp.get(j) + 1);
                }
            }
        }
        return firstAnswer + " " + secondAnswer;
    }
}
