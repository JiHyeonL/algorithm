import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int n;
    public static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        System.out.println(answer());
    }

    // 수빈이: 1초 후 x -1 or x + 1 or 2 * x로 이동
    // 수빈이 동생을 찾을 수 있는 가장 빠른 시간 구하기
    private static int answer() {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001];
        queue.add(n);
        visited[n] = 1;
        // 인덱스 = 위치
        // 도착지 visited 값 = 초
        // 다음 위치 = 3가지 경우의 수 위치
        if (n == k) {
            return 0;
        }

        while (!queue.isEmpty()) {
            final int x = queue.poll();
            if (x == k) {
                return visited[x] -1;
            }
            for (int i = 0; i < 3; i++) {
                final int nx = calculate(i, x);
                // 이동
                if (inRange(nx) && visited[nx] == 0) {
                    queue.add(nx);
                    visited[nx] = visited[x] + 1;
                    //System.out.println("추가: " + nx + ", " + visited[nx]);
                }
            }
        }
        return visited[k];
    }

    // x = 현재 위치
    private static int calculate(int index, int x) {
        if (index == 0) {
            return x - 1;
        }
        if (index == 1) {
            return x + 1;
        }
        return 2 * x;
    }

    private static boolean inRange(int i) {
        if (i < 0 || i > 100000) {
            return false;
        }
        return true;
    }
}

