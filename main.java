package warCards;

public class main {

	public static void main(String[] args) {
		int sum = 0;
		int numberOfRounds = 1000;
		
		for (int counter = 0; counter < numberOfRounds; counter ++)
		{
			Deck deck = new Deck("InitGame");
			Player player1 = new Player();
			Player player2 = new Player();
			devideCards(player1, player2, deck);
			
			int numOfTurns = 0;
			try {
				while (isGameFinish(player1,player2) == false){
					checkIfBankNeeded(player1, player2);
					turn(player1, player2);
					numOfTurns += 1;
				}
			} catch (IndexOutOfBoundsException ex){
				// System.out.println(ex);
			}
			sum += numOfTurns;
		}
		
		System.out.println(sum / numberOfRounds);
	}
	
	public static void devideCards(Player player1,Player player2,Deck deck)
	{
		int i = 0;
		for (Card card : deck.getCards())
		{
			if (i % 2 ==0)
			{
				player1.addCard(card);
			} else {
				player2.addCard(card);
			}
			i++;
		}
	}
	
	public static void turn(Player player1,Player player2){
		Card cardP1 = player1.getCard();
		Card cardP2 = player2.getCard();
		
		// System.out.println("player 1 got: " + cardP1.toString() + "  player 2 got: " + cardP2.toString());
		
		// check who winner
		if (cardP1.getValue() > cardP2.getValue()) { // player 1 win
			player1.addToBank(cardP1, cardP2);
			// System.out.println("player 1 win");
		} else if (cardP1.getValue() == cardP2.getValue()) // war
		{
			System.out.println("war");
			Deck warDeck = new Deck("Empty");
			warTurn(warDeck,cardP1, cardP2, player1, player2);
		} else { // player 2 win
			player2.addToBank(cardP1, cardP2);
			// System.out.println("player 2 win");
		}
	}
	
	public static void warTurn(Deck warDeck, Card cardP1, Card cardP2,Player player1, Player player2)
	{
		// deck for the war will contain in the end:
		// player1 + player 2 original cards
		// 2 additional cards of player1 and 2 additional cards of player2
		// the end war cards of player 1 and 2
		
		// add the original player 1 & 2 cards
		warDeck.addCard(cardP1);
		warDeck.addCard(cardP2);
		
		// add 2 cards of player 1
		checkIfBankNeeded(player1,player2);
		warDeck.addCard(player1.getCard());
		checkIfBankNeeded(player1,player2);
		warDeck.addCard(player1.getCard());
		
		// add 2 cards of player 1
		checkIfBankNeeded(player1,player2);
		warDeck.addCard(player2.getCard());
		checkIfBankNeeded(player1,player2);
		warDeck.addCard(player2.getCard());
		
		checkIfBankNeeded(player1,player2);
		cardP1 = player1.getCard();
		warDeck.addCard(cardP1);
		checkIfBankNeeded(player1,player2);
		cardP2 = player2.getCard();
		warDeck.addCard(cardP2);
		
		// check who wins the war
		if (cardP1.getValue() > cardP2.getValue()) { // player 1 win
			player1.addToBank(warDeck);
		} else if (cardP1.getValue() == cardP2.getValue()){ // war again
			warTurn(warDeck, cardP1, cardP2, player1, player2);
		} else {
			player2.addToBank(warDeck);
		}
	}
	
	public static boolean isGameFinish(Player p1, Player p2){
		// the game ends when one player has all the deck of 54 cards
		return p1.getTotalCardsAmount() == 54 || p2.getTotalCardsAmount() == 54; 
	}
	
	public static void checkIfBankNeeded(Player p1, Player p2){
		int p1amount = p1.getDeck().getAmount();
		int p2amount = p2.getDeck().getAmount();

		if (p1amount == 0 || p2amount == 0)
		{
			p1.fromBankToDeck();
			p2.fromBankToDeck();
		}
	}
	
	
}