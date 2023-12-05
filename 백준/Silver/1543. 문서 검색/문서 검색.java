import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static String strOne;
    private static String strTwo;
    private static int[] alphabet = new int[26];
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strOne = br.readLine();
        strTwo = br.readLine();

        int oneI = 0;
        while (oneI < strOne.length()) {
            if (strOne.length() - oneI < strTwo.length()) {
                break;
            }
            String target = strOne.substring(oneI, oneI + strTwo.length());
            if (target.equals(strTwo)) {
                oneI += strTwo.length();
                result++;
                continue;
            }
            oneI++;
        }

        System.out.println(result);
    }

}

