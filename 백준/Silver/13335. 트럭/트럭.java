

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int n;
    public static int w;
    public static int l;
    public static Queue<Integer> trucks = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        w = Integer.parseInt(temp[1]);
        l = Integer.parseInt(temp[2]);
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(temp[i]));
        }

        System.out.println(answer());
    }

    private static int answer() {
        Queue<Integer> bridge = new LinkedList<>();
        int weight = 0;
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int answer = 0;
        while (!bridge.isEmpty()) {
            answer++;
            weight -= bridge.poll();    // 가장 끝에 있는 트럭 or 빈 값 다리에서 나감
            if (trucks.isEmpty()) {
                continue;
            }
            // 트럭 이동 가능
            if (trucks.peek() + weight <= l) {
                int truck = trucks.poll();
                bridge.add(truck);  // 다리로 트럭 출발
                weight += truck;
            } else {
                bridge.add(0);
            }
        }
        return answer;
    }
}
