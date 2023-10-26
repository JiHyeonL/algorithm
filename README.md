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

## 🎉 시간복잡도
- 시간복잡도 계산했을 때 5억정도면 약 1초라고 생각
- 풀이 생각 -> 시간복잡도 생각 -> 코드 구현
- System.out.println 남발 금지!!
  - `StringBuilder`나 `ArrayList`에 값을 저장한 뒤 한꺼번에 출력하는 것이 좋다.
- `StringBuiler` 사용할 때 append 안에서 문자열 연산 금지!! (기껏 StringBuilder 사용했는데 문자열을 새로 생성하게 된다.)
  - ex) sb.append("a" + "\n") -> sb.append("a").append("\n");

## 🎉 알고리즘 정리

### ✅ 배열 vs 리스트
- 배열을 `기본타입`으로 선언한 경우 `초기값`을 가지고 있다.
  - `int[] arr = new int[5];`
  - `System.out.println(arr[0]);`
  - `>> 0`
- ArrayList는 초기값이 null이다.
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

### ✅ 해시
- 삽입, 검색, 제거, 교체 모두 O(1)에 가능
- HashMap<key,value> 선언
- put(key,value) & get(key) & remove(key)
  - put의 경우 이전에 동일한 key에 매핑된 값이 있었다면 그 값을 반환해준다.
- containsKey(key) : key 있는지 확인
- 해시맵.keySet() : 해시맵의 모든 key 반환
  - key값을 통해 value를 찾는 과정에서 시간이 많이 소요됨
  - 대신 entrySet()을 사용하는게 좋다고 한다.
- Map이 비어있는지 체크할 때 `.isEmpty()`보다 `.size() == 0` 으로 체크하는 것이 좋다.

### ✅ 정렬
> 정렬 대상.sort(정렬할 객체, 정렬 기준)

- 정렬 대상
  - Arrays.sort : `배열` 정렬
  - Collections.sort : `리스트` 정렬 
- 정렬할 객체의 데이터 타입이 기본형이 아닐 경우 정렬 기준이 없으면 에러 발생.
  - 

- 정렬 기준  
  - 람다식 사용
    - 예) ```(o1,o2) -> {return o2-o1;}``` : 내림차순 
  - 정렬 기준 한개(Comparable)
    - `o1.compareTo(o2)` : 문자열 비교. o1 < o2 이면 -1
  - 정렬 기준 한개 이상(comparator)
    - `compare(o1,o2)` : 문자열 비교. o1 < o2 이면 -1 

### ✅ 스택
> LIFO : 데이터 이동 공간이 하나밖에 없어서 가장 늦게 들어온 데이터가 가장 빨리 빠져나간다.
- `new Stack<>()` 으로 선언
- push(value) & peek() & pop()
  - pop은 스택의 마지막 요소를 제거하고 해당 값을 `반환`한다.
  - 스택이 비어있을 때 pop을 하면 에러 발생!!
    - peek의 경우 null 반환

### ✅ 큐
> FIFO : 데이터 이동 공간이 두개. 한 쪽은 삭제 연산만, 반대 쪽은 삽입 연산이 이루어진다.    
> 이런 성질을 이용하면, Queue 인터페이스만 제공하는 자바에서는 LinkedList(단방향 연결 리스트)로 큐를 사용할 수 있다.
- `new LinkedList<>()` 로 선언
- offer(value) & peek() & poll()
  - peek & poll -> 첫번째 값 반환 & 삭제 후 반환
  - 큐가 비어있을 때 poll()은 `null`을 반환
- ❗**중요**❗ 큐 전체 값을 탐색하거나 출력할 때 for(원소 : 큐) 하면 이상한 값 나옴.
    - `while(큐.iterator().hasNext()) { print(큐.poll()); }` 와 같이 Iterator<자료형> 형식으로 탐색해야 함.

### ✅ 우선순위큐
> 우선순위가 높은 데이터가 먼저 반환되는 큐
- `new PriorityQueue<>()`
- offer(value) & poll() & peek()
- 우선순위를 기본(오름차순)과 다르게 정하고 싶다면 람다식 이용해서 정렬해주면 된다.


### ✅ 이진검색트리
> 이진 트리 : 각 노드의 자식이 2개 이하인 트리  
> 이진 검색 트리 : 왼쪽 서브트리의 모든 값은 부모의 값보다 작고, 오른쪽 서브트리의 모든 값은 부모의 값보다 큰 이진 트리  
- 삽입, 제거, 검색, 교체 모두 O(lgN)에 가능.
- 원소가 크기 순으로 정렬되어 있기 때문에 정렬을 해야 하는 경우 사용.(해시의 경우 정렬이 필요할 시 비효율적)
- 중복 불가(힙과 다른 부분)
1. Set 인터페이스 `new Treeset<>();` 으로 구현
   - add(value) & remove(value)
   - 반환
     - first() : 최소값
     - last() : 최대값
     - pollFirst() & pollLast() : 최대, 최소값 반환 후 삭제
     - higher(value) : value보다 큰 값 중 최소값(value 바로 위)
     - lower(value) : value보다 작은 값 중 최대값(value 바로 아래)
     - floor(value) & ceiling(value) : value나 value 바로 아래 or 위 값
   - iterator() : TreeSet을 반복자로 만들어줌.
     - ex) `Iterator it = TreeSet.iterator();`
     - `while(it.hasNext()) {iter.next();}` 를 사용하면 객체를 하나씩 가져올 수 있다.
2. `TreeMap`으로 구현
   - put(key, value) & remove(key)
   - 반환
     - get(key) : key에 해당하는 value
     - TreeSet의 모든 반환 메소드를 갖고있다.
       - `Treeset메소드명+(Entry or Key)(K key or void)`
       - pollFirstEntry & pollLastEntry는 Entry만 가능.



### ✅ 정렬
- `앞 원소 <= 뒤 원소`를 비교할 때, 두 원소가 같을 경우 `앞 원소`를 먼저 사용하는 것이 좋다.
  - stable sort(우선순위가 같은 원소들은 원래의 순서를 따라가는 정렬) 원리.
- *Comparison Sort*
  - `Merge Sort` : 리스트의 원소를 분해한 뒤 다시 합하면서 정렬하는 방법(재귀 사용)
    - O(NlgN) 보장.
  - `Quick Sort` : 대부분의 정렬 라이브러리에서 사용하는 방법. pivot보다 작거나 같은 값은 왼쪽, 큰 값은 오른쪽에 옮김.
    - 정렬을 위해 추가적인 공간이 필요 없기 때문에(in-place sort) 속도가 빠르다.
    - 그러나 라이브러리 사용이 불가능해서 직접 정렬을 구현해야 한다면 Quick Sort는 비추천.
      - pivot이 제일 작은 값이거나 제일 큰 값이라면 최악의 경우 O(N^2) 시간이 발생할 수 있다.
      - 그래서 직접 구현해야 한다면 Merge Sort 추천.
- *Non-Comparison Sort*
  - `Counting Sort` : 모든 숫자의 등장 개수를 센 뒤 등장 개수 만큼 숫자 나열
    - 간단하지만 숫자 등장 횟수를 저장할 공간이 필요하다는 단점이 있다.
    - ArrayList보다 int[]를 추천.(int는 초기값이 0이기 때문에 초기화 할 필요가 없다)

  
