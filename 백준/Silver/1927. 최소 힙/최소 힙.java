import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int n;
    private static int[] operations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        operations = new int[n];
        for (int i = 0; i < n; i++) {
            operations[i] = Integer.parseInt(br.readLine());
        }
        // 0 = 가장 작은 값 출력 후 제거
        PriorityQueue pq = new PriorityQueue();
        StringBuilder sb = new StringBuilder();
        for (int operation : operations) {
            if (operation == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                pq.add(operation);
            }
        }
        System.out.println(sb);
    }
}
