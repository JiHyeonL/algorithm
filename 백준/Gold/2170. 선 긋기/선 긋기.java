import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n;
    public static Line[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new Line[n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            lines[i] = new Line(Long.parseLong(temp[0]), Long.parseLong(temp[1]));
        }
        Arrays.sort(lines, (o1, o2) -> Long.compare(o1.start, o2.start));

        long answer = 0;
        long start = lines[0].start;
        long end = lines[0].end;
        for (Line line : lines) {
            if (start <= line.start && end >= line.start) {
                end = Math.max(line.end, end);
            } else {
                answer += end - start;
                start = line.start;
                end = line.end;
            }
        }
        answer += end - start;
        System.out.println(answer);
    }

    static class Line {
        long start;
        long end;

        public Line(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
