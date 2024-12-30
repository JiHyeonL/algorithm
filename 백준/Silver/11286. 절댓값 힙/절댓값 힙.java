import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int n;
    public static int m;
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        array = new int[n + 1];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (Math.abs(o1) > Math.abs(o2)) {
                        return 1;
                    }
                    if (Math.abs(o1) < Math.abs(o2)) {
                        return -1;
                    }
                    if (o1 < o2) {
                        return -1;
                    }
                    if (o1 > o2) {
                        return 1;
                    }
                    return 0;
                });

        for (int i = 0; i < n; i++) {
            if (array[i] == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    int num = queue.poll();
                    System.out.println(num);
                }
                continue;
            }
            queue.add(array[i]);
        }
    }
}
