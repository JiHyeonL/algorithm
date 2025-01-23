import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int[] child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        child = new int[n];
        for (int i = 0; i < n; i++) {
            child[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(child[0]);
        for (int i = 1; i < n; i++) {
            if (lis.get(lis.size() -1) < child[i]) {
                lis.add(child[i]);
            } else {
                int index = binarySearch(lis, child[i]);
                lis.set(index, child[i]);
            }
        }
        System.out.println(n - lis.size());
    }

    private static int binarySearch(List<Integer> lis, int target) {
        int start = 0;
        int end = lis.size() -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target < lis.get(mid)) {
                end = mid - 1;
            }
            else if (target == lis.get(mid)) {
                start = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }
}
