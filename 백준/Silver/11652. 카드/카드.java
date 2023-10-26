import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 11652번
public class Main {

    public static int N;
    public static ArrayList<Long> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            arrayList.add(Long.parseLong(br.readLine()));
        }

        Collections.sort(arrayList);
        int cnt = 0;
        int maxCnt = 0;
        long answer = arrayList.get(0)-1;

        for (int i = 0; i < N; i++) {
            if (i == 0 || arrayList.get(i-1).equals(arrayList.get(i))) {
                cnt++;
                continue;
            }
            if (cnt > maxCnt) {
                maxCnt = cnt;
                answer = arrayList.get(i-1);
            }
            cnt = 1;
        }

        if (cnt > maxCnt) {
            answer = arrayList.get(N-1);
        }
        System.out.println(answer);
    }
}

