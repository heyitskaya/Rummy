package edu.mtholyoke.cs341bd.p0;
import java.util. *;

public class Card implements Comparable<Card>{
	
	private Integer order; //1 or 14
	private String suit;
	private Integer cost; //cost of A is 15

	public Card(Integer c, String s, Integer o )
	{
		cost=c;
		suit=s;
		order=o;
	}
	
	public Integer getOrder() //1 or 14
	{
		return order;
	}
	
	public Integer getCost()
	{
		return cost;
	}
	public String getSuit()
	{
		return suit;
	}
	
	/** print string form of card**/
/**	public String toString()
	{
		StringBuilder s= new StringBuilder(""); //string builder with empty string
		s.append(this.cost);
		s.append(this.suit);
		s.append(this.order);
		
		return s.toString();
		
	}
	**/
	
	//rewrote toString
	
	public String toString()
	{
		StringBuilder s= new StringBuilder("");
		Integer cost= this.cost;
		String suit=this.suit;
		Integer order= this.order;
		
		if(order==1)
		{
			s.append("A");
		}
		else if(order==11)
		{
			s.append("J");
		}
		else if(order == 12)
		{
			s.append("Q");
		}
		else if(order==13)
		{
			s.append("K");
		}
		else
		{
			s.append(order);
		}
		
		s.append(suit);
		return s.toString();
	
	}
	
	
	/** compared to according to order**/
	public int compareTo(Card b)
	{
		if(this.order>b.getOrder())
		{
			return 1;
		}
		else if( this.order.equals(b.getOrder()))
		{
			return 0;
		}
		else 
		{
			return -1;
		}
	}
	
	public boolean isRunPiece(Card a, Card b)
	{
		return true;
	}
	
	
	@Override
	public boolean equals(Object card)
	{
		if(this.getOrder().equals(((Card)card).getOrder()) && this.getCost().equals(((Card)card).getCost()) && this.getSuit().equals(((Card)card).getSuit()))
		{
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] args)
	{
		Card card1= new Card(1, "H", 1);
		Card card2= new Card(14, "H", 14);
		Card card3= new Card(4, "a", 4);
		Card card4= new Card(5, "c", 5);
		
		
		
		
		
		ArrayList<Card> listOfCards= new ArrayList<Card>();
		listOfCards.add(card3);
		listOfCards.add(card1);
		listOfCards.add(card4);
		listOfCards.add(card2);
		Collections.sort(listOfCards); //this works it can actually be sorted
		
		
		int l= listOfCards.size();
		for(Card c:listOfCards)
		{
			c.toString();
		}
		
		if(listOfCards.contains(new Card(1,"H",1)))
		{
			
			listOfCards.remove(new Card(1,"H",1));
		}
		
		for(Card c:listOfCards)
		{
			c.toString();
		}
	}
	//comparator by suit
	
	//by order
	
	
	//one deck of cards
	
	//sort according to suit
	//sort according to order
	
	//have a collection of cards hashset ....
	
	//sorting things
	//runs one suite at a time
	//hashmap of suite to list of cards
	//don't write a sort function
	//Collections.sort(list must be comparable)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
