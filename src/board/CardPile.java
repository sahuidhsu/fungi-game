package board;

import cards.Card;

import java.util.Stack;

public class CardPile {
    private static Stack<Card> cPile;
    public CardPile(){
        cPile = new Stack<Card>();
    }
    public void addCard(Card card){
        cPile.push(card);
    }
    public static Card drawCard(){
        return cPile.pop();
    }
    public void shufflePile(){
        for (int i = 0; i < cPile.size(); i++) {
            int r = (int) (Math.random() * cPile.size());
            Card temp = cPile.get(i);
            cPile.set(i, cPile.get(r));
            cPile.set(r, temp);
        }
    }
    public int pileSize(){
        return cPile.size();
    }
    public boolean isEmpty(){
        return cPile.isEmpty();
    }
}