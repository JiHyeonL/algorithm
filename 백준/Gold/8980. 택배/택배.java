import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static int n;
    public static int c;
    public static int m;
    public static Delivery[] deliveries;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        c = Integer.parseInt(temp[1]);
        m = Integer.parseInt(br.readLine());
        deliveries = new Delivery[m];
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            deliveries[i] = new Delivery(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        }
        Arrays.sort(deliveries, (o1, o2) -> {
            if (o1.destination < o2.destination) {
                return -1;
            }
            else if (o1.destination == o2.destination) {
                if (o1.start < o2.start) {
                    return -1;
                }
                else if (o1.start == o2.start) {
                    return 0;
                }
                return 1;
            }
            else {
                return 1;
            }
        });

        // i마을 당 보낼 수 있는 박스 최대 개수
        int[] weight = new int[n+1];
        for (int i = 1; i <= n; i++) {
            weight[i] = c;
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            Delivery delivery = deliveries[i];
            int maxCount = Integer.MAX_VALUE;
            // 현재 택배정보의 출발지부터 도착지 경로 중 가장 담을 수 있는 개수가 작은 값 찾기
            // weight이 c가 아니라는 것은 이미 택배 전송이 이루어진 경로라는 뜻
            // 이 최소 개수를 넘어서 싣을 수 없음. 만약 0인 weight이 존재한다면 해당 택배는 배송이 아예 불가능
            for (int j = delivery.start; j < delivery.destination; j++) {
                maxCount = Math.min(maxCount, weight[j]);
            }
            // 현재 박스 전부 트럭에 싣기
            if (maxCount >= delivery.count) {
                // 출발지 ~ 도착지 경로의 가능한 무게 수에서 현재 택배의 개수만큼 빼주기
                for (int j = delivery.start; j < delivery.destination; j++) {
                    weight[j] -= delivery.count;
                }
                answer += delivery.count;
            }
            // 싣을 수 있는 박스 수보다 더 많기 때문에 담을 수 있는 만큼만 싣기
            else {
                // 출발지 ~ 도착지 경로의 가능한 무게 수에서 현재 택배의 개수만큼 빼주기
                for (int j = delivery.start; j < delivery.destination; j++) {
                    weight[j] -= maxCount;
                }
                answer += maxCount;
            }
        }
        System.out.println(answer);
    }

    static class Delivery {
        int start;
        int destination;
        int count;

        public Delivery(int start, int destination, int count) {
            this.start = start;
            this.destination = destination;
            this.count = count;
        }
    }

}
