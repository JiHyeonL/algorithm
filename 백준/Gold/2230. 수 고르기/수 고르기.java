import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int n;
    public static int m;
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(array);
        System.out.println(twoPointer());
    }

    public static int twoPointer() {
        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (left <= right) {
            if (right == n) {
                break;
            }
            if (array[right] - array[left] == m) {
                //System.out.println("같음: " + left + ", " + right + ", " + array[left] + ", " + array[right]);
                return m;
            }
            if (array[right] - array[left] > m) {
                //System.out.println("더 큼: " + left + ", " + right);
                answer = Math.min(answer, array[right] - array[left]);
                left++;
            } else {
                //System.out.println("더 작음: " + left + ", " + right);
                right++;
            }
        }
        return answer;
    }
}
