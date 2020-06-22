package com.intulda.black.service.model;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private List<String> cardDTO;
    private int money;

    public UserDTO() {
        this.cardDTO = new ArrayList<>();
    }

    public List<String> getCard() {
        return cardDTO;
    }

    public void setCard(List<String> cardDTO) {
        cardDTO.stream()
                .filter(x -> this.cardDTO.add(x));
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
