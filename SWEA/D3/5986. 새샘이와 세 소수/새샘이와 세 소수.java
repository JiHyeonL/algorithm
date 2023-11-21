/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static final int MAX = 999;
    public static boolean[] notPrime = new boolean[MAX+1];
    public static List<Integer> primeNumber = new ArrayList<>();

    public static int n;

    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/


        notPrime[0] = notPrime[1] = true;
        findPrime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int test_case = 1; test_case <= T; test_case++)
        {
            n = sc.nextInt();
            System.out.printf("#%d %d\n", test_case, findAnswer(0,0,0));
        }
    }

    public static void findPrime() {
        // 소수 조건인 1, 자기 자신 제외 나누어 떨어지는게 있는지 확인
        for (int i = 2; i <= (int)Math.sqrt(MAX); i++) {
            if (!notPrime[i]) {  // 소수이면
                for (int j = i * i; j <= MAX; j += i) {
                    notPrime[j] = true;  // 그 소수의 배수는 소수가 아님
                }
            }
        }

        for (int i = 2; i <= MAX; i++) {
            if (!notPrime[i]) {
                primeNumber.add(i);
            }
        }
    }

    public static int findAnswer(int level, int sum, int start) {
        int count = 0;
        if (level == 3) {
            if (sum == n) {
                return 1;
            }
            return 0;
        }
        for (int i = start; i < primeNumber.size(); i++) {
            if (sum + primeNumber.get(i) > n) {
                break;
            }
            count += findAnswer(level+1, sum+primeNumber.get(i), i);
        }

        return count;
    }
}