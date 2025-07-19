import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static int n;
    private static int k;
    private static int[] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        point = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            point[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(point);
        List<Integer> pointSet = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (pointSet.isEmpty() || pointSet.get(pointSet.size() - 1) != point[i]) {
                pointSet.add(point[i]);
            }
        }
        Length[] betweenLength = new Length[pointSet.size() - 1];
        for (int i = 0; i < pointSet.size() - 1; i++) {
            betweenLength[i] = new Length(Math.abs(pointSet.get(i) - pointSet.get(i + 1)),
                    pointSet.get(i), pointSet.get(i + 1));
        }
        Arrays.sort(betweenLength, (o1, o2) -> {
            if (o1.length < o2.length) {
                return 1;
            } else if (o1.length == o2.length) {
                return 0;
            } else {
                return -1;
            }
        });
        Map<Integer, Boolean> flagMap = new HashMap<>();
        for (int point : pointSet) {
            flagMap.put(point, false);
        }
        for (int i = 0; i < betweenLength.length; i++) {
            if (k == 1) {
                break;
            }
            k--;
            flagMap.put(betweenLength[i].start, true);
        }

        int answer = 0;
        int startIndex = 0;
        int index = 0;
        while (index < pointSet.size()) {
            if (flagMap.get(pointSet.get(index)) || index == pointSet.size() - 1) {
                answer += pointSet.get(index) - pointSet.get(startIndex);
                startIndex = index + 1;
                index = startIndex;
            } else {
                index++;
            }
        }
        System.out.println(answer);
    }

    static class Length {
        int length;
        int start;
        int end;

        public Length(int length, int start, int end) {
            this.length = length;
            this.start = start;
            this.end = end;
        }
    }
}
