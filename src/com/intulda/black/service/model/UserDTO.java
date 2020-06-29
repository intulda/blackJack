package com.intulda.black.service.model;

import com.intulda.black.service.dao.BlackjackDAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDTO {
    private List<String> cards;
    private CardDTO cardDTO;
    private int money;
    private String score;

    public UserDTO() {
        this.cards = new ArrayList<>();
        this.score = "0";
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCard(List<String> takeCards) {
        BlackjackDAO blackjackDAO = BlackjackDAO.getInstance();
        this.cardDTO = blackjackDAO.getCardDTO();
        takeCards.stream()
                .forEach(x -> this.cards.add(x));
        this.cardDTO.openCard(this.cards, this);
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void cardReset(){
        this.cards = new LinkedList<>();
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
