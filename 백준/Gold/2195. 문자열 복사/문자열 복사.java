import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static String s;
    private static String p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        p = br.readLine();

        int answer = 1;
        int index = 0;
        for (int i = 0; i < p.length(); i++) {
            if (s.indexOf(p.substring(index, i + 1)) == -1) {
                answer++;
                index = i;
            }
        }
        System.out.println(answer);
    }
}
