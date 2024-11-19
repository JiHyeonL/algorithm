import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int[][] house;
    public static boolean[][] visited;
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};
    public static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n+1][n + 1];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(temp[j]);
            }
        }
        visited = new boolean[n+1][n+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (house[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(answer.size());
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i)).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int i, int j) {
        int count = 0;
        Queue<XY> queue = new LinkedList<>();

        queue.add(new XY(i, j));
        visited[i][j] = true;
        count++;

        while (!queue.isEmpty()) {
            final XY node = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = node.x + dx[k];
                int y = node.y + dy[k];
                if (inRange(x,y) && !visited[x][y] && house[x][y] == 1) {
                    queue.add(new XY(x, y));
                    visited[x][y] = true;
                    count++;
                }
            }
        }
        answer.add(count);
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
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
