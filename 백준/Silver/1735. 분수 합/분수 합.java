import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int a[] = new int[2];
    public static int b[] = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        a[0] = Integer.parseInt(temp[0]);
        a[1] = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        b[0] = Integer.parseInt(temp[0]);
        b[1] = Integer.parseInt(temp[1]);

        int numerator = a[0] * b[1] + b[0] * a[1];
        int denominator = a[1] * b[1];
        // 분모 분자 최대공약수 구하기
        int gcd = gcd(numerator, denominator);
        System.out.println((numerator / gcd) + " " + (denominator / gcd));
    }

    private static int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) {
            return b;
        }
        return gcd(b, r);
    }
}
