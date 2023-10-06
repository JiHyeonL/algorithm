import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.StringTokenizer;

// 1010번
public class Main {

    public static int[][] count = new int[30][30];
    public static int[] listN;
    public static int[] listM;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        listN = new int[T];
        listM = new int[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            listN[i] = Integer.parseInt(st.nextToken());
            listM[i] = Integer.parseInt(st.nextToken());

            System.out.println(countBridge(listM[i],listN[i])); // n과 m 순서 혼동하면 안됨!
        }
    }

    public static int countBridge(int n, int m) {
        // 이미 푼 문제 재활용
        if(count[n][m] > 0)
            return count[n][m];
        // 초기 조건
        if (n == m || m == 0) {
            return count[n][m] = 1;
        }
        // 재귀
        return count[n][m] = countBridge(n-1,m-1) + countBridge(n-1, m);
    }


}