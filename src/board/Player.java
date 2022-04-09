package board;

import cards.Card;
import board.*;
import cards.CardType;
import cards.Pan;
import cards.Stick;

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
        for (int i = 0; i < stick; i++) {
            d.add(new Stick());
        }
    }
    public void removeSticks(int stick) {
        sticks -= stick;
        int alreadyRemovedSticks = 0;
        while (alreadyRemovedSticks != stick) {
            for (int i = 0; i < d.size(); i++) {
                if (d.getElementAt(i).getType().equals(CardType.STICK)) {
                    d.removeElement(i);
                    alreadyRemovedSticks++;
                    break;
                }
            }

        }
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
        Card drawnCard = Board.getForest().getElementAt(8-position);
        if ((getHand().size() == getHandLimit()) && !(drawnCard.getType() == CardType.BASKET || drawnCard.getType() == CardType.STICK))
            return false;
        else if (position >= 3 && position <= 8) {
            if (getStickNumber() >= position - 2) {
                removeSticks(position - 2);
                if (drawnCard.getType().equals(CardType.BASKET)) {
                    addCardtoDisplay(drawnCard);
                    Board.getForest().removeCardAt(8-position);
                    handlimit += 2;
                    return true;
                } else if (drawnCard.getType().equals(CardType.STICK)) {
                    addSticks(1);
                    //addCardtoDisplay(drawnCard);
                    Board.getForest().removeCardAt(8-position);
                    return true;
                }
                else {
                    addCardtoHand(drawnCard);
                    return true;
                }
            } else return false;
        } else if (position == 1 || position == 2) {
            if (drawnCard.getType().equals(CardType.BASKET)) {
                addCardtoDisplay(drawnCard);
                handlimit += 2;
                Board.getForest().removeCardAt(8-position);
                return true;
            } else if (drawnCard.getType().equals(CardType.STICK)) {
                addSticks(1);
                addCardtoDisplay(drawnCard);
                Board.getForest().removeCardAt(8-position);
                return true;
            }
            else {
                addCardtoHand(drawnCard);
                Board.getForest().removeCardAt(8-position);
                return true;
            }
        }
        return false;
    }
    public Boolean takeFromDecay() {
        if (getHand().size()+Board.getDecayPile().size() <= getHandLimit()) {
            for (int i = 0; i < Board.getDecayPile().size(); i++) {
                if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                    addCardtoDisplay(Board.getDecayPile().remove(i));
                }
                else if (Board.getDecayPile().get(i).getType().equals(CardType.STICK)) {
                    addSticks(1);
                }
                else {
                    addCardtoHand(Board.getDecayPile().remove(i));
                }
            }
            return true;
        }
        if (Board.getDecayPile().size() < 4) {
            Boolean validBasket = false;
            for (int i = 0; i < Board.getDecayPile().size(); i++) {
                if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                    validBasket = true;
                }
            }
            if (validBasket) {
                for (int i = 0; i < Board.getDecayPile().size(); i++) {
                    if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                        addCardtoDisplay(Board.getDecayPile().remove(i));
                        handlimit += 2;
                    }
                    else if (Board.getDecayPile().get(i).getType().equals(CardType.STICK)) {
                        addSticks(1);
                    }
                    else {
                        addCardtoHand(Board.getDecayPile().remove(i));
                    }
                }
                return true;
            }
            else return false;
        }
        else {
            int numberOfBaskets = 0;
            for (int i = 0; i < Board.getDecayPile().size(); i++) {
                if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                    numberOfBaskets++;
                }
            }
            if (numberOfBaskets == 2) {
                for (int i = 0; i < Board.getDecayPile().size(); i++) {
                    if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                        addCardtoDisplay(Board.getDecayPile().remove(i));
                        handlimit += 2;
                    }
                    else if (Board.getDecayPile().get(i).getType().equals(CardType.STICK)) {
                        addSticks(1);
                    }
                    else {
                        addCardtoHand(Board.getDecayPile().remove(i));
                    }
                }
                return true;
            }
        }
        return false;
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
