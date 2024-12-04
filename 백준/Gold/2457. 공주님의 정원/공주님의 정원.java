import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/2457
public class Main {

    public static int n;
    public static List<Date> flower = new ArrayList<>();
    public static List<Integer> dateEnd = List.of(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            flower.add(new Date(temp));
        }
        // 조건 1: 피는 달, 일 조건 2: 지는 달, 일
        flower.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o2.end - o1.end;
            }
            return o1.start - o2.start;
        });
        System.out.println(greedy());
    }

    private static int greedy() {
        int count = 0;
        int start = 301;
        int end = 0;
        int index = 0;
        while (start <= 1130) {
            boolean nextFlower = false;
            for (int i = index; i < n; i++) {
                Date date = flower.get(i);
                // 시작일보다 나중에 피는 꽃이면 가망없음
                if (date.start > start) {
                    break;
                }
                // 시작일보다 이전에 피었다가 종료일 갱신
                if (date.end > end) {
                    end = date.end;
                    index = i + 1;
                    nextFlower = true;
                }
            }

            if (nextFlower) {
                start = end;
                count++;
            } else {
                break;
            }
        }

        if (end <= 1130) {
            return 0;
        }
        return count;
    }

    static class Date {
        int start;
        int end;

        public Date(String[] info) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].length() == 1) {
                    info[i] = "0" + info[i];
                }
            }
            this.start = Integer.parseInt(info[0] + info[1]);
            this.end = Integer.parseInt(info[2] + info[3]);;
        }
    }
}
