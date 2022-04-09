package board;

import cards.Card;
import board.*;
import cards.CardType;
import cards.Pan;
import cards.Stick;

import javax.swing.*;
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
        if (c.getType().equals(CardType.BASKET)) {
            addCardtoDisplay(c);
            handlimit += 2;
        } else if (c.getType().equals(CardType.STICK)) {
            addSticks(1);
        } else {
            h.add(c);
        }
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
        System.out.println(getHandLimit());
        System.out.println(getHand().size());
        int repeatNum = Board.getDecayPile().size();
        if (getHand().size()+Board.getDecayPile().size() <= getHandLimit()) {
            for (int i = 0; i < repeatNum; i++) {
                if (Board.getDecayPile().get(0).getType().equals(CardType.BASKET)) {
                    addCardtoDisplay(Board.getDecayPile().remove(0));
                    handlimit += 2;
                }
                else if (Board.getDecayPile().get(0).getType().equals(CardType.STICK)) {
                    addSticks(1);
                    Board.getDecayPile().remove(0);
                }
                else {
                    addCardtoHand(Board.getDecayPile().remove(0));
                }
            }
            return true;
        }
        if (getHand().size() > getHandLimit()) {
            return false;
        }
        if (Board.getDecayPile().size() < 4) {
            Boolean validBasket = false;
            for (int i = 0; i < Board.getDecayPile().size(); i++) {
                if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                    validBasket = true;
                }
            }
            if (validBasket) {
                for (int i = 0; i < repeatNum; i++) {
                    if (Board.getDecayPile().get(0).getType().equals(CardType.BASKET)) {
                        addCardtoDisplay(Board.getDecayPile().remove(0));
                        handlimit += 2;
                    }
                    else if (Board.getDecayPile().get(0).getType().equals(CardType.STICK)) {
                        addSticks(1);
                        Board.getDecayPile().remove(0);
                    }
                    else {
                        addCardtoHand(Board.getDecayPile().remove(0));
                    }
                }
                return true;
            }
            else return false;
        }
        else {
            int numberOfBaskets = 0;
            for (int i = 0; i < repeatNum; i++) {
                if (Board.getDecayPile().get(i).getType().equals(CardType.BASKET)) {
                    numberOfBaskets++;
                }
            }
            if ((numberOfBaskets >= 2 && getHand().size() == getHandLimit()) || (numberOfBaskets >= 1 && getHand().size() < getHandLimit())) {
                for (int i = 0; i < repeatNum; i++) {
                    if (Board.getDecayPile().get(0).getType().equals(CardType.BASKET)) {
                        addCardtoDisplay(Board.getDecayPile().remove(0));
                        handlimit += 2;
                    }
                    else if (Board.getDecayPile().get(0).getType().equals(CardType.STICK)) {
                        addSticks(1);
                        Board.getDecayPile().remove(0);
                    }
                    else {
                        addCardtoHand(Board.getDecayPile().remove(0));
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
        Boolean hasPan = false;
        for (int i = 0; i < getHand().size(); i++) {
            if (getHand().getElementAt(i).getType().equals(CardType.PAN)) {
                hasPan = true;
                break;
            }
        }
        if (hasPan) {
            int counter = 0;
            while  (counter < getHand().size()) {
                if (getHand().getElementAt(counter).getType().equals(CardType.PAN)) {
                    getDisplay().add(getHand().removeElement(counter));
                    return true;
                }
                counter++;
            }
        }
        return false;
    }
}
