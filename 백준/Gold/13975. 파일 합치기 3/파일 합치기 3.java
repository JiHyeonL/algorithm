import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int t;
    public static long n;
    public static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                list.add(Long.parseLong(temp[j]));
            }
            System.out.println(answer());
        }
    }

    public static long answer() {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(list.get(i));
        }

        long answer = 0;
        while (queue.size() > 1) {
            long sum = queue.poll() + queue.poll();
            queue.add(sum);
            answer += sum;
        }
        return answer;
    }
}
