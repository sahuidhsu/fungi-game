package board;

import cards.Card;

import java.util.ArrayList;

public class Display {
    private ArrayList<Card> displayList;
    public void add(Card card) {
        displayList.add(card);
    }
    public int size() {
        return displayList.size();
    }
    public Card getElementAt(int position) {
        return displayList.get(position);
    }
    public Card removeElement(int position) {
        return displayList.remove(position);
    }
}
