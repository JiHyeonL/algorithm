import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static String str;
    private static int[] alphabet = new int[26];
    private static char result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            alphabet[Character.toUpperCase(str.charAt(i)) - 'A'] += 1;
        }

        int max = 0;
        int maxSecond = 0;
        int maxIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (max <= alphabet[i]) {
                maxSecond = max;
                maxIndex = i;
                max = alphabet[i];
            }
        }

        if (max == maxSecond) {
            System.out.println("?");
            return;
        }

        result = (char)((int)'A' + maxIndex);
        System.out.println(result);
    }

}

