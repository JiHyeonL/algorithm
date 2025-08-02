import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static int n;
    private static int m;
    private static int[][] wood;
    private static int[][] dx = {{-1, 0}, {0, 1}, {0, 1}, {-1, 0}};
    private static int[][] dy = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int answer = 0;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        wood = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                wood[i][j] = Integer.parseInt(temp[j]);
            }
        }
        visited = new boolean[n][m];
        backtracking(0, 0);
        System.out.println(answer);
    }

    private static void backtracking(int start, int sum) {
        if (start == n * m) {
            answer = Math.max(answer, sum);
            return;
        }

        int x = start / m;
        int y = start % m;
        if (!visited[x][y]) {
            for (int i = 0; i < 4; i++) {
                int firstX = x + dx[i][0];
                int firstY = y + dy[i][0];
                int secondX = x + dx[i][1];
                int secondY = y + dy[i][1];
                if (inRange(firstX, firstY, secondX, secondY) && !visited[firstX][firstY]
                        && !visited[secondX][secondY]) {
                    visited[x][y] = true;
                    visited[firstX][firstY] = true;
                    visited[secondX][secondY] = true;
                    backtracking(start + 1, sum + wood[firstX][firstY] + wood[secondX][secondY] + wood[x][y] * 2);
                    visited[x][y] = false;
                    visited[firstX][firstY] = false;
                    visited[secondX][secondY] = false;
                }
            }
        }
        backtracking(start + 1, sum);
    }

    private static boolean inRange(int firstX, int firstY, int secondX, int secondY) {
        return firstX >= 0 && firstX < n && firstY >= 0 && firstY < m &&
                secondX >= 0 && secondX < n && secondY >= 0 && secondY < m;
    }
}
