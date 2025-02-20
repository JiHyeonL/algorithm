import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int m;
    public static int l;
    public static int k;
    public static List<Star> stars = new ArrayList<>();
    public static int[] dx = {-1, 1, -1, 1};
    public static int[] dy = {1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        l = Integer.parseInt(temp[2]);
        k = Integer.parseInt(temp[3]);
        for (int i = 0; i < k; i++) {
            temp = br.readLine().split(" ");
            stars.add(new Star(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }

        int answer = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                answer = Math.max(answer, countStars(stars.get(i).x, stars.get(j).y));
            }
        }
        System.out.println(k - answer);
    }

    private static int countStars(int firstX, int secondY) {
        int result = 0;
        for (int i = 0; i < k; i++) {
            Star star = stars.get(i);
            if (firstX <= star.x && star.x <= firstX + l && secondY <= star.y && star.y <= secondY + l) {
                result++;
            }
        }
        return result;
    }

    static class Star {
        int x;
        int y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
