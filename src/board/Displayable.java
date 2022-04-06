package board;

import cards.Card;

public interface Displayable {
    void add(Card card);
    int size();
    Card getElementAt(int position);
    Card removeElement(int position);
}
