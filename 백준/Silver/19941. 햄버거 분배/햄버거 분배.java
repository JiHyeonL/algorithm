import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static int k;
    private static String[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        temp = br.readLine().split("");
        table = new String[n];
        for (int i = 0; i < n; i++) {
            table[i] = temp[i];
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!table[i].equals("P")) {
                continue;
            }

            for (int j = i - k; j <= i + k; j++) {
                if (j < 0 || j >= n) {
                    continue;
                }
                if (table[j].equals("H")) {
                    answer++;
                    table[j] = "";  // 햄버거 먹음
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
