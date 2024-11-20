import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int m;
    public static int n;
    public static int[][] tomato;
    public static final int[] dx = {1,0,-1,0};
    public static final int[] dy = {0,-1,0,1};

    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        tomato = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<XY> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 1 && !visited[i][j]) {
                    queue.add(new XY(i,j));
                }
            }
        }
        if (queue.isEmpty()) {
            System.out.println(-1);
            return;
        }
        if (queue.size() == n * m) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            XY node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = node.x + dx[i];
                int newY = node.y + dy[i];
                if (inRange(newX, newY) && tomato[newX][newY] == 0) {
                    queue.add(new XY(newX, newY));
                    tomato[newX][newY] = tomato[node.x][node.y] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                answer = Math.max(tomato[i][j], answer);
            }
        }
        System.out.println(answer -1);
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


    static class XY {
        int x;
        int y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
