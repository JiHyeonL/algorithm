import java.util.*;

class Solution {
    
    public List<Integer> queue = new ArrayList<>();
    public long goal;
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long queue1Sum = 0;
        for (long q1 : queue1) {
            queue1Sum += q1;
        }
        long queue2Sum = 0;
        for (long q2 : queue2) {
            queue2Sum += q2;
        }
        goal = (queue1Sum + queue2Sum) / 2;
        if (goal * 2 == queue1Sum + queue2Sum) {
            for (int q1 : queue1) {
                queue.add(q1);
            }
            for (int q2 : queue2) {
                queue.add(q2);
            }
            answer = calculate(queue1Sum, 0, queue1.length);
        }
        return answer;
    }
    
    public int calculate(long q1Sum, int q1Index, int q2Index) {
        int count = 0;
        while (q1Index < q2Index) {
            if (q1Sum < goal) {
                q1Sum += queue.get(q2Index);
                q2Index++;
                q2Index = q2Index % queue.size();
                count++;
            }
            else if (q1Sum == goal) {
                return count;
            } else {
                q1Sum -= queue.get(q1Index);
                q1Index++;
                q1Index = q1Index % queue.size();
                count++;
            }
        }
        return -1;
    }
}

