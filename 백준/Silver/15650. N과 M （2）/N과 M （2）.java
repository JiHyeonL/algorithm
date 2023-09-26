import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 15650번
public class Main {

    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        dfs(0,0,N,M);

        System.out.println(sb);
    }

    public static void dfs(int depth, int start, int N, int M) {
        /** 종료 조건 **/
        if (depth == M) {
            for (int n : arr) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        /** 재귀식 **/
        for (int i = start; i < N; i++) {
            arr[depth] = i+1;
            dfs(depth + 1, i+1, N, M);
        }
    }
}