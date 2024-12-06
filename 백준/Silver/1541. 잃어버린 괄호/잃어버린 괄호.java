import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split("");
        List<String> numbers = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        for (String t : temp) {
            if (t.equals("-") || t.equals("+")) {
                numbers.add(num.toString());
                numbers.add(t);
                num = new StringBuilder();
            } else {
                num.append(t);
            }
        }
        numbers.add(num.toString());
        
        int answer = 0;
        int i = 0;
        while (i < numbers.size()) {
            if (numbers.get(i).equals("-")) {
                int sumNum = 0;
                int j = i + 1;
                while(j < numbers.size()) {
                    // 다음 -가 나왔으니 끝냄.
                    if (numbers.get(j).equals("-")) {
                        break;
                    }
                    if (numbers.get(j).equals("+")) {
                        sumNum += Integer.parseInt(numbers.get(j+1));
                        j += 2;
                    } else {
                        sumNum += Integer.parseInt(numbers.get(j));
                        j += 1;
                    }
                }
                answer -= sumNum;
                i = j;
            }
            else if (numbers.get(i).equals("+")) {
                answer += Integer.parseInt(numbers.get(i+1));
                i += 2;
            } else {
                if (i == 0) {
                    answer += Integer.parseInt(numbers.get(i));
                }
                i++;
            }
        }
        System.out.println(answer);
    }

}
