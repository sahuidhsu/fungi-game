package board;

import cards.Card;

import java.util.ArrayList;

public class Board {
    private static CardPile forestCardsPile() {
        return new CardPile();
    }
    private static CardList forest() {
        return new CardList();
    }
    private static ArrayList<Card> decayPile() {
        return new ArrayList<>();
    }
    public static void initialisePiles() {
        CardPile forestCardsPile = forestCardsPile();
    }
    public static void setUpCards() {
        ArrayList<Card> decayPile = decayPile();
    }
    public static CardPile getForestCardsPile() {
        return forestCardsPile();
    }
    public static CardList getForest() {
        return forest();
    }
    public static ArrayList<Card> getDecayPile() {
        return decayPile();
    }
    public static void updateDecayPile() {
    }
}
