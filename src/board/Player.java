package board;

import cards.Card;

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
        switch (position) {
            case 1, 2:
        }
        return true;
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
