import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int[][] wheels = new int[5][8];
    public static int k;
    public static Move[] moves;
    public static int[] rotation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 4; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = Integer.parseInt(temp[j]);
            }
        }
        k = Integer.parseInt(br.readLine());
        moves = new Move[k];
        for (int i = 0; i < k; i++) {
            String[] temp = br.readLine().split(" ");
            moves[i] = new Move(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        for (Move move : moves) {
            rotation = new int[5];
            moveBfs(move);
            // 톱니바퀴 모두 돌리기
            rotate();
        }
        System.out.println(calculate());
    }

    private static void moveBfs(Move move) {
        // 1 = 시계, -1 = 반시계
        Queue<Move> queue = new LinkedList<>();
        boolean[] visited = new boolean[5];
        queue.add(move);
        visited[move.number] = true;
        rotation[move.number] = move.direction;

        while (!queue.isEmpty()) {
            Move target = queue.poll();
            int left = target.number - 1;
            //System.out.println(target.number);
            if (left >= 1 && !visited[left]) {   // 왼쪽 확인
                int direction = wheels[target.number][6] == wheels[left][2] ? 0 : target.direction * -1;
                queue.add(new Move(left, direction));
                visited[left] = true;
                rotation[left] = direction;
                //System.out.println("왼쪽: " + direction);
            }
            int right = target.number + 1;
            if (right <= 4 && !visited[right]) {   // 오른쪽 확인
                int direction = wheels[target.number][2] == wheels[right][6] ? 0 : target.direction * -1;
                queue.add(new Move(right, direction));
                visited[right] = true;
                rotation[right] = direction;
                //System.out.println("오른쪽: " + direction + ", " + wheels[target.number][2] + ", " + wheels[right][6]);
            }
        }
    }

    private static void rotate() {
        for (int i = 1; i <= 4; i++) {
            if (rotation[i] == 1) { // 시계
                int temp = wheels[i][7];
                for (int j = 7; j > 0; j--) {
                    wheels[i][j] = wheels[i][j - 1];
                }
                wheels[i][0] = temp;
            } else if (rotation[i] == -1) { // 반시계
                int temp = wheels[i][0];
                for (int j = 0; j < 7; j++) {
                    wheels[i][j] = wheels[i][j + 1];
                }
                wheels[i][7] = temp;
            }
        }
    }

    private static int calculate() {
        int answer = 0;
        for (int i = 1; i <= 4; i++) {
            if (wheels[i][0] == 1) {
                answer += (int) Math.pow(2, i - 1);
                //System.out.println("트루");
            }
        }
        return answer;
    }

    static class Move {
        int number;
        int direction;

        public Move(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }
    }
}
