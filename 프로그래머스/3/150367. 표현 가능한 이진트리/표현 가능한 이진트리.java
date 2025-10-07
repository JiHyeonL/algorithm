import java.util.*;

class Solution {
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = changeBinary(numbers[i]);
            if (isBinaryTree(binary)) {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    private boolean isBinaryTree(String binary) {
        if (binary.length() == 1) {
            return true;
        }
        int mid = binary.length() / 2;
        String left = binary.substring(0, mid);
        String right = binary.substring(mid + 1);
        if (binary.charAt(mid) == '0') {
            if (isAllZero(left) && isAllZero(right)) {
                return true;
            }
            return false;
        }
        return isBinaryTree(left) && isBinaryTree(right);
    } 
    
    private boolean isAllZero(String str) {
        return !str.contains("1");
    }
    
    private String changeBinary(long number) {
        StringBuilder binary = new StringBuilder();
        while (number > 0) {
            if (number % 2 == 0) {
                binary.append(0);
            } else {
                binary.append(1);
            }
            number /= 2;
        }
        int treeLength = 1;
        while (Math.pow(2, treeLength)-1 < binary.length()) {
            treeLength++;
        }
        int binaryLength = binary.length();
        for (int i = 0; i < Math.pow(2, treeLength)-1 - binaryLength; i++) {
            binary.append(0);
        }
        binary.reverse();
        return binary.toString();
    }
}

// 1. 빈 문자열 생성
// 2. 노드를 추가해서 포화 이진트리 만들기
// 3. 이진트리 노드를 왼쪽 -> 오른쪽 순서로 살펴보기
// 4. 살펴본 노드가 더미 노드라면 문자열 뒤에 0, 아니면 1 추가
// 5. 빈 문자열 2진수를 10진수로 변환

// 10 -> 2
// 예) 7
// 7 % 2 -> 1
// 3 % 2 -> 1
// 1 % 2 -> 1
// 111 -> 4 + 2 + 1


//          o
//         / \
//        o   *
// 101(짝수면 맨 앞에 0 붙이기)
// 왼쪽1 부모0 오른쪽1 -> 불가능
// 어케암? 자식 중에 1이 있으면 부모 0은 불가능

//       o
//     /  \
//    o    o
//   / \  / \
//  o   o o  o
// /\  /\ /\ /\
// o o oo oo oo
// 
// 21 0
// 10 1
// 5 0
// 2 1
// 1  0
// 2 + 8+ 32
// 101010 == 0101010

// 111
// 55 -> 1
// 27 -> 1
// 13 -> 1
// 6 -> 1
// 3 -> 0
// 1 -> 1
// 1(왼)1(부)0(오) 1(부) 1(왼)1(부)1(오)
// 0    1    2     3    4    5   6
// 1, 3, 5

// 1 또는 0