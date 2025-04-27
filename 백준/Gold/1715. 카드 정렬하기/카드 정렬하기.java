import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int n;
    public static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(cards[i]);
        }

        int answer = 0;
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            pq.add(sum);
            answer += sum;
        }
        System.out.println(answer);
    }
}
