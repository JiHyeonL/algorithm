import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int f;
    public static int s;
    public static int g;
    public static int u;
    public static int d;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        f = Integer.parseInt(temp[0]);
        s = Integer.parseInt(temp[1]);
        g = Integer.parseInt(temp[2]);
        u = Integer.parseInt(temp[3]);
        d = Integer.parseInt(temp[4]);

        int[] visited = new int[f + 1];
        Queue<Integer> queue = new LinkedList();
        visited[s] = 1;
        queue.add(s);
        while (!queue.isEmpty()) {
            int floor = queue.poll();
            if (floor == g) {
                break;
            }
            int up = floor + u;
            int down = floor - d;
            if (inRange(up) && visited[up] == 0) {
                queue.add(up);
                visited[up] = visited[floor] + 1;
            }
            if (inRange(down) && visited[down] == 0) {
                queue.add(down);
                visited[down] = visited[floor] + 1;
            }
        }

        if (visited[g] == 0) {
            System.out.println("use the stairs");
        } else {
            System.out.println(visited[g] - 1);
        }
    }

    private static boolean inRange(int floor) {
        return floor >= 1 && floor <= f;
    }
}
