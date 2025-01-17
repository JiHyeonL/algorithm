import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int h;
    public static int w;
    public static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        h = Integer.parseInt(temp[0]);
        w = Integer.parseInt(temp[1]);

        blocks = new int[w];
        temp = br.readLine().split(" ");
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(temp[i]);
        }

        int rainSum = 0;
        for (int i = 1; i < w; i++) {
            int left = 0;
            int right = 0;

            // i 인덱스보다 왼쪽에 있는 블록 중 가장 높은 h 찾기
            for (int j = 0; j < i; j++) {
                left = Math.max(left, blocks[j]);
            }
            // 오른쪽 블록 중 가장 높은 h 찾기
            for (int j = i + 1; j < w; j++) {
                right = Math.max(right, blocks[j]);
            }
            // 둘 중 작은 높이에 맞추기
            int maxH = Math.min(left, right);
            if (maxH > blocks[i]) { // 만약 maxH가 현재 block 보다 같거나 작으면 빗물이 고일 수 없음
                rainSum += maxH - blocks[i];
            }
        }
        System.out.println(rainSum);
    }
}
