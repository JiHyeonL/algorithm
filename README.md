[자바로 백준 풀 때 유의점](https://nahwasa.com/entry/%EC%9E%90%EB%B0%94%EB%A1%9C-%EB%B0%B1%EC%A4%80-%ED%92%80-%EB%95%8C%EC%9D%98-%ED%8C%81-%EB%B0%8F-%EC%A3%BC%EC%9D%98%EC%A0%90-boj-java)  
<details>
<summary>자바 입출력 예시</summary>

``` JAVA
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
public void solution() throws Exception {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int n = Integer.parseInt(br.readLine());
for (int i = 0; i < n; i++) {
StringTokenizer st = new StringTokenizer(br.readLine());
int s = Integer.parseInt(st.nextToken());

            for (int j = 0; j < s; j++) {
                int data = Integer.parseInt(st.nextToken());
                System.out.println(data);
            }
        }
        System.out.println("test");
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}
```
</details>
<br/>

## 🎉 알고리즘 정리

### ✅ 백트래킹
> `dfs(재귀)` 중 불필요한 부분을 건너뛴다. 특징은 하나의 재귀가 끝날 때 visit을 다시 false로 되돌려 준다.
노드 유망성 판단 -> 유망하지 않다면 부모 노드로 돌아가 다른 자식 노드 찾는다.  
1. base condition
   - if (depth == M) { print(); return; }
2. 재귀식
``` JAVA
for(int i = k~N) { 
   if(!visited[i]) {
      visited[i] = true;
      재귀식
      visited[i] = false;
   } 
}
```
- 노드 방문 순서는 이미 정해져있다고 생각. (for문을 사용하니까 다음에는 어떤 노드를 방문해야 하는지 생각 안해도 됨)
- 백트래킹 조건에 따라 재귀식을 수정
- 만약 조건이 중복 숫자 불가라면 숫자를 `고정` 시킨 뒤 생각해 보자.  

### ✅ 재귀
1. 함수의 정의
    - func(int n, int r, int c) 
2. base condition(언제 재귀가 끝나는가)
    - n = 0일때 return 0;
3. 재귀식
    - (r,c)가 1번 사각형일 때 return func(n-1,r,c)
    - (r,c)가 2번 사각형일 때 return half*half + func(n-1,r,c-half)   

[[바킹독의 실전 알고리즘] 0x0B강 - 재귀](https://www.youtube.com/watch?v=8vDDJm5EewM)

### ✅ DP  
> 동일한 계산 과정이 반복될 때 사용한다. ex) 경우의 수, 조합, 조건 내 최소값 구하기  
> 점화식을 세우고 어느 부분에서 for문 or 재귀를 돌릴지 생각해보자.  
> [예시 - N을 1로 만드는 횟수](https://www.acmicpc.net/problem/1463)
1. 테이블 정의하기
   - D[i] = i를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값  
     <br/>
2. 점화식 찾기
   - D[12] = ?
   - 3으로 나누거나 (D[12] = D[4] + 1)
   - 2로 나누거나 (D[12] = D[6] + 1)
   - 1을 빼거나 (D[12] = D[11] + 1)
   - -> D[12] = min(D[4] + 1, D[6] + 1, D[11] + 1)  
   <br/>
3. 초기값 정하기
   - D[1] = 0  

`예시`
```JAVA
public static void DP() {
    if (해결한 문제) {
        return 값 재활용
    }
}   if (초기 조건) {
        return 초기값;
    }   
    return 점화식
```

[[바킹독의 실전 알고리즘] 0x10강 - 다이나믹 프로그래밍](https://www.youtube.com/watch?v=5leTtB3PQu0)  

### ✅ 조합
> n개 중 r개를 뽑는다. -> 중복은 count 하지 않음
1. 점화식 세우기 (파스칼 삼각형 생각)  
   - n+1 C r+1 = n C r + n C r+1    
   - n C r = n-1 C r-1 + n-1 C r  
     <br/>
2. 초기값 정하기  
   - n C n = 1  
   - n C 0 = 1  
