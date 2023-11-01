import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 10816번
public class Main {

    public static int N;
    public static int M;
    public static Map<Long, Integer> nMap = new HashMap<>();
    public static List<Long> mList = new ArrayList<>();
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Long number = Long.parseLong(st.nextToken());
            if (nMap.containsKey(number)) {
                nMap.put(number, nMap.get(number)+1);
                continue;
            }
            nMap.put(number, 1);
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mList.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M-1; i++) {
            if (nMap.containsKey(mList.get(i))) {
                answer.append(nMap.get(mList.get(i)) + " ");
                continue;
            }
            answer.append("0 ");
        }
        if (nMap.containsKey(mList.get(M-1))) {
            answer.append(nMap.get(mList.get(M-1)));
        } else { answer.append("0"); }

        System.out.println(answer.toString());
    }
}

