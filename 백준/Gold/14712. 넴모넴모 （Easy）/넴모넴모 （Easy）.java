import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int m;
    public static boolean[][] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        visited = new boolean[n+1][m+1];
        backtracking(new Point(0,0));
        System.out.println(answer);
    }

    public static void backtracking(Point start) {
        if (is2x2()) {
            return;
        }
        answer++;

        for (int i = start.x; i < n; i++) {
            for (int j = (i == start.x) ? start.y : 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    backtracking(new Point(i, j));
                    visited[i][j] = false;
                }
            }
        }
    }

    public static boolean is2x2() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
