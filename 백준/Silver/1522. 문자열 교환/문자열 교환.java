import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String str;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();

        int bCount = 0;
        for (char c : str.toCharArray()) {
            if (c == 'b') {
                bCount++;
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int aCount = 0;
            for (int j = 0; j < bCount; j++) {
                int index = (i + j) % n;
                if (str.charAt(index) == 'a') {
                    aCount++;
                }
            }
            answer = Math.min(answer, aCount);
        }
        System.out.println(answer);
    }
}
