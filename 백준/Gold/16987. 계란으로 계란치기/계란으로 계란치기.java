import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16987
public class Main {

    public static int n;
    public static int[] s;
    public static int[] w;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n + 1];
        w = new int[n + 1];
        int[] myS = new int[n + 1];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            s[i] = Integer.parseInt(temp[0]);
            w[i] = Integer.parseInt(temp[1]);
            myS[i] = s[i];
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int start, int count) {
        // 가장 오른쪽 계란일 경우 종료
        if (start - 1 == n-1) {
            answer = Math.max(answer, count);
            return;
        }
        // 손에 들고 있는 계란이 깨져있으면 한 칸 오른쪽의 계란을 든다
        if (s[start] <= 0) {
            dfs(start + 1, count);
        }
        // 같이 깰 계란 선택
        for (int i = 0; i < n; i++) {
            if (s[start] <= 0) {
                answer = Math.max(answer, count);
                break;
            }
            // 손에 들고 있는 계란과 같은 계란이거나 깨져있는 계란이면 패스
            if (i == start || s[i] <= 0) {
                continue;
            }
            // 깨지지 않은 계란만 허용
            // 계란 깨기
            s[start] -= w[i];
            s[i] -= w[start];
            int brokenCount = 0;
            if (s[start] <= 0) {
                brokenCount++;
            }
            if (s[i] <= 0) {
                brokenCount++;
            }
            dfs(start + 1, count + brokenCount);
            // 계란 깨기 전으로 이동
            s[start] += w[i];
            s[i] += w[start];
        }
        answer = Math.max(answer, count);
    }
}
