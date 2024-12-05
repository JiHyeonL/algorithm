import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static List<Integer> rope = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            rope.add(Integer.parseInt(br.readLine()));
        }
        rope.sort((o1, o2) -> o2-o1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = Math.max(sum, rope.get(i) * (i+1));
        }
        System.out.println(sum);
    }

    static class Cls {
        int s;
        int t;

        public Cls(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }
}
