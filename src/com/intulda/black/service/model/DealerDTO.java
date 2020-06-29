package com.intulda.black.service.model;

import com.intulda.black.service.dao.BlackjackDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DealerDTO {
    private List<String> cards;
    private List<String> cardDeck;
    private CardDTO cardDTO;
    private int money;
    private String score;

    public DealerDTO() {
        this.cards = new LinkedList<>();
        this.score = "0";
    }

    /**
     * 딜러가 카드를 상황조건에 따라 유저에게 주는 함수
     * @param kind
     * @return
     */
    public List<String> giveCard(String kind) {
        List<String> drawCards = null;
        if(Rule.FIRST_DRAW_CARD.equals(kind)) {
            drawCards = new LinkedList<>();
            for(int i=0; i<2; i++) {
                drawCards.add(this.cardDeck.get(i));
                this.cardDeck.remove(i);
            }
        } else if(Rule.SECOND_DRAW_CARD.equals(kind)) {
            drawCards = new LinkedList<>();
            drawCards.add(this.cardDeck.get(0));
            this.cardDeck.remove(0);
        }
        return drawCards;
    }

    /**
     * 새 카드팩 새로 저장
     * @param cardDTO
     */
    public void setCardDeck(List<String> cardDTO) {
        this.cardDeck = cardDTO;
    }

    /**
     * 유저와 공통적으로 셔플 된 카드 세팅
     */
    public void setCard(List<String> takeCards) {
        BlackjackDAO blackjackDAO = BlackjackDAO.getInstance();
        this.cardDTO = blackjackDAO.getCardDTO();
        takeCards.stream()
                .forEach(x -> this.cards.add(x));
        this.cardDTO.openCard(this.cards, this);
    }

    /**
     * 카드 섞기
     */
    public void shuffle() {
        Collections.shuffle(this.cardDeck);
    }

    public int getMoney() {
        return money;
    }

    public void cardReset(){
        this.cards = new LinkedList<>();
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    /**
     * 유저의 돈 10배로 세팅 ( 유저먼저 세팅되어야 한다 )
     */
    public void setMoney() {
        BlackjackDAO blackjackDAO = BlackjackDAO.getInstance();
        this.money = blackjackDAO.getUserDTO().getMoney() * 10;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

