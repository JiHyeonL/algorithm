import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int n;
    public static int k;
    public static int p;
    public static String x;
    public static List<List<Integer>> number = List.of(
            List.of(1, 1, 1, 1, 1, 1, 0), // 0
            List.of(1, 0, 0, 0, 0, 1, 0), // 1
            List.of(1, 1, 0, 1, 1, 0, 1), // 2
            List.of(1, 1, 0, 0, 1, 1, 1), // 3
            List.of(1, 0, 1, 0, 0, 1, 1),
            List.of(0, 1, 1, 0, 1, 1, 1),
            List.of(0, 1, 1, 1, 1, 1, 1),
            List.of(1, 1, 0, 0, 0, 1, 0),
            List.of(1, 1, 1, 1, 1, 1, 1),
            List.of(1, 1, 1, 0, 1, 1, 1)  // 9
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        k = Integer.parseInt(temp[1]);
        p = Integer.parseInt(temp[2]);
        x = temp[3];

        // 1. n보다 k가 더 크면 앞에 0을 붙여야 함.
        List<Integer> intX = new ArrayList<>();
        for (int i = 0 ; i < k - x.length(); i++) {
            intX.add(0);
        }
        for (String x : x.split("")) {
            intX.add(Integer.parseInt(x));
        }

        // 2. 1층부터 n층까지 만들 수 있는 경우의 수 구하기
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // 자기 자신 제외
            if (i == Integer.parseInt(x)) {
                continue;
            }
            answer += canMake(i, new ArrayList<>(intX)) ? 1 : 0;
        }
        System.out.println(answer);
    }

    private static boolean canMake(int floor, List<Integer> target) {
        // floor도 k 자리수로 맞추기
        List<Integer> targetFloor = new ArrayList<>();
        String strFloor = String.valueOf(floor);
        for (int i = 0 ; i < target.size() - strFloor.length(); i++) {
            targetFloor.add(0);
        }
        for (String f : strFloor.split("")) {
            targetFloor.add(Integer.parseInt(f));
        }

        // 찾기
        int flipCount = 0;
        for (int i = 0; i < target.size(); i++) {
            if (targetFloor.get(i) == target.get(i)) {
                continue;
            }
            List<Integer> floorLed = new ArrayList<>(number.get(targetFloor.get(i)));
            List<Integer> targetLed = new ArrayList<>(number.get(target.get(i)));
            // led 하나씩 만들어 보기.
            for (int j = 0; j < 7; j++) {
                // led가 다르면 flip
                if (flipCount < p && floorLed.get(j) != targetLed.get(j)) {
                    targetLed.set(j, floorLed.get(j));  // flip
                    flipCount++;
                }
                if (flipCount == p && floorLed.get(j) != targetLed.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String toString(List<Integer> ints) {
        StringBuilder sb = new StringBuilder();
        for (int i : ints) {
            sb.append(String.valueOf(i));
        }
        return sb.toString();
    }
}
