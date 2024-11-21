import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int k;
    public static String[] words;
    public static final char[] learn = {'a', 'n', 't', 'i', 'c'};
    public static boolean[] visited = new boolean[26];
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);

        if (k < 5) {
            System.out.println(0);
            return;
        }
        if (k == 26) {
            System.out.println(n);
            return;
        }

        words = new String[n];
        for (int i = 0; i < n; i++) {
            String full = br.readLine();
            words[i] = full.substring(4, full.length() - 4);
        }

        for (int i = 0; i < learn.length; i++) {
            visited[learn[i] - 'a'] = true;
        }
        backTracking(0, 0);
        System.out.println(answer);
    }

    public static void backTracking(int start, int learnCount) {
        if (learnCount == k - 5) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean canRead = true;
                char[] word = words[i].toCharArray();
                for (char alp : word) {
                    if (!visited[alp-'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }

        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(i, learnCount+1);
                visited[i] = false;
            }
        }
    }
}
