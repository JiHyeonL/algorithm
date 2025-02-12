import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.sun.jdi.connect.spi.TransportService.ListenKey;

public class Main {

    public static int t;
    public static int n;
    public static Queue<Integer> prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            prices = new LinkedList<>();
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                prices.add(Integer.parseInt(temp[j]));
            }
            System.out.println(greedy());
        }
    }

    private static long greedy() {
        long profit = 0;
        while (!prices.isEmpty()) {
            int index = getMaxIndex();
            if (index == 0) {
                prices.poll();
                continue;
            }
            for (int i = 0; i < index; i++) {
                int price = prices.poll();
                profit -= price;
            }
            long maxValue = prices.poll();
            // 계산
            profit += maxValue * index;
        }
        return profit;
    }

    private static int getMaxIndex() {
        int count = 0;
        int index = 0;
        int maxValue = 0;
        for (int price : prices) {
            if (price >= maxValue) {
                maxValue = price;
                index = count;
            }
            count++;
        }
        return index;
    }
}
