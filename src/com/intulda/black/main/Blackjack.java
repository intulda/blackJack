package com.intulda.black.main;

import com.intulda.black.service.dao.BlackjackDAO;
import com.intulda.black.service.model.Rule;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Blackjack {

    private BufferedReader bufferedReader;
    private BlackjackDAO blackjackDAO;

    public Blackjack() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        blackjackDAO = BlackjackDAO.getInstance();

        blackjackDAO.getUserDTO().setMoney(1000);
        blackjackDAO.getDealerDTO().setMoney();
    }

    public void play() {
        //TODO : 기본 준비
        blackjackDAO.getDealerDTO().setCardDeck(blackjackDAO.getCardDTO().getCard());
        //TODO : 배팅
        /**
         * 2 ~ 500달러
         */
        blackjackDAO.setUserBatting();
        //TODO : 셔플
        blackjackDAO.getDealerDTO().shuffle();
        //TODO : 딜
        blackjackDAO.getDealerDTO().setCard(blackjackDAO.getDealerDTO().giveCard(Rule.FIRST_DRAW_CARD));
        blackjackDAO.getUserDTO().setCard(blackjackDAO.getDealerDTO().giveCard(Rule.FIRST_DRAW_CARD));

        blackjackDAO.setScore(blackjackDAO.getDealerDTO());
        blackjackDAO.setScore(blackjackDAO.getUserDTO());
        //TODO : 블랙잭인지 아닌지
        /**
         * 딜러의 오픈된 카드가 10, J, Q, K, A 인 경우, 딜러는 덮어둔 카드를 혼자 확인하고 블랙잭이 만들어졌는지 여부를 플레이어에게 알려줍니다.
         * 딜러가 블랙잭이면, 블랙잭이 없는 모든 플레이어의 모든 베팅을 수금해갑니다.
         * 플레이어가 블랙잭이면, 보상금액을 3:2로 지급. 즉 베팅금액의 2.5배를 돌려받게 됩니다 ("BLACKJACK PAYS 3 to 2"). 예를 들어, 10달러를 베팅했다면 블랙잭은 원금 10 + 보상 15 = 25달러를 받습니다.
         * 딜러와 플레이어가 함께 블랙잭이면, 스탠드오프가 되어 그 플레이어는 자기 칩을 도로 가져갑니다. ("Stand-Off"/"Tie"/"Push")
         */
        //TODO
        /**
         * 플레이어 먼저, 오른쪽부터 왼쪽 순서로 (시계방향) 플레이 합니다.
         *
         * 스탠드 Stand/"No More" 더이상 카드를 받지 않음.
         *
         * 히트 Hit 카드를 한 장 더 받음. 버스트 될 때까지 여러 번 히트 가능. Face-Up 게임에선 테이블을 두드리고, Face-Down 게임에선 자신의 몸쪽을 향해 테이블을 검지로 긁습니다.
         *
         * 버스트 Bust 총합 21 초과. 플레이어가 버스트 됐을 경우 딜러는 모든 베팅 금액을 가져가고 다음 플레이어 차례로 넘어갑니다. 딜러가 버스트 되면 자동적으로 버스트 되지 않은 플레이어의 승리입니다.
         * ​
         */
        blackjackDAO.getInputs();
        this.restarting();
    }

    public void restarting() {
        if(blackjackDAO.resetInformation()){
            System.out.println("1.다음 게임 시작, 2.종료");
            int choice = blackjackDAO.getScanner().nextInt();
            if(choice == 1) {
                this.play();
            } else {
                System.out.println("이용해주셔서 감사합니다.");
            }
        } else {
            System.out.println("돈을 전부 소진하셨습니다. 이용해주셔서 감사합니다.");
        }
    }
}
