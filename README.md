# algorithm
Baekjoon Online Judge [BaekjoonHub](https://github.com/BaekjoonHub/BaekjoonHub) 에 의해 자동으로 생성되는 레포지토리입니다.

[자바로 백준 풀 때 유의점](https://nahwasa.com/entry/%EC%9E%90%EB%B0%94%EB%A1%9C-%EB%B0%B1%EC%A4%80-%ED%92%80-%EB%95%8C%EC%9D%98-%ED%8C%81-%EB%B0%8F-%EC%A3%BC%EC%9D%98%EC%A0%90-boj-java)  
<details>
<summary>자바 입출력 예시</summary>

```
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

___
## 🎉 알고리즘 정리

### 백트래킹
노드 유망성 판단 -> 유망하지 않다면 부모 노드로 돌아가 다른 자식 노드 찾는다.  
- 노드 방문 순서는 이미 정해져있다고 생각. (for문을 사용하니까 다음에는 어떤 노드를 방문해야 하는지 생각 안해도 됨)
- 중복 조건이나 에 따라 기본 백트래킹 알고리즘을 수정
> dfs(재귀) 응용. 특징은 하나의 재귀가 끝날 때 visit을 다시 false로 되돌려 준다.

### 재귀
1. 함수의 정의
    - func(int n, int r, int c) 
2. base condition(언제 재귀가 끝나는가)
    - n = 0일때 return 0;
3. 재귀식
    - (r,c)가 1번 사각형일 때 return func(n-1,r,c)
    - (r,c)가 2번 사각형일 때 return half*half + func(n-1,r,c-half)
> [[바킹독의 실전 알고리즘] 0x0B강 - 재귀](https://www.youtube.com/watch?v=8vDDJm5EewM)
