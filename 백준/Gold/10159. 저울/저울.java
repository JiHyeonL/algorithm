import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static int n;
    public static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // compares[i][j] = 1 : i > j 인 관계가 존재한다.
        int[][] compares = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int big = Integer.parseInt(temp[0]);
            int small = Integer.parseInt(temp[1]);
            compares[big][small] = 1;
            compares[small][big] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (compares[i][k] == 1 && compares[k][j] == 1) {
                        compares[i][j] = 1;
                    }
                    if (compares[i][k] == -1 && compares[k][j] == -1) {
                        compares[i][j] = -1;
                    }
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (compares[i][j] != 0) {
                    count++;
                }
            }
            answer.append(n - count -1).append("\n");
        }

        System.out.println(answer);
    }
}
