package warCards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck(String type)
	{
		if (type.equals("Empty")) {
			cards = new  ArrayList<Card>();
		}
		
		if (type.equals("InitGame")) {
			cards = new ArrayList<Card>();
			// run on 4 suits
			for (int suit = 0; suit < 4; suit++)
			{
				for (int value = 2; value <= 14; value++)
				{
					cards.add(new Card(value));
				}
			}
			
			// add 2 jokers
			cards.add(new Card(15));
			cards.add(new Card(15));
			this.shuffleDeck();
		}
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	public Card getFirstCard(){
		int firstCardValue = this.cards.get(0).getValue();
		cards.remove(0);
		return new Card(firstCardValue);
	}
	
	public void addCard(Card card){
		this.cards.add(card);
	}
	
	public void printDeck(){
		for (Card card : this.getCards()) {
		    card.printCard();
		}
	}
	
	public int getAmount(){
		return this.cards.size();
	}
	
	public void shuffleDeck(){
		if (this.cards.size() > 0)
		{
			Collections.shuffle(this.cards);
		}
	}
}
