package board;

import cards.*;

import java.util.ArrayList;

public class Board {
    private static CardPile forestCardsPile;
    private static CardList forest;
    private static ArrayList<Card> decayPile;
    public static void initialisePiles() {
        forestCardsPile = new CardPile();
        forest = new CardList();
        decayPile = new ArrayList<>();
    }
    public static void setUpCards() {
        for (int i = 0; i < 10; i++) {
            forestCardsPile.addCard(new HoneyFungus(CardType.DAYMUSHROOM));
        }
        for (int i = 0; i < 8; i++) {
            forestCardsPile.addCard(new TreeEar(CardType.DAYMUSHROOM));
        }
        for (int i = 0; i < 6; i++) {
            forestCardsPile.addCard(new LawyersWig(CardType.DAYMUSHROOM));
        }
        for (int i = 0; i < 5; i++) {
            forestCardsPile.addCard(new Shiitake(CardType.DAYMUSHROOM));
            forestCardsPile.addCard(new HenOfWoods(CardType.DAYMUSHROOM));
            forestCardsPile.addCard(new Basket());
        }
        for (int i = 0; i < 4; i++) {
            forestCardsPile.addCard(new BirchBolete(CardType.DAYMUSHROOM));
            forestCardsPile.addCard(new Porcini(CardType.DAYMUSHROOM));
            forestCardsPile.addCard(new Chanterelle(CardType.DAYMUSHROOM));
        }
        for (int i = 0; i < 3; i++) {
            forestCardsPile.addCard(new Morel(CardType.DAYMUSHROOM));
        }
        forestCardsPile.addCard(new HoneyFungus(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new TreeEar(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new LawyersWig(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new Shiitake(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new HenOfWoods(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new BirchBolete(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new Porcini(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new Chanterelle(CardType.NIGHTMUSHROOM));
        forestCardsPile.addCard(new Butter());
        forestCardsPile.addCard(new Cider());
        for (int i = 0; i < 13; i++) {
            forestCardsPile.addCard(new Pan());
        }
    }
    public static CardPile getForestCardsPile() {
        return forestCardsPile;
    }
    public static CardList getForest() {
        return forest;
    }
    public static ArrayList<Card> getDecayPile() {
        return decayPile;
    }
    public static void updateDecayPile() {
    }
}
