import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

// 11728번
public class Main {

    public static int N;
    public static int M;
    public static ArrayList<Integer> A = new ArrayList<>();
    public static ArrayList<Integer> B = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        int indexA = 0;
        int indexB = 0;
        for (int i = 0; i < N + M; i++) {
            if (indexA == N) {
                while (indexB < M) {
                    sb.append(B.get(indexB++) + " ");
                }
                break;
            }
            if (indexB == M) {
                while (indexA < N) {
                    sb.append(A.get(indexA++) + " ");
                }
                break;
            }
            if (A.get(indexA) <= B.get(indexB)) {
                sb.append(A.get(indexA++) + " ");
                continue;
            }
            sb.append(B.get(indexB++) + " ");
        }

        System.out.println(sb.toString());
    }
}