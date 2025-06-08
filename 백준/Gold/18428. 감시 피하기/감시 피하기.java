import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static int n;
    private static String[][] school;
    private static List<Point> teachers = new ArrayList<>();
    private static boolean isYes = false;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        school = new String[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                school[i][j] = temp[j];
                if (temp[j].equals("T")) {
                    teachers.add(new Point(i, j));
                }
            }
        }
        backTracking(0);
        if (isYes) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void backTracking(int count) {
        if (count == 3) {
            // 판단
            if (validate()) {
                isYes = true;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (school[i][j].equals("X")) {
                    school[i][j] = "O";
                    backTracking(count + 1);
                    if (isYes) {
                        return;
                    }
                    school[i][j] = "X";
                }
            }
        }
    }

    private static boolean validate() {
        for (Point teacher : teachers) {
            for (int dir = 0; dir < 4; dir++) {
                int x = teacher.x;
                int y = teacher.y;
                while (true) {
                    x += dx[dir];
                    y += dy[dir];
                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        break;
                    }
                    if (school[x][y].equals("O")) {
                        break;
                    }
                    if (school[x][y].equals("S")) {
                        return false;
                    }
                }
            }
        }
        return true;
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
