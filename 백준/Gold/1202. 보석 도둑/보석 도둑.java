import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 1202번
public class Main {
    public static int N;    // 보석 개수
    public static int K;    // 가방 개수
    public static ArrayList<Info> jewel = new ArrayList<>(); // 보석의 무게, 가격이 들어있는 리스트 (o1,o2) -> o2.price - o1.price
    public static ArrayList<Integer> bag = new ArrayList<>();

    static class Info {
        int weight;
        int price;

        Info(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewel.add(new Info(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        for (int j = 0; j < K; j++) {
            bag.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(jewel,(o1,o2) -> o1.weight-o2.weight);
        Collections.sort(bag);

        PriorityQueue<Info> priorityQueue = new PriorityQueue<>((o1,o2) -> o2.price - o1.price);
        long maxPrice = 0;
        int index = 0;
        for (int i = 0; i < K; i++) {
            while (index < N && jewel.get(index).weight <= bag.get(i)) {
                priorityQueue.offer(new Info(jewel.get(index).weight,jewel.get(index).price));
                index++;
            }
            if (!priorityQueue.isEmpty()) {
                maxPrice += priorityQueue.poll().price;
            }
        }
        System.out.println(maxPrice);
    }

}

