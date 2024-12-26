import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int n;
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        array = new int[n+1];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(temp[i]);
        }


        List<Integer> list = new ArrayList<>();
        list.add(array[0]);
        for (int i = 1; i < n; i++) {
            if (list.get(list.size() - 1) < array[i]) {
                list.add(array[i]);
            }
            else {
                int left = 0;
                int right = list.size() - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) >= array[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;

                    }
                }
                list.set(right, array[i]);
            }
        }
        System.out.println(list.size());
    }
}
