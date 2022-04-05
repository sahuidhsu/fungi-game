package driver;

import java.util.ArrayList;
import java.io.Console;
import board.*;
import cards.Card;
import cards.CardType;

public class Game{
	private static Player p1, p2;
	
	public static void play() {
		boolean p1plays=true;
		Player currentPlayer;
		
		Board.initialisePiles();
		Board.setUpCards();
		Board.getForestCardsPile().shufflePile();
		
		//Populate forest	
		for (int i=0; i<8;i++) {			
			Board.getForest().add(Board.getForestCardsPile().drawCard());		
		}
		//Initialise players and populate player hands
		p1  = new Player(); currentPlayer=p1; p2 = new Player();
		p1.addCardtoHand(Board.getForestCardsPile().drawCard());p1.addCardtoHand(Board.getForestCardsPile().drawCard());p1.addCardtoHand(Board.getForestCardsPile().drawCard());
		p2.addCardtoHand(Board.getForestCardsPile().drawCard());p2.addCardtoHand(Board.getForestCardsPile().drawCard());p2.addCardtoHand(Board.getForestCardsPile().drawCard());	
				
		//Display board
		displayBoard();		
		while (Board.getForest().size()>0) {		
			if (p1plays) 
				System.out.println("\nPlayer 1, which of the following actions are you going to do?\n");
			else
				System.out.println("\nPlayer 2, which of the following actions are you going to do?\n");
			System.out.println("1. Take one card from the forest");
			System.out.println("2. Take all the cards out of the decay pile");
			System.out.println("3. Cook three or more identical types of mushrooms");
			System.out.println("4. Sell two or more identical types of mushrooms");
			System.out.println("5. Put down one pan\n");
			try { 
				Console keyboard = System.console();			
				int option = Integer.parseInt(keyboard.readLine("Enter the number for the action:"));
				boolean succesfullMove = false;
				switch(option) {
					case 1:
						int position = Integer.parseInt(keyboard.readLine("Enter the number of the card you want to take:"));
						if (currentPlayer.takeCardFromTheForest(position)) {
							if (Board.getForestCardsPile().pileSize()>0) {
								Board.getForest().add(Board.getForestCardsPile().drawCard());
							}
							succesfullMove=true;						
						}
						break;
					case 2:
						if (currentPlayer.takeFromDecay()) {
							succesfullMove=true;	
						}
						break;
					case 3:
						String cookMush = keyboard.readLine("What ingredients are you cooking? Type the position of the cards in your hand (use commas to separate):");
					    String[] splittedStringOfInts = cookMush.split(",");
						ArrayList<Card> cookingmushrooms = new ArrayList<Card>();
						for (int k=0;k<splittedStringOfInts.length;k++) {
							int inputInt=Integer.parseInt(splittedStringOfInts[k]);
							cookingmushrooms.add(currentPlayer.getHand().getElementAt(inputInt-1));
						}					
						if (currentPlayer.cookMushrooms(cookingmushrooms)) {
							succesfullMove=true;
						}
						break;
					case 4:
						String mushType = keyboard.readLine("What type of mushrooms are you willing to sell? Type mushroom name:");
						int mushNumber  = Integer.parseInt(keyboard.readLine("How many are willing to sell? Type number:"));
						if (currentPlayer.sellMushrooms(mushType,mushNumber)) {
							succesfullMove=true;
						}						
						break;
					case 5:
						if (currentPlayer.putPanDown()) {
							succesfullMove=true;
						}
						break;
				}			
				String prompt="TRY AGAIN";
				if (succesfullMove) {
					if (Board.getForest().size()>0) {
						Board.updateDecayPile();
					}
					if (Board.getForestCardsPile().pileSize()>0) {
						Board.getForest().add(Board.getForestCardsPile().drawCard());
					}
					displayBoard();
					p1plays=!p1plays;
					if (p1plays)
						currentPlayer=p1;
					else
						currentPlayer=p2;
					prompt="NEW MOVE";
				}			
				System.out.println();
				displayGameStatus();
				System.out.println("================================================================================================================");
				System.out.println("                                         "+prompt+"                                                             ");
				System.out.println("================================================================================================================");
			}
			catch(NumberFormatException e) {
				System.out.println("This is not the appropriate format. Avoid white spaces and characters when a number is expected.");
			}
		}
		if (p1.getScore()>p2.getScore()) {
			System.out.println("Player 1 wins");
		}
		else if (p2.getScore()>p1.getScore()) {
			System.out.println("Player 2 wins");
		}
		else {
			System.out.println("There was a tie");
		}			
	}
	
