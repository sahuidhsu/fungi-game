package board;

import java.util.ArrayList;

public class CardList{
    private ArrayList<cards.Card> cList;
    public CardList() {
        cList = new ArrayList<>();
    }

    public void add(cards.Card card) {
        cList.add(card);
    }
    public int size() {
        return cList.size();
    }
    public cards.Card getElementAt(int position) {
        return cList.get(position);
    }
    public void removeCardAt(int position) {
        cList.remove(position);
    }
}
