import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        people = new int[n];
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(temp[i]);
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int count = -1;
            for (int j = 0; j < n; j++) {
                if (answer[j] == 0) {
                    count++;
                }
                if (count == people[i] && answer[j] == 0) {
                    answer[j] = i + 1;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }
}
