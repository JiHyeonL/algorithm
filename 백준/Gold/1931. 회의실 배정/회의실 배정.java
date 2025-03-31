import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static int n;
    public static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            meetings[i] = new Meeting(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        PriorityQueue<Meeting> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.end < o2.end) {
                return -1;
            } else if (o1.end == o2.end) {
                return Integer.compare(o1.start, o2.start);
            }
            return 1;
        });
        for (int i = 0; i < n; i++) {
            pq.add(meetings[i]);
        }
        Stack<Meeting> answer = new Stack<>();
        while (!pq.isEmpty()) {
            Meeting now = pq.poll();
            if (answer.isEmpty()) {
                answer.add(now);
                continue;
            }
            Meeting before = answer.peek();
            if (before.end <= now.start) {
                answer.add(now);
            }
        }
        System.out.println(answer.size());
    }

    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
