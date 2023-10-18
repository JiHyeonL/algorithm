import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 2839번
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 5로 나누어 떨어질 때까지 3을 빼준다.
        int count = 0;
        while (n > 2) {
            // 5로 나누어 떨어지는 경우
            if (n % 5 == 0) {
                count += n / 5;
                break;
            }
            // 3으로 나누어 떨어지지 않는 경우 -> -1 반환
            if (n < 5 && n % 3 != 0) {
                count = -1;
                break;
            }
            n -= 3;
            count++;
        }
        System.out.println(count);
    }

}