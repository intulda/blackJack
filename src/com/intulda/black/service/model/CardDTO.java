package com.intulda.black.service.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Card DTO
 * @author kimbogeun
 */
public class CardDTO {
    private final List<String> PATTERNS;
    private final List<String> NUMBERS;
    private List<String> card;

    public CardDTO() {
        this.PATTERNS = Arrays.asList("Clover", "Heart", "Spade", "Diamond");
        this.NUMBERS = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K");
        this.card = new ArrayList<>();
        for ( String pattern : this.PATTERNS) {
            for( String number : this.NUMBERS ) {
                card.add("["+pattern+"]"+number);
            }
        }
    }

    public List<String> getCard() {
        return card;
    }

    /**
     * 카드 오픈
     * @param cards
     * @param object
     */
    public void openCard(List<String> cards, Object object) {
        int size = cards.size();
        String player;
        if(object instanceof DealerDTO) {
            System.out.println("=========== Dealer Cards ===========");
            player = "Dealer";
        } else {
            System.out.println("=========== User   Cards ===========");
            player = "User";
        }

        for(int i=0; i<size; i++) {
            if(i == 0 && "Dealer".equals(player)) {
                System.out.print("[ 뒷면 ]" + " ");
            } else {
                System.out.print(cards.get(i) + " ");
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "CARD=" + this.card +
                '}';
    }
}
