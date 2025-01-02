import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int m;
    public static int x;
    public static int y;
    public static int k;
    public static int[][] map;
    public static int[] commands;
    // 동서북남 순서
    public static int[] dx = {0, 0, 0, -1, 1};
    public static int[] dy = {0, 1, -1, 0, 0};
    public static int[] dice = {0, 0, 0, 0, 0, 0, 0}; // 인덱스 1 ~ 6까지 사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        x = Integer.parseInt(temp[2]);
        y = Integer.parseInt(temp[3]);
        k = Integer.parseInt(temp[4]);
        map = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        temp = br.readLine().split(" ");
        commands = new int[k];
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(temp[i]);
        }

        for (int command : commands) {
            // 1. 커맨드에 따라 주사위 위치 이동
            int nx = x + dx[command];
            int ny = y + dy[command];
            if (inRange(nx, ny)) {
                x = nx;
                y = ny;
                moveDown(command);
                // 2. 도착한 칸이 0이면 복사
                if (map[nx][ny] == 0) {
                    map[nx][ny] = dice[6];
                } else {
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                System.out.println(dice[1]);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static void moveDown(int command) {
        int temp = dice[1];
        if (command == 1) { // 동
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }
        if (command == 2) { // 서
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }
        if (command == 3) { // 북
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        if (command == 4) { // 님
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }
}
