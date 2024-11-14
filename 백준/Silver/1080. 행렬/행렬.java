import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1080
// 행렬 - 답지 봄
public class Main {

    public static int n;
    public static int m;
    public static int[][] a;
    public static int[][] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        a = new int[n][m];
        b = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(temp[j]);
            }
        }
        System.out.println(greedy());
    }

    private static int greedy() {
        // n이나 m이 3보다 작은데 a = b가 아니면 무조건 -1
        if (n < 3 || m < 3) {
            if (isSameMatrix()) {
                return 0;
            }
            return -1;
        }
        int answer = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = 0; j < m-2; j++) {
                if (a[i][j] != b[i][j]) {
                    flip(i,j);
                    answer++;
                }
                if (isSameMatrix()) {
                    return answer;
                }
            }
        }
        if (isSameMatrix()) {
            return answer;
        }
        return -1;
    }

    private static void flip(int i, int j) {
        for (int ii = 0; ii < 3; ii++) {
            for (int jj = 0; jj < 3; jj++) {
                a[i + ii][j + jj] = (a[i + ii][j + jj] + 1) % 2;
            }
        }
    }

    private static boolean isSameMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
