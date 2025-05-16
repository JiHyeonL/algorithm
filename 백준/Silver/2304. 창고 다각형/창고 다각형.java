import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int n;
    private static Pillar[] pillars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pillars = new Pillar[n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            pillars[i] = new Pillar(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        Arrays.sort(pillars, (o1, o2) -> Integer.compare(o1.position, o2.position));

        int answer = 0;
        int maxHeight = 0;
        int maxHeightIndex = 0;
        for (int i = 0; i < n; i++) {
            if (pillars[i].height > maxHeight) {
                maxHeight = pillars[i].height;
                maxHeightIndex = i;
            }
        }

        // 왼쪽부터 가장 기둥이 높은 위치까지
        for (int i = 0; i < maxHeightIndex; i++) {
            for (int j = i + 1; j <= maxHeightIndex; j++) {
                if (pillars[i].height <= pillars[j].height) {
                    answer += (pillars[j].position - pillars[i].position) * pillars[i].height;
                    i = j;
                }
            }
        }

        // 오른쪽부터 가장 기둥이 높은 위치까지
        for (int i = n - 1; i > maxHeightIndex; i--) {
            for (int j = i - 1; j >= maxHeightIndex; j--) {
                if (pillars[i].height <= pillars[j].height) {
                    answer += (pillars[i].position - pillars[j].position) * pillars[i].height;
                    i = j;
                }
            }
        }

        answer += maxHeight;

        System.out.println(answer);
    }

    static class Pillar {
        int position;
        int height;

        public Pillar(int position, int height) {
            this.position = position;
            this.height = height;
        }
    }
}
