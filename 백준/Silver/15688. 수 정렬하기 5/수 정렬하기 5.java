import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

// 15688번
public class Main {

    public static int N;
    public static int[] numberTable = new int[2000003];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            numberTable[n+1000000] += 1;
        }
        for (int i = 0; i < 2000003; i++) {
            int cnt = numberTable[i];
            for (int j = 0; j < cnt; j++) {
                sb.append(i-1000000).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
