import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int n;
    public static List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n < 10) {
            System.out.println(n);
            return;
        }
        if (n >= 1023) {
            // 감소하는 수 최대 = 9,876,543,210
            // 1자리 감소 경우의 수 = 10
            // 2자리 감소 경우의 수 = 10c2 (10개 숫자 중 2개를 뽑아 큰 순서대로 나열.)
            // 10자리 감소 경우의 수 = 10c10
            // 1자리 ~ 10자리 감소 경우의 수 = 2^10 - 1 = 1023
            System.out.println(-1);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            recursive(1, i);
        }
        Collections.sort(numbers);
        System.out.println(numbers.get(n));
    }

    static void recursive(int index, long number) {
        if (index > 10) {
            return;
        }
        numbers.add(number);
        for (int i = 0; i < number % 10; i++) {
            recursive(index + 1, number * 10 + i);
        }
    }
}
