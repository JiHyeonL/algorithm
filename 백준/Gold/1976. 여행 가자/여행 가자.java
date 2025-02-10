import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int m;
    public static int[][] cities;
    public static int[] destinations;
    public static boolean[][] canGo;
    public static int start;
    public static int end;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        cities = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                cities[i][j] = Integer.parseInt(temp[j]);
            }
        }
        destinations = new int[m];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            destinations[i] = Integer.parseInt(temp[i]) -1;
        }
        if (m == 1) {
            System.out.println("YES");
            return;
        }

        // 도시와 도시 사이 최단거리 계산
        canGo = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                start = i;
                end = j;
                dfs(i, new boolean[n]);
            }
        }
        
        for (int i = 1; i < m; i++) {
            if (!canGo[destinations[i - 1]][destinations[i]]) {
                if (destinations[i-1] != destinations[i]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static void dfs(int now, boolean[] visited) {
        visited[now] = true;
        if (now == end) {
            canGo[start][end] = true;
            canGo[end][start] = true;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (cities[now][i] == 1 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }
}
