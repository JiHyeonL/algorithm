import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int m;
    public static int n;
    public static int l;
    public static int[] shoots;
    public static Point[] animals;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        l = Integer.parseInt(temp[2]);
        shoots = new int[m];
        temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            shoots[i] = Integer.parseInt(temp[i]);
        }
        animals = new Point[n];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            animals[i] = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        Arrays.sort(shoots);
        int count = 0;
        for (Point animal : animals) {
            if (binarySearch(animal)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean binarySearch(Point animal) {
        int start = 0;
        int end = m - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            long length = Math.abs(shoots[mid] - animal.x) + animal.y;
            if (length <= l) {
                return true;
            }
            if (animal.x < shoots[mid]) {
                end = mid - 1;
            } else if (animal.x == shoots[mid]) {
                return false;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
