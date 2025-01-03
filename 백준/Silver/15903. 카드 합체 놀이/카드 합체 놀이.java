import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static int n;
    public static int m;
    public static PriorityQueue<Long> cards = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cards.add(Long.parseLong(temp[i]));
        }

        for (int i = 0; i < m; i++) {
            long sum = cards.poll() + cards.poll();
            cards.add(sum);
            cards.add(sum);
        }

        long answer = 0;
        while (!cards.isEmpty()) {
            answer += cards.poll();
        }
        System.out.println(answer);
    }

}
