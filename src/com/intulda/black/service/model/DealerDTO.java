package com.intulda.black.service.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerDTO {
    private List<String> cardDTO;

    /**
     * 딜러가 카드를 상황조건에 따라 유저에게 주는 함수
     * @param kind
     * @return
     */
    public List<String> giveCard(String kind) {
        List<String> cards = null;
        if(RuleDTO.FIRST_DRAW_CARD.equals(kind)) {
            cards = new ArrayList<>();
            for(int i=0; i<2; i++) {
                cards.add(this.cardDTO.get(i));
                this.cardDTO.remove(i);
            }
        } else if(RuleDTO.SECOND_DRAW_CARD.equals(kind)) {
            cards = new ArrayList<>();
            cards.add(this.cardDTO.get(0));
            this.cardDTO.remove(0);
        }
        return cards;
    }

    /**
     * 새 카드팩 새로 저장
     * @param cardDTO
     */
    public void setCardDeck(List<String> cardDTO) {
        this.cardDTO = cardDTO;
    }

    /**
     * 유저와 공통적으로 셔플 된 카드 세팅
     * @param message
     */
    public void setCard(String message) {
        this.giveCard(message);
    }

    public void shuffle() {
        Collections.shuffle(this.cardDTO);
    }
}

