import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 7662번
public class Main {

    public static int T;
    public static int K;

    // 가장 작은 값, 가장 큰 값 알아야 한다 & 중복 허용
    // -> HashMap으로 <데이터, 데이터 중복 개수> 표현
    public static TreeMap<Integer,Integer> priorityQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            priorityQ = new TreeMap<>();
            
            for (int j = 0; j < K; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int integer = Integer.parseInt(st.nextToken());

                // integer 삽입
                if (command.equals("I")) {
                    if (!priorityQ.containsKey(integer)) {
                        priorityQ.put(integer, 1);
                        continue;
                    }
                    priorityQ.put(integer, priorityQ.get(integer)+1);
                    continue;
                }
                // 비어있으면 삭제 무시
                if (priorityQ.size() == 0)
                    continue;
                //integer 삭제
                if (integer == -1) { // 최소값 삭제
                    int key = priorityQ.firstKey();
                    if (priorityQ.put(key, priorityQ.get(key)-1) == 1)  // put은 key에 매핑된 이전 값을 리턴해준다.
                        priorityQ.remove(key); // 더이상 값이 없으니 제거해준다.
                }
                if (integer == 1) {  // 최대값 삭제
                    int key = priorityQ.lastKey();
                    if (priorityQ.put(key, priorityQ.get(key)-1) == 1)
                        priorityQ.remove(key);
                }
            }

            if (priorityQ.size() == 0) {
                System.out.println("EMPTY");
                continue;
            }
            System.out.println(priorityQ.lastKey() + " " + priorityQ.firstKey());
        }
    }

}