	public static void main (String [] args) {
		play();
	}
	
	private static void displayBoard() {
		displayGameStatus(); 
		System.out.print("\n");		
		System.out.println("Player 1 hand");

		printTxtDisplayable(p1.getHand());
		System.out.println("Player 1 display");
		printTxtDisplayable(p1.getDisplay());
		printTxtForest(Board.getForest());
		System.out.println("Player 2 display");
		printTxtDisplayable(p2.getDisplay());
		System.out.println("Player 2 hand");
		printTxtDisplayable(p2.getHand());
	}
	
	private static void displayGameStatus() {
		System.out.println("\nThere are "+Board.getForestCardsPile().pileSize()+" cards in the Forest pile");
		System.out.print("Decay pile: ");
		for (Card c : Board.getDecayPile()) {
			System.out.print(c.getName()+"| ");
		}
		System.out.println("\nPlayer 1: "+p1.getStickNumber()+" sticks, score: "+p1.getScore());
		System.out.println("Player 2: "+p2.getStickNumber()+" sticks, score: "+p2.getScore());
	}
	
	private static void printTxtDisplayable(Displayable d) {
		int index;
		printTxtCardTop(d.size());
		for (int i=0; i < d.size() ; i++) {
			printTxtCard(d.getElementAt(i));
		}
		System.out.print("\n");
		for (int j=0; j < d.size(); j++) {
			index=j+1;
			if (index<10) {
				System.out.print("|      ("+index+")       |");
			}
			else {
				System.out.print("|      ("+index+")      |");
			}
		}
		System.out.println();
		for (int k=0; k < d.size(); k++) {	
			if (d.getElementAt(k).getType().equals(CardType.NIGHTMUSHROOM)) {
				System.out.print("|      (N)       |");			}
			else {
				System.out.print("|                |");
			}			
		}
		printTxtCardBottom(d.size());
	}
	
	private static void printTxtForest(CardList cl) {
		int index;
		System.out.println("Forest");
		printTxtCardTop(cl.size());
		for (int i=0; i < cl.size(); i++) {
			printTxtCard(cl.getElementAt(i));
		}
		System.out.print("\n");
		for (int j=0; j < cl.size(); j++) {
			index= cl.size()-j;
			if (index<10) {
				System.out.print("|      ("+index+")       |");
			}
			else {
				System.out.print("|      ("+index+")      |");
			}	
		}
		System.out.println();
		for (int k=0; k < cl.size(); k++) {	
			if (cl.getElementAt(k).getType().equals(CardType.NIGHTMUSHROOM)) {
				System.out.print("|       (N)      |");			}
			else {
				System.out.print("|                |");
			}			
		}
		printTxtCardBottom(cl.size());
	}
	
	private static void printTxtCardTop(int cardNumber) {
		for (int i=0; i < cardNumber; i++) {
			System.out.print(" ---------------- ");
		}
		System.out.print("\n");
		for (int j=0; j<3;j++) {
			for (int k=0; k < cardNumber; k++) {
				System.out.print("|                |");
			}
			System.out.print("\n");
		}	
	}
	
	private static void printTxtCardBottom(int cardNumber) {
		System.out.print("\n");
		for (int i=0; i < 3; i++) {
			for (int j=0; j < cardNumber; j++) {
				System.out.print("|                |");
			}
			System.out.print("\n");			
		}
		for (int k=0; k < cardNumber; k++) {
			System.out.print(" ---------------- ");
		}
		System.out.print("\n");
	}
	

	private static void printTxtCard(Card c) {
		final int width = 16;
		String name=c.getName();
		int blank= width-name.length();
		System.out.print("|");		
		if (blank % 2 == 0) { 
			for (int i=0; i < blank/2; i++) {
				System.out.print(" ");
			}
			System.out.print(name);
			for (int j=0; j < blank/2; j++) {
				System.out.print(" ");
			}
		}
		else {
			for (int i=0; i < blank/2; i++) {
				System.out.print(" ");
			}
			System.out.print(name);
			for (int j=0; j <= blank/2; j++) {
				System.out.print(" ");
			}			
		}
		System.out.print("|");		
	}	
}
