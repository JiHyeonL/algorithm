import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 1920번
public class Main {

    public static int N;
    public static int M;
    public static Set<Long> nSet = new HashSet<>();
    public static List<Long> mList = new ArrayList<>();
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nSet.add(Long.parseLong(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mList.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M-1; i++) {
            if (nSet.contains(mList.get(i))) {
                answer.append("1\n");
                continue;
            }
            answer.append("0\n");
        }
        if (nSet.contains(mList.get(M-1))) {
            answer.append("1");
        } else { answer.append("0"); }

        System.out.println(answer.toString());
    }
}