package warCards;

public class Card {
	private int value;
	
	public Card(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void printCard()
	{
		System.out.println( this.toString() );
	}
	
	public String toString()
	{
		return "" + this.value;
	}
}