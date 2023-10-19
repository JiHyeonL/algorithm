import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 15683번
public class Main {

    public static int n;
    public static HashMap<String, String> office = new HashMap<>();
    public static ArrayList<String> inOffice = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();

            // 처음 들어간 경우(enter)
            if (!office.containsKey(name)) {
                office.put(name, status);
                continue;
            }
            // 이미 값이 있는 경우(leave 처리)
            office.remove(name);
        }

        for (String name : office.keySet()) {
            inOffice.add(name);
        }

        Collections.sort(inOffice, (o1, o2) -> {return o2.compareTo(o1);});

        for (String name : inOffice) {
            System.out.println(name);
        }
    }

}
