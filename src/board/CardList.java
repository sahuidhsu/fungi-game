package board;

import cards.Card;

import java.util.ArrayList;

public class CardList{
    private static ArrayList<cards.Card> cList;
    public CardList() {
        cList = new ArrayList<Card>();
    }
    public void add(cards.Card card) {
        cList.add(card);
    }
    public int size() {
        return cList.size();
    }
    public Card getElementAt(int position) {
        return cList.get(position);
    }
    public static Card removeCardAt(int position) {
        return cList.remove(position);
    }
}