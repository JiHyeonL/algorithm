import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 18870번
public class Main {

    public static int N;
    public static List<Long> sortList = new ArrayList<>();
    public static List<Long> answerList = new ArrayList<>();
    public static HashMap<Long, Long> setList = new HashMap<>();
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sortList.add(Long.parseLong(st.nextToken()));
        }

        answerList = new ArrayList<>(sortList);
        Collections.sort(sortList);

        Long count = 0L;
        for (Long number : sortList) {
            if (!setList.containsKey(number)) {
                setList.put(number, count);
                count++;
            }
        }

        for (Long number : answerList) {
            answer.append(setList.get(number)).append(" ");
        }

        System.out.println(answer.toString());
    }
}

