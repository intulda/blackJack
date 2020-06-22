package com.intulda.black.service.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDTO {
    private final List<String> PATTERNS;
    private final List<String> NUMBERS;
    private List<String> card;

    public CardDTO() {
        this.PATTERNS = Arrays.asList("Clover", "Heart", "Spade", "Diamond");
        this.NUMBERS = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K");

        for ( String pattern : this.PATTERNS) {
            for( String number : this.NUMBERS ) {
                card.add("["+pattern+"]"+number);
            }
        }
    }

    public List<String> getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "CARD=" + this.card +
                '}';
    }
}
