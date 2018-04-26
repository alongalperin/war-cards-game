package warCards;

public class Player {
	private Deck deck;
	private Deck bank;
	
	public Player()
	{
		deck = new Deck("Empty");
		bank = new Deck("Empty");
	}
	
	public Deck getDeck(){
		return this.deck;
	}
	
	public void addCard(Card card){
		this.deck.addCard(card);
	}
	
	public Card getCard(){
		return this.deck.getFirstCard();
	}
	
	public void addToBank(Card card1, Card card2){
		this.bank.addCard(card1);
		this.bank.addCard(card2);
	}
	
	public void addToBank(Deck deck){
		while (deck.getAmount() > 0)
		{
			this.addToBank(deck.getFirstCard(), deck.getFirstCard());
		}
	}
	
	public int getTotalCardsAmount(){
		return this.bank.getAmount()+this.deck.getAmount();
	}
	
	public void fromBankToDeck(){
		bank.shuffleDeck();
		while (bank.getAmount() > 0)
		{
			this.addCard(bank.getFirstCard());
		}
	}
}
