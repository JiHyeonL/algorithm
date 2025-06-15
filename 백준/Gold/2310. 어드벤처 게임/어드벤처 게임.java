import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static Room[] rooms;
    private static String answer;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            rooms = new Room[n + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = String.valueOf(st.nextToken().charAt(0));
                int cost = Integer.parseInt(st.nextToken());
                List<Integer> nextR = new ArrayList<>();
                int roomNum = 0;
                while ((roomNum = Integer.parseInt(st.nextToken())) != 0) {
                    nextR.add(roomNum);
                }
                rooms[i] = new Room(type, cost, nextR);
            }
            visited = new boolean[n + 1];
            answer = "No";
            canGo(1, 0);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void canGo(int room, int cost) {
        if (room == n) {
            answer = "Yes";
            return;
        }

        for (int nextRoom : rooms[room].nextRooms) {
            if (!visited[nextRoom]) {
                int newCost = cost;
                if (rooms[nextRoom].type.equals("L") && newCost < rooms[nextRoom].cost) {
                    newCost = rooms[nextRoom].cost;
                }
                if (rooms[nextRoom].type.equals("T")) {
                    if (newCost < rooms[nextRoom].cost) {
                        continue;
                    }
                    newCost -= rooms[nextRoom].cost;
                }
                visited[nextRoom] = true;
                canGo(nextRoom, newCost);
                visited[nextRoom] = false;
            }
        }
    }

    static class Room {
        String type;
        int cost;
        List<Integer> nextRooms;

        public Room(String type, int cost, List<Integer> nextRooms) {
            this.type = type;
            this.cost = cost;
            this.nextRooms = nextRooms;
        }
    }
}
