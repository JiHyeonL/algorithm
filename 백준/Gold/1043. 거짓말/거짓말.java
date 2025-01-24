import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static int n;
    public static int m;
    public static Set<Integer> truths;
    public static List<List<Integer>> parties;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        int truthLength = Integer.parseInt(temp[0]);
        if (truthLength == 0) {
            System.out.println(m);
            return;
        }
        truths = new HashSet<>();
        for (int i = 0; i < truthLength; i++) {
            truths.add(Integer.parseInt(temp[1+i]));
        }
        parties = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            List<Integer> people = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(temp[0]); j++) {
                people.add(Integer.parseInt(temp[1+j]));
            }
            parties.add(people);
        }
        System.out.println(calculate());
    }

    private static int calculate() {
        visited = new boolean[m];
        while (!truths.isEmpty()) {
            checkTruth();
        }

        int count = 0;
        for (boolean visit : visited) {
            if (!visit) {
                count++;
            }
        }
        return count;
    }

    private static void checkTruth() {
        Set<Integer> newTruth = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (visited[i]) {
                continue;
            }
            List<Integer> party = parties.get(i);
            for (int truth : truths) {
                if (party.contains(truth)) {
                    newTruth.addAll(getKnowTruth(party));
                    visited[i] = true;
                    break;
                }
            }
        }
        truths = new HashSet<>(newTruth);
    }

    private static List<Integer> getKnowTruth(List<Integer> people) {
        List<Integer> truth = new ArrayList<>();
        for (int p : people) {
            if (truths.contains(p)) {
                continue;
            }
            truth.add(p);
        }
        return truth;
    }
}
