import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static long[] buildings;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        buildings = new long[n];
        for (int i = 0; i < n; i++) {
            buildings[i] = Long.parseLong(temp[i]);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            // 1. 빌딩의 왼쪽에서 보이는 빌딩 수 구하기
            int leftCount = getLeftCount(i);
            if (i != 0) {
                leftCount = getLeftCount(i);
            }

            // 2. 빌딩의 오른쪽에서 보이는 빌딩 수 구하기
            int rightCount = 0;
            if (i != n - 1) {
                rightCount = getRightCount(i);
            }
            answer = Math.max(answer, leftCount + rightCount);
        }
        System.out.println(answer);
    }

    private static int getLeftCount(int targetIndex) {
        int answer = 0;
        double beforeSlope = 0;
        for (int i = targetIndex - 1; i >= 0; i--) {
            double slope = (double) (buildings[targetIndex] - buildings[i]) / (targetIndex - i);
            if (i == targetIndex -1 || beforeSlope > slope) {
                answer++;
                beforeSlope = slope;
            }
        }
        return answer;
    }


    private static int getRightCount(int targetIndex) {
        int answer = 0;
        double beforeSlope = 0;
        for (int i = targetIndex + 1; i < n; i++) {
            double slope = (double) (buildings[targetIndex] - buildings[i]) / (targetIndex - i);
            if (i == targetIndex + 1 || beforeSlope < slope) {
                answer++;
                beforeSlope = slope;
            }
        }
        return answer;
    }
}
