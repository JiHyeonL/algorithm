import java.util.*;

class Solution {
    
    public final int[] discountRates = {10, 20, 30, 40};
    public int emoticonsLength;
    public int[] emoticonsPrice;
    public List<List<Emoticon>> emoticonComb = new ArrayList<>();
    public int emoticonPlusCount = 0;
    public int emoticonPurchase = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        emoticonsLength = emoticons.length;
        emoticonsPrice = emoticons;
        makeEmoticonComb(0, new ArrayList<>());
        
        for (List<Emoticon> emoticonInfo : emoticonComb) {
            calculateResult(users, emoticonInfo);
        }
        int[] answer = {emoticonPlusCount, emoticonPurchase};
        return answer;
    }
    
    private void calculateResult(int[][] users, List<Emoticon> emoticons) {
        int tempPlusCount = 0;
        int tempPurchase = 0;
        for (int[] user : users) {
            int userPurchase = 0;
            for (Emoticon emoticon : emoticons) {
                if (user[0] <= emoticon.getDiscountRate()) {
                    userPurchase += emoticonsPrice[emoticon.getIndex()] * (100 - emoticon.getDiscountRate()) / 100;
                }
            }
            if (userPurchase >= user[1]) {
                tempPlusCount++;
            } else {
                tempPurchase += userPurchase;
            }
        }
        if (tempPlusCount > emoticonPlusCount) {
            emoticonPlusCount = tempPlusCount;
            emoticonPurchase = tempPurchase;
        }
        else if (tempPlusCount == emoticonPlusCount && tempPurchase > emoticonPurchase) {
            emoticonPurchase = tempPurchase;
        }
    }
    
    
    private void makeEmoticonComb(int count, List<Integer> emoticonDR) {
        if (count == emoticonsLength) {
            List<Emoticon> em = new ArrayList<>();
            for (int i = 0; i < emoticonsLength; i++) {
                em.add(new Emoticon(i, emoticonDR.get(i)));
            }
            emoticonComb.add(em);
            return;
        }
        
        for (int discountRate : discountRates) {
            emoticonDR.add(discountRate);
            makeEmoticonComb(count + 1, emoticonDR);
            emoticonDR.remove(emoticonDR.size()-1);
        }
    }
    
    class Emoticon {
        int index;
        int discountRate;
        
        public Emoticon(int index, int discountRate) {
            this.index = index;
            this.discountRate = discountRate;
        }
        
        public int getIndex() {
            return index;
        }
        
        public int getDiscountRate() {
            return discountRate;
        }
        
        public String toString() {
            return index + ": " + discountRate;
        }
    }
}

// 1. 이모티콘 플러스 가입자 +
// 2. 판매액 +
// -> n명에게 m개 이모티콘 할인 판매
// 1. 이모티콘을 사거나
// 2. 플러스 가입(구매 비용 합이 사용자 가격을 넘으면)
// 결론: 최대한 이모티콘 구매 비용을 높게 잡기(할인률 낮추기)
// 대신 적어도 하나의 이모티콘 할인률이 사용자의 비율보다는 높아야 함.(그래야 구매하니까)

// 이모티콘
// 하나 당 4가지 * 7개 = 4^7 개 조합
// 각 조합에 대해 결과 계산.