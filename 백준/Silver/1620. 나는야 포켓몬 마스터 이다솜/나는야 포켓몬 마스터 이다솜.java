import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 15683번
public class Main {

    public static int n;    // 포켓몬 개수
    public static int m;    // 맞춰야 하는 문제의 개수
    public static HashMap<String, String> pokemon = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            pokemon.put(Integer.toString(i),name);
            pokemon.put(name,Integer.toString(i));
        }

        for (int j = 0; j < m; j++) {
            System.out.println(pokemon.get(br.readLine()));
        }

    }

}