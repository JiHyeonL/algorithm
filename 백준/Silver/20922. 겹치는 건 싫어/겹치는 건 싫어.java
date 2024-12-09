import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int k;
    public static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);

        a = new int[n + 1];
        temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }

        int answer = 0;
        int visited[] = new int[100001];
        int start = 0;
        int end = 0;
        while (end < n) {
            if (visited[a[end]] < k) {
                visited[a[end]] += 1;
                end++;
            } else {
                answer = Math.max(answer, end - start);
                visited[a[start]] -= 1;
                start++;
            }
        }
        System.out.println(Math.max(answer,  end - start));
    }

}
