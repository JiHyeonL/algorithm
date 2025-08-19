import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static int n;
    private static Child[] tree;
    private static int[] parent;
    private static List<Integer> path = new ArrayList<>();
    private static boolean[] visited;
    private static int end = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new Child[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] temp = br.readLine().split(" ");
            int temp0 = Integer.parseInt(temp[0]);
            tree[temp0] = new Child(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            if (tree[temp0].left != -1) {
                parent[tree[temp0].left] = temp0;
            }
            if (tree[temp0].right != -1) {
                parent[tree[temp0].right] = temp0;
            }
        }
        visited = new boolean[n + 1];
        visited[1] = true;
        findEnd(1);
        search(1);
        System.out.println(path.size() - 1);
    }

    private static void search(int node) {
        Child child = tree[node];
        path.add(node);
        if (child.left != -1 && !visited[child.left]) {
            visited[child.left] = true;
            search(child.left);
        } else if (child.right != -1 && !visited[child.right]) {
            visited[child.right] = true;
            search(child.right);
        } else if (node == end) {
            return;
        } else if (parent[node] != 0) {
            search(parent[node]);
        }
    }

    private static void findEnd(int node) {
        if (node == -1) {
            return;
        }
        findEnd(tree[node].left);
        end = node;
        findEnd(tree[node].right);
    }

    static class Child {
        int left;
        int right;

        public Child(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
