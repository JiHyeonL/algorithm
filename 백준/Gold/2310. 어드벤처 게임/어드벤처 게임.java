import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static int n;
    private static Map<Integer, List<Integer>> nextRooms;
    private static Room[] rooms;
    private static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            rooms = new Room[n + 1];
            nextRooms = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                String[] temp = br.readLine().split(" ");
                int cost = Integer.parseInt(temp[1]);
                List<Integer> nextR = new ArrayList<>();
                for (int j = 2; j < temp.length - 1; j++) {
                    nextR.add(Integer.parseInt(temp[j]));
                }
                rooms[i] = new Room(temp[0], cost, nextR);
                nextRooms.put(i, nextR);
            }

            answer = "No";
            if (rooms[1].type.equals("L")) {
                canGo(1, new boolean[n + 1], rooms[1].cost);
            }
            if (rooms[1].type.equals("E")) {
                canGo(1, new boolean[n + 1], 0);
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void canGo(int room, boolean[] visited, int cost) {
        if (room == n) {
            answer = "Yes";
            return;
        }

        visited[room] = true;
        for (int nextRoom : nextRooms.get(room)) {
            if (!visited[nextRoom]) {
                if (rooms[nextRoom].type.equals("L") && cost < rooms[nextRoom].cost) {
                    cost = rooms[nextRoom].cost;
                }
                if (rooms[nextRoom].type.equals("T")) {
                    if (cost < rooms[nextRoom].cost) {
                        continue;
                    }
                    cost -= rooms[nextRoom].cost;
                }
                visited[nextRoom] = true;
                canGo(nextRoom, visited, cost);
            }
        }
    }

    static class Room {
        String type;
        int cost;
        List<Integer> nextRooms = new ArrayList<>();

        public Room(String type, int cost, List<Integer> nextRooms) {
            this.type = type;
            this.cost = cost;
            this.nextRooms = nextRooms;
        }
    }
}
