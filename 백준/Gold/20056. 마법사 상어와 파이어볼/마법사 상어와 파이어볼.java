import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;    // 격자 크기
    public static int m;    // 파이어볼 개수
    public static int k;    // 파이어볼 이동 총 횟수
    public static List<Info>[][] fireballs;
    public static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        k = Integer.parseInt(temp[2]);

        fireballs = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fireballs[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int r = Integer.parseInt(temp[0]) - 1;
            int c = Integer.parseInt(temp[1]) - 1;
            fireballs[r][c].add(new Info(Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4])));
        }

        // 파이어볼 k번 이동
        for (int cnt = 0; cnt < k; cnt++) {
            List<Info>[][] fireballsTemp = new ArrayList[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    fireballsTemp[i][j] = new ArrayList<>(); // 새로운 배열 초기화
                }
            }
            // 한 번 이동
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (Info info : fireballs[i][j]) {
                        int ni = calculateIndex(i + di[info.d] * info.s);
                        int nj = calculateIndex(j + dj[info.d] * info.s);
                        fireballsTemp[ni][nj].add(new Info(info.m, info.s, info.d));
                    }
                }
            }
            fireballs = fireballsTemp;
            // 2개 이상의 파이어볼이 있는 칸의 파이어볼 분리
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (fireballs[i][j].size() < 2) {
                        continue;
                    }
                    int count = fireballs[i][j].size();
                    int newM = 0;
                    int newS = 0;
                    int evenCount = 0;
                    for (Info info : fireballs[i][j]) {
                        newM += info.m;
                        newS += info.s;
                        if (info.d % 2 == 0) {
                            evenCount++;
                        }
                    }
                    newM /= 5;
                    newS /= count;
                    int[] newD = evenCount == 0 || evenCount == count ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                    fireballs[i][j] = new ArrayList<>();
                    if (newM != 0) {
                        for (int h = 0; h < 4; h++) {
                            fireballs[i][j].add(new Info(newM, newS, newD[h]));
                        }
                    }
                }
            }
        }
        // 질량의 합 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Info info : fireballs[i][j]) {
                    answer += info.m;
                }
            }
        }
        System.out.println(answer);
    }

    private static int calculateIndex(int number) {
        return (number % n + n) % n;
    }

    static class Info {
        int m;  // 질량
        int s;  // 속력
        int d;  // 방향

        public Info(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
