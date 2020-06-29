package com.intulda.black.service.dao;

import com.intulda.black.main.Blackjack;
import com.intulda.black.service.model.CardDTO;
import com.intulda.black.service.model.DealerDTO;
import com.intulda.black.service.model.Rule;
import com.intulda.black.service.model.UserDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * BLACK JACK DAO CLASS
 * @author kimbogeun
 */
public class BlackjackDAO {

    private Scanner scanner;
    private static BlackjackDAO BLACKJACK_DAO = null;
    private DealerDTO dealerDTO;
    private UserDTO userDTO;
    private CardDTO cardDTO;
    private Rule ruleDTO;

    private BlackjackDAO() {
        cardDTO = new CardDTO();
        dealerDTO = new DealerDTO();
        userDTO = new UserDTO();
        ruleDTO = new Rule();
        scanner = new Scanner(System.in);
    }

    public static BlackjackDAO getInstance() {
        if(BLACKJACK_DAO == null) {
            BlackjackDAO.BLACKJACK_DAO = new BlackjackDAO();
        }
        return BlackjackDAO.BLACKJACK_DAO;
    }

    public void getMessage(String message) {
        System.out.println(message);
    }

    public void setScore(Object object) {
        if(this.blackjackCardCheck(object)) {
            if(object instanceof DealerDTO) {
                this.dealerDTO.setScore(Rule.BLACK_JACK);
            } else {
                this.userDTO.setScore(Rule.BLACK_JACK);
            }
        } else {
            if(object instanceof UserDTO) {
                this.userDTO.setScore(scoreCalc(this.userDTO.getCards(), this.userDTO));
                this.getMessage("현재 카드 점수 : " + this.userDTO.getScore());
                this.getMessage("배팅 금액     : $" + this.ruleDTO.getBattingMoney());
            }
        }
    }

