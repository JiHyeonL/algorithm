import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int n;
    public static long[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        array = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                array[i][j] = Integer.parseInt(temp[j]);
            }
        }
        // 초기 세팅
        boolean[][] visited = new boolean[n+1][n+1];
        List<Point> answer = new ArrayList<>();
        List<Point> candidate = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            candidate.add(new Point(n-1, j, array[n-1][j]));
        }
        Collections.sort(candidate, (o1, o2) -> {
            if (o2.num > o1.num) {
                return 1;
            }
            return -1;
        });
        answer.add(candidate.get(0));
        visited[candidate.get(0).i][candidate.get(0).j] = true;
        // 같은 숫자 있을 경우 처리
        for (int i = 1; i < n; i++) {
            if (candidate.get(i).num == candidate.get(i - 1).num) {
                answer.add(candidate.get(i));
                visited[candidate.get(i).i][candidate.get(i).j] = true;
            } else {
                break;
            }
        }

        // 찾기
        while (answer.size() < n) {
            // 한번에 총 n개의 숫자를 확인하는데, 숫자들의 i값이 각자 다를 수 있음.
            // 같을 경우 visited 다 체크
            candidate = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // i값 찾기
                for (int i = n-1; i >= 0; i--) {
                    if (!visited[i][j]) {
                        candidate.add(new Point(i, j, array[i][j]));
                        break;
                    }
                }
            }
            // 최대값 찾기
            Collections.sort(candidate, (o1, o2) -> {
                if (o2.num > o1.num) {
                    return 1;
                }
                return -1;
            });
            answer.add(candidate.get(0));
            visited[candidate.get(0).i][candidate.get(0).j] = true;
            for (int k = 1; k < candidate.size(); k++) {
                if (answer.size() == n) {
                    break;
                }
                if (candidate.get(k).num == candidate.get(k - 1).num) {
                    answer.add(candidate.get(k));
                    visited[candidate.get(k).i][candidate.get(k).j] = true;
                } else {
                    break;
                }
            }
        }
        System.out.println(answer.get(n-1).num);
    }

    static class Point {
        int i;
        int j;
        long num;

        public Point(int i, int j, long num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
    }
}
