import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static int n;
    public static int m;
    public static Map<Integer, Integer> ladder = new HashMap<>();
    public static Map<Integer, Integer> snake = new HashMap();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            ladder.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            snake.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(1, 0));
        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!queue.isEmpty()) {
            Move now = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int next = now.number + i;
                if (next <= 100 && !visited[next]) {
                    if (next == 100) {
                        return now.count + 1;
                    }
                    if (ladder.containsKey(next)) {
                        queue.add(new Move(ladder.get(next), now.count + 1));
                        visited[next] = true;
                    }
                    else if (snake.containsKey(next)) {
                        queue.add(new Move(snake.get(next), now.count + 1));
                        visited[next] = true;
                    }
                    else {
                        queue.add(new Move(next, now.count + 1));
                        visited[next] = true;
                    }
                }
            }
        }
        return 0;
    }

    static class Move {
        int number;
        int count;

        public Move(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}
