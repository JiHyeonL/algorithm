import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 나무 자르기
public class Main {

    public static int n;
    public static int m;
    public static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        trees = new int[n+1];
        String[] treeStr = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(treeStr[i]);
        }
        System.out.println(answer());
    }

    private static int answer() {
        // 기준 = 높이
        int start = 0;
        int end = Arrays.stream(trees).max().getAsInt();
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;
            //System.out.println(start + ", " + end + ", " + mid);
            long treeSum = 0;
            for (int i = 0; i < n; i++) {
                if (trees[i] > mid) {
                    treeSum += trees[i] - mid;
                }
            }
            // 나무의 합이 m보다 더 크면 더 최적의 값을 찾기 위해 절단기 높이기
            if (treeSum >= m) {
                //System.out.println(treeSum + "이 m보다 더 크거나 같습니다");
                start = mid + 1;
            }
            // 나무의 합이 m보다 작으면 mid가 더 작아져야 함.
            else if (treeSum < m) {
                //System.out.println(treeSum + "이 m보다 더 작습니다");
                end = mid - 1;
            }
        }
        return end;
    }
}
