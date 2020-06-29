package com.intulda.black.service.model;

import com.intulda.black.service.dao.BlackjackDAO;

import java.util.Arrays;
import java.util.List;

/**
 * 룰 모아두는 VO
 * @author kimbogeun
 * @create 2020.06.05
 */
public class Rule {

    public static final String FIRST_DRAW_CARD = "FIRST_DRAW_CARD";
    public static final String SECOND_DRAW_CARD = "SECOND_DRAW_CARD";
    public static final List<String> FIRST_BLACKJACK_CHECK = Arrays.asList("10", "J", "Q", "K", "A");
    public static final List<String> SECOND_BLACKJACK_CHECK = Arrays.asList("10", "J", "Q", "K");
    public static final String FIRST_SCORE_DIFF = "FIRST_SCORE_DIFF";
    public static final String USER_BLACKJACK_WIN = "USER_BLACKJACK_WIN";
    public static final String USER_WIN = "USER_WIN";
    public static final String DEALER_WIN = "DEALER_WIN";
    public static final String PUSH = "PUSH";
    public static final int MAX_CARD_COUNT = 6;
    public static final int MAX_DEALER_SCORE = 17;
    public static final String SCORE_DIFF = "SCORE_DIFF";
    public static final String BLACK_JACK = "BLACK_JACK";
    public static final String STAND_OFF = "STAND OFF";

    private int battingMoney = 0;

    public int getBattingMoney() {
        return battingMoney;
    }

    public void setBattingMoney(int battingMoney) {
        this.battingMoney = battingMoney;
    }
}