    /**
     * 초기 블랙잭 체크여부
     * @param object
     * @return
     */
    public boolean blackjackCardCheck(Object object) {
        boolean check = false;
        for(String card : Rule.FIRST_BLACKJACK_CHECK) {
            if(object instanceof DealerDTO) {
                int idx = this.dealerDTO.getCards().get(1).indexOf(card);
                if(idx > -1) {
                    int secondIdx = this.dealerDTO.getCards().get(1).indexOf("A");
                    if(secondIdx > -1) {
                        for(String second : Rule.SECOND_BLACKJACK_CHECK) {
                            if(this.dealerDTO.getCards().get(0).indexOf(second) > -1) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        if(this.dealerDTO.getCards().get(0).indexOf("A") > -1) {
                            check = true;
                            break;
                        }
                    }
                }
            } else {
                int idx = this.userDTO.getCards().get(1).indexOf(card);
                if(idx > -1) {
                    int secondIdx = this.userDTO.getCards().get(1).indexOf("A");
                    if(secondIdx > -1) {
                        for(String second : Rule.SECOND_BLACKJACK_CHECK) {
                            if(this.userDTO.getCards().get(0).indexOf(second) > -1) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        if(this.userDTO.getCards().get(0).indexOf("A") > -1) {
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        return check;
    }

    /**
     * 점수 비교 함수
     */
    public boolean diffScore(String str) {
        boolean check = true;
        String dealerScore = this.dealerDTO.getScore();
        String userScore = this.userDTO.getScore();
        if(dealerScore != null && userScore != null) {
            if(Rule.FIRST_SCORE_DIFF.equals(str)) {
                if(Rule.BLACK_JACK.equals(dealerScore) && Rule.BLACK_JACK.equals(userScore)) {
                    this.getMessage(Rule.STAND_OFF);
                    check = false;
                } else if(Rule.BLACK_JACK.equals(dealerScore)) {
                    this.getMessage(Rule.BLACK_JACK);
                    this.getMessage("DEALER WIN");
                    check = false;
                    this.moneyCalc(Rule.DEALER_WIN);
                } else if(Rule.BLACK_JACK.equals(userScore)) {
                    this.getMessage(Rule.BLACK_JACK);
                    this.getMessage("USER WIN");
                    check = false;
                    this.moneyCalc(Rule.USER_BLACKJACK_WIN);
                }
            } else if(Rule.SCORE_DIFF.equals(str)) {
                this.getMessage("=============");
                if(Integer.parseInt(userScore) > 21) {
                    this.getMessage("USER BUST!");
                    this.getMessage("DEALER WIN");
                    this.getMessage("=============");
                    this.moneyCalc(Rule.DEALER_WIN);
                    return false;
                } else if(Integer.parseInt(dealerScore) > 21) {
                    this.getMessage("DEALER BUST");
                    this.getMessage("USER WIN");
                    this.getMessage("=============");
                    this.moneyCalc(Rule.USER_WIN);
                    return false;
                } else if(Integer.parseInt(userScore) > 21 && Integer.parseInt(dealerScore) > 21) {
                    this.getMessage("DEALER AND USER BUST");
                    this.getMessage("DEALER WIN");
                    this.getMessage("=============");
                    this.moneyCalc(Rule.DEALER_WIN);
                    return false;
                }

                if(Integer.parseInt(dealerScore) > Integer.parseInt(userScore)) {
                    this.getMessage("DEALER WIN");
                    this.moneyCalc(Rule.DEALER_WIN);
                } else if (Integer.parseInt(dealerScore) == Integer.parseInt(userScore)){
                    this.getMessage("PUSH");
                    this.moneyCalc(Rule.PUSH);
                } else {
                    this.getMessage("USER WIN");
                    this.moneyCalc(Rule.USER_WIN);
                }
                this.getMessage("=============");
                check = false;
            }
        }
        return check;
    }

    public void moneyCalc(String result) {
        if(Rule.USER_BLACKJACK_WIN.equals(result)) {
            this.dealerDTO.setMoney(this.dealerDTO.getMoney() - this.ruleDTO.getBattingMoney() < 0 ? 0 : this.dealerDTO.getMoney() - this.ruleDTO.getBattingMoney());
            this.userDTO.setMoney(this.userDTO.getMoney() + ((int)(this.ruleDTO.getBattingMoney() * 0.25)+this.ruleDTO.getBattingMoney()));
            this.ruleDTO.setBattingMoney(0);
        } else if(Rule.USER_WIN.equals(result)) {
            this.dealerDTO.setMoney(this.dealerDTO.getMoney() - this.ruleDTO.getBattingMoney() < 0 ? 0 : this.dealerDTO.getMoney() - this.ruleDTO.getBattingMoney());
            this.userDTO.setMoney(this.userDTO.getMoney() + this.ruleDTO.getBattingMoney());
            this.ruleDTO.setBattingMoney(0);
        } else if(Rule.DEALER_WIN.equals(result)) {
            this.dealerDTO.setMoney(this.dealerDTO.getMoney() + this.ruleDTO.getBattingMoney());
            this.userDTO.setMoney(this.userDTO.getMoney() - this.ruleDTO.getBattingMoney() < 0 ? 0 : this.userDTO.getMoney() - this.ruleDTO.getBattingMoney());
            this.ruleDTO.setBattingMoney(0);
        } else if(Rule.PUSH.equals(result)) {
            this.ruleDTO.setBattingMoney(0);
        }
    }

    /**
     * 유저 입력정보 받기
     */
    public void getInputs() {
        while(true) {
            if(!this.diffScore(Rule.FIRST_SCORE_DIFF)){
                break;
            }
            System.out.println("Stand/\"No more\" : 'S', \"Hit\" : 'H'");
            String choice = scanner.nextLine().toUpperCase();
            if("S".equals(choice)) {
                this.dealerDTO.setScore(scoreCalc(this.dealerDTO.getCards(), this.dealerDTO));
                this.userDTO.setScore(scoreCalc(this.userDTO.getCards(), this.userDTO));
                this.getMessage("DEALER : " + this.dealerDTO.getScore());
                this.getMessage("USER   : " + this.userDTO.getScore());
                this.diffScore(Rule.SCORE_DIFF);
                this.getMessage("남은 금액 : $" + this.userDTO.getMoney());
                break;
            } else if("H".equals(choice)) {
                this.moreCard();
                this.dealerDTO.setScore(scoreCalc(this.dealerDTO.getCards(), this.dealerDTO));
                this.userDTO.setScore(scoreCalc(this.userDTO.getCards(), this.userDTO));
                this.getMessage("현재 카드 점수 : " + this.userDTO.getScore());
                this.getMessage("배팅 금액     : " + this.ruleDTO.getBattingMoney());
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }
        }

    }
    /**
     * 점수 계산
     */
    public String scoreCalc(List<String> cards, Object object) {
        int sum = 0;
        for(int i=0; i<cards.size(); i++) {
            sum += changeStringToInt(cards.get(i), object);
        }
        return String.valueOf(sum);
    }

    /**
     * 카드 드로우
     */
    public void moreCard() {
        if(Integer.parseInt(this.dealerDTO.getScore()) < Rule.MAX_DEALER_SCORE) {
            if(this.dealerDTO.getCards().size() < Rule.MAX_CARD_COUNT) {
                this.dealerDTO.setCard(this.dealerDTO.giveCard(Rule.SECOND_DRAW_CARD));
            } else {
                this.getMessage("최대로 받을 수 있는 카드 수 입니다.");
            }
        } else {
            this.cardDTO.openCard(this.dealerDTO.getCards(), this.dealerDTO);
        }
        if(this.userDTO.getCards().size() < Rule.MAX_CARD_COUNT) {
            this.userDTO.setCard(this.dealerDTO.giveCard(Rule.SECOND_DRAW_CARD));
        } else {
            this.cardDTO.openCard(this.userDTO.getCards(), this.userDTO);
            this.getMessage("최대로 받을 수 있는 카드 수 입니다.");
        }
    }

    /**
     * 카드 점수만 가져오기
     * @param card
     * @return
     */
    public int changeStringToInt(String card, Object object) {
        int lastIndex = card.indexOf("]");
        String value = card.substring(lastIndex+1);
        int result = 0;
        if(!(value.charAt(0) >= 48 && value.charAt(0) <= 57)) {
            if("A".equals(value)) {
                result = aceCardScoreCheck(object);
            } else if("J".equals(value) || "Q".equals(value) || "K".equals(value)) {
                result = 10;
            }
        } else {
            result = Integer.parseInt(value);
        }
        return result;
    }

    /**
     * A카드 1점 11점 체크
     * @param object
     * @return
     */
    private int aceCardScoreCheck(Object object) {
        if(object instanceof UserDTO) {
            return Integer.parseInt(((UserDTO) object).getScore()) > 10 ? 1 : 11;
        } else {
            return Integer.parseInt(((DealerDTO) object).getScore()) > 10 ? 1 : 11;
        }
    }

    public void setUserBatting() {
        while(true) {
            System.out.println("베팅할 금액을 입력해주세요(2 ~ 500)");
            int inputMoney = scanner.nextInt();
            if(inputMoney <= 500 && inputMoney >= 2) {
                this.ruleDTO.setBattingMoney(inputMoney);
                break;
            } else {
                System.out.println("범위에서 벗어난 금액 입니다. 다시 입력해주세요.");
            }
        }
        scanner.nextLine();
    }

    public boolean resetInformation() {
        if(this.userDTO.getMoney() == 0) {
            return false;
        }
        this.dealerDTO.setScore("0");
        this.userDTO.setScore("0");
        this.dealerDTO.cardReset();
        this.userDTO.cardReset();
        return true;
    }

    public DealerDTO getDealerDTO() {
        return dealerDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public CardDTO getCardDTO() {
        return cardDTO;
    }

    public Scanner getScanner() {
        return scanner;
    }
}