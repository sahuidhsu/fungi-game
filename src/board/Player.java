package board;

import cards.Card;
import board.*;
import cards.CardType;
import cards.Pan;

import java.util.ArrayList;

public class Player {
    private Hand h;
    private Display d;
    private static int score;
    private int handlimit;
    private int sticks;

    public Player() {
        h = new Hand();
        d = new Display();
        d.add(new Pan());
        score = 0;
        handlimit = 8;
        sticks = 0;
    }
    public static int getScore() {
        return score;
    }
    public int getHandLimit() {
        return handlimit;
    }
    public int getStickNumber() {
        return sticks;
    }
    public void addSticks(int stick) {
        sticks += stick;
    }
    public void removeSticks(int stick) {
        sticks -= stick;
    }
    public Hand getHand() {
        return h;
    }
    public Display getDisplay() {
        return d;
    }
    public void addCardtoHand(Card c) {
        h.add(c);
    }
    public void addCardtoDisplay(Card c) {
        d.add(c);
    }

    public Boolean takeCardFromTheForest(int position) {
        if (getHand().size()+1 > getHandLimit()) {
            return false;
        }
        Card drawnCard = Board.getForest().removeCardAt(1);
        if (drawnCard.getType().equals(CardType.BASKET)) {
            addCardtoDisplay(drawnCard);
            handlimit += 2;
            return true;
        }
        else if (drawnCard.getType().equals(CardType.STICK)) {
            addSticks(1);
            return true;
        }
        else if (position >= 3 && position <= 8) {
            if (getStickNumber() >= position-2) {
                removeSticks(position - 2);
                addCardtoHand(drawnCard);
                return true;
            }
            else return false;
        }
        else if (position == 1 || position == 2) {
            addCardtoHand(drawnCard);
            return true;
        }
        else return false;
    }
    public Boolean takeFromDecay() {
        return true;
    }
    public Boolean cookMushrooms(ArrayList<Card> cardList) {
        return true;
    }
    public Boolean sellMushrooms(String cardName, int number) {
        return true;
    }
    public Boolean putPanDown() {
        return true;
    }
}
