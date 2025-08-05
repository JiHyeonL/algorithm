import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static String strOne;
    private static String strTwo;
    private static int[] alphabet = new int[26];
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strOne = br.readLine();
        strTwo = br.readLine();

        for (int i = 0; i < strOne.length(); i++) {
            alphabet[strOne.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < strTwo.length(); i++) {
            alphabet[strTwo.charAt(i) - 'a'] -= 1;
        }

        for (int count : alphabet) {
            result += Math.abs(count);
        }

        System.out.println(result);
    }

}

