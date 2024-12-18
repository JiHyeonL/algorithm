import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int m;
    public static int[][] cities;
    public static List<Point> chicken = new ArrayList<>();
    public static List<Point> house = new ArrayList<>();
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        cities = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                cities[i][j] = Integer.parseInt(temp[j]);
                if (cities[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
                if (cities[i][j] == 1) {
                    house.add(new Point(i, j));
                }
            }
        }
        dfs_chicken(0, 0, new boolean[chicken.size()]);
        System.out.println(answer);
    }

    public static void dfs_chicken(int count, int start, boolean[] visited) {
        if (count == m) {
            // 거리 계산
            List<Point> selectedChicken = new ArrayList<>();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    selectedChicken.add(chicken.get(i));
                }
            }
            int result = calculateLength(selectedChicken);
            answer = Math.min(answer, result);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs_chicken(count + 1, i + 1, visited);
                visited[i] = false;
            }
        }
    }

    // 모든 집의 가장 가까운 치킨집까지 거리의 합 구하기
    public static int calculateLength(List<Point> selectedChicken) {
        int sum = 0;

        for (int i = 0; i < house.size(); i++) {
            // 모든 경로 중 가장 가까운 2를 찾는거니까 bfs
            sum += min_length(house.get(i), selectedChicken);
        }
        return sum;
    }

    public static int min_length(Point start, List<Point> selectedChicken) {
        int answer = Integer.MAX_VALUE;
        for (Point end : selectedChicken) {
            answer = Math.min(Math.abs(start.x - end.x) + Math.abs(start.y - end.y), answer);
        }
        return answer;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Point point) {
            return point.x == x && point.y == y;
        }
    }
}
