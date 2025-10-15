import java.util.*;

class Solution {
        
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        boolean[] myCards = new boolean[n+1];
        boolean[] pickCards = new boolean[n+1];
        
        int round = 1;
        int cardIndex = n/3;
        for (int i = 0; i < n/3; i++) {
            myCards[cards[i]] = true;
        }
        while (true) {
            // 0. 라운드 시작
            if (cardIndex == n) {
                break;
            }
            pickCards[cards[cardIndex++]] = true;
            pickCards[cards[cardIndex++]] = true;
            // 1. 가지고 있는 카드 중 N+1이 가능한지 확인
            boolean isOk = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && myCards[i] && myCards[j] && i + j== n+1) {
                        isOk = true;
                        myCards[i] = false;
                        myCards[j] = false;
                        break;
                    }
                }
                if (isOk) {
                    break;
                }
            }
            if (isOk) {
                round++;
                continue;
            }
            // 2. 1개를 구매
            if (coin > 0) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (myCards[i] && pickCards[j] && i + j == n+1) {
                            isOk = true;
                            myCards[i] = false;
                            pickCards[j] = false;
                            coin--;
                            break;
                        }
                    }
                    if (isOk) break;
                }
            }
            if (isOk) {
                round++;
                continue;  
            }
            // 3. 2개를 구매
            if (coin > 1) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (i != j && pickCards[i] && pickCards[j] && i + j == n+1) {
                            isOk = true;
                            pickCards[i] = false;
                            pickCards[j] = false;
                            coin -= 2;
                            break;
                        }
                    }
                    if (isOk) {
                        break;
                    }
                }
            }
            if (isOk) {
                round++;
                continue;  
            } 
            // 4. 라운드 끝
            break;
        }
        return round;
    } 
}

// 카드 뭉치: 1~n 개(n은 6의 배수)
// 동전: coin 개 -> 카드랑 교환
// 1. 카드 뭉치에서 n/3장을 뽑아 가짐
// 2. 각 라운드 시작할 때 카드 2장 뽑음. 뭉치에 남은 카드 없으면 게임 종료
// 2-1. 뽑은 카드는 버리거나 동전을 써서 가질 수 있음
// 3. 카드에 적힌 수의 합이 n+1이 되도록 카드 두장 내서 다음 라운드 진행
// 3-1. 카드 두장 낼 수 없으면 게임 종료
// 최대 라운드까지 진행

// coin 두개 소모할지 말지
// 일단 무조건 다음 단계로 갈 카드는 사야함.
// 그런데 아닌것도 사야되나?
// 최대한 지금 



// 뭉치: 5, 9, 8, 12, 11, 4
// 내거: 3 6 7 2
// 뽑은거: 1 10
// 1라운드
// 10구매 coin 3
// 뭉치: 5, 9, 8, 12, 11, 4
// 내거: 6 7 2
// 뽑은거: 1 10(씀)
// 2라운드
// 내거로 씀 coin 3
// 뭉치: 8, 12, 11, 4
// 내거: 2
// 뽑은거: 1 5 9
// 3라운드
// 뭉치: 11, 4
// 내거: 2
// 뽑은거: 1 5 9 8 12
// 내거로도 안되고 뭉치로도 안됨. 그럼 뽑은거에서 사자
// 12랑 1 2개 구매 coin 1
// 내거: 2
// 뽑은거: 5 9 8
// 4라운드
// 뭉치: 0
// 내거: 2
// 뽑은거: 5 9 8 11 4
// 하나 구매
// coin 0