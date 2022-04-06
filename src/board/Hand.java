package board;

import cards.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> handList;
    public void add(Card card) {
        handList.add(card);
    }
    public int size() {
        return handList.size();
    }
    public Card getElementAt(int position) {
        return handList.get(position);
    }
    public Card removeElement(int position) {
        return  handList.remove(position);
    }
}
