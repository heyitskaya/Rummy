package edu.mtholyoke.cs341bd.p0;
import java.util. *;

public class Scrummy {
	
	private HashSet<Card> currentCards= new HashSet<Card>();
	private HashMap<Integer, ArrayList<Card>> mapForSet= new HashMap<Integer,ArrayList<Card>>();
	private HashMap<String, ArrayList<Card>>  mapForRun= new HashMap<String, ArrayList<Card>>();
	private HashSet<ArrayList<Card>> setOfValidRuns= new HashSet<ArrayList<Card>>();
	private ArrayList<ArrayList<Card>> finalSet= new ArrayList<ArrayList<Card>>();
	public Scrummy(String s)
	{
		parseIntoSet(s); //sets currentCards to the set
	}
	
	public HashSet<Card> getCardSet()
	{
		return currentCards;
	}
	
	/** helper method for parsing string into an arraylist**/
	public void printSet()
	{
		Iterator iter= currentCards.iterator();
		while(iter.hasNext())
		{
			iter.next().toString();
		}
	}
	
	public void parseIntoSet(String s)
	{
		//KS 2H 3H 4H 5H 5D 5S
		if(s.isEmpty()==true) 
		{
			return;
		}
		String[] array=s.split(" "); 
		
		for(String str:array) //iterate through array
		{
			Integer order;
			String suit;
			Integer cost;
			String tempString=str.substring(0,1); 
			if(tempString.equals("K"))
			{
				order=13;
				cost=13;
			}
			else if(tempString.equals("Q"))
			{
				order=12;
				cost=12;
			}
			else if(tempString.equals("J"))
			{
				order=11;
				cost=11;
			}
			else if(tempString.equals("A"))
			{
				order=1;
				cost=15;
			}
			else //is a convertible int 
			{
				order=Integer.valueOf(tempString);
				cost=order;
			}
			suit=str.substring(1,2);
			//check to see if it's ace
			
		//	Card card= new Card(order,suit,cost);
			Card card= new Card(cost,suit,order);
			currentCards.add(card); //add it to the set
		}
	}
	/** go through current cards and hash according to suit and 
	 * then sort each arraylist 
	 */
	public HashMap<String, ArrayList<Card>> initHashMapForRun()
	{
		mapForRun.put("H", new ArrayList<Card>());
		mapForRun.put("D", new ArrayList<Card>());
		mapForRun.put("C", new ArrayList<Card>());
		mapForRun.put("S", new ArrayList<Card>());
		return mapForRun;
		
	}

	public ArrayList<ArrayList<Card>> findRun()
	{
		initHashMapForRun();
		//iterate through currentCards
		Iterator iter= currentCards.iterator();
		while(iter.hasNext())
		{
			Card currentCard= (Card)iter.next();
			String currentSuit=currentCard.getSuit();
			ArrayList<Card> tempList=mapForRun.get(currentSuit);
			tempList.add(currentCard);
			
		}
		Iterator iter2= mapForRun.keySet().iterator();
		while(iter2.hasNext())
		{
			String currentKey=(String)iter2.next();
			ArrayList<Card> list= mapForRun.get(currentKey); //get the array list
			//sort it
			if(!list.isEmpty())
			{
				Collections.sort(list);
				findRunInArrayList(list);
			}
		}
		//after this all the lists should be sorted
		//again iterate through them
	//	Iterator iter3= mapForRun.keySet
		//update currentCards after calling this
		
		return finalSet;
	}
	
	//look at this again
	public HashSet<ArrayList<Card>>  findRunInArrayList(ArrayList<Card> a)
	{
		ArrayList<ArrayList<Card>> listOfRuns= new ArrayList<ArrayList<Card>>();
		Card dupeCard;
		Card nonDupe;
		int p1=0;
		int p2=2;
		String currentSuit=a.get(0).getSuit();
		Card A1= new Card(15,currentSuit,1);
		Card A14= new Card(15, currentSuit,14);
		if(a.contains(A1))
		{
			nonDupe=A1;
			dupeCard=A14;
			a.add(A14);
		}
		else if(a.contains(A14))
		{
			nonDupe=A14;
			dupeCard=A1;
			a.add(0,A1);
		}
		int size=a.size();  //CHANGED THIS BE VERY CAREFUL
		while(p2<size && p1<p2)
		{
			//System.out.println("p1 "+p1);
			//System.out.println("p2 "+p2);
			if(isValidRun(p1,p2,a)) //if this is a valid run
			{
				size=a.size(); //update size here cause we may have removed sth
				ArrayList<Card> temp= new ArrayList<Card>();
				temp.add(a.get(p1));
				temp.add(a.get(p1+1));
				temp.add(a.get(p2));
				currentCards.remove(a.get(p1));
				currentCards.remove(a.get(p1+1));
				currentCards.remove(a.get(p2));
				
				//after adding p1 to p2 increment p2 to see we can find anymmore
				//while(a.get(p2+1).getOrder()-a.get(p2).getOrder()==1 && p2<size)
				
				if(inRange(p2+1,size))
				{
				
				while(inRange(p2+1,size) && a.get(p2+1).getOrder()-a.get(p2).getOrder()==1 && p2<size && inRange(p2,size))
				{
					temp.add(a.get(p2+1));
					currentCards.remove(a.get(p2+1));
					
					p2++;
				}
				}
				else //we've reached end of arraylist
				{
					setOfValidRuns.add(temp);
					return setOfValidRuns;
				}
				
				if(temp.contains(A1))
				{
					a.remove(A14);
				}
				if(temp.contains(A14))
				{
					a.remove(A1);
				}
				System.out.println("dsjfljflsf");
				printList(temp);
				
				setOfValidRuns.add(temp);
				
				
				if(inRange(p2+1,size) && inRange(p2+3,size))
				{
					
					p1=p2+1;
					p2=p1+2;
				}
			}
			else
			{
				//System.out.println("we should be in here");
				p1++;
				p2++;
			}
		}
		//System.out.println("set of valid runs "+ setOfValidRuns.size());
		for(ArrayList<Card> list:setOfValidRuns)
		{
			StringBuilder sb= new StringBuilder("");
			
			for(Card c:list)
			{
				sb.append(c.toString());
				sb.append(" ");
			}
			//System.out.println("Fucking print");
			System.out.println("printing runs");
			System.out.println(sb.toString());
			
		}
		return setOfValidRuns;
		
	}
	
	public void printSet(HashSet<ArrayList<Card>> set)
	{
		for(ArrayList<Card> a:set)
		{
			
			printList(a);
		}
	}
	
	public boolean inRange(int index, int sizeOfList)
	{
		if(index>=0 && index<sizeOfList)
		{
			return true;
		}
		return false;
	}
	//for size 3
	public boolean isValidRun(int p1, int p2, ArrayList<Card> list)
	{
		if(list==null)
		{
			return false;
		}
		if(list.get(p2).getOrder()-list.get(p2-1).getOrder()==1 &&list.get(p2-1).getOrder()-list.get(p1).getOrder()==1 )
		{
			return true;
		}
		return false;
	}
	/** initializes hashMap according to order**/
	public HashMap<Integer, ArrayList<Card>> initHashMapForSet()
	{
		for(Integer i=1;i<14;i++)
		{
			mapForSet.put(i, new ArrayList<Card>());
		}
		return mapForSet;
	}
	
	
	
	/**hash according to cost**/
	public ArrayList<ArrayList<Card>> findSet()
	{
		//update current cards after calling this 
		//iterate through hashset, use map for set
		mapForSet=initHashMapForSet();
		Iterator iter= currentCards.iterator();
		while(iter.hasNext())
		{
			Card currentCard= (Card)iter.next();
			Integer order=currentCard.getOrder();
			ArrayList<Card> tempList=mapForSet.get(order);
			printList(tempList);
			tempList.add(currentCard);
			//put it back in the map
			mapForSet.put(order, tempList);
		}
		ArrayList<ArrayList<Card>> setsFound= new ArrayList<ArrayList<Card>>();
		Iterator iter2=mapForSet.keySet().iterator(); //iterate through the key set
		while(iter2.hasNext())
		{
			Integer currentKey=(Integer)iter2.next();
			ArrayList<Card> currentArrayList=mapForSet.get(currentKey);
			if(currentArrayList.size()>=3) //if the arrayList this
				//key maps to is greater or equal to 3
			{
				setsFound.add(currentArrayList);
				iter2.remove(); //use iter.remove() to remove things
				//from hashmap while iterating through it
				
				//iterate through currentArrayList and remove eveything from
				//currentCards thats currently in this arrayList
				for(Card c:currentArrayList)
				{
					currentCards.remove(c);
				}
			}
			//instead of removing set key to void
			//mapForSet.put(-1, new ArrayList<Card>());
		}
		for(ArrayList<Card> al: setsFound)
		{
			StringBuilder sb = new StringBuilder("");
			for(Card c:al)
			{
				sb.append(c.toString());
				sb.append(" ");
				
			}
			System.out.println(sb.toString()); //DO NOT UNCOMMENT
		}
		
		return setsFound;
		//after getting the set update the currentCards hashset
	}

	
	public HashMap<Integer, ArrayList<Card>> getMapForSet()
	{
		return mapForSet;
	}
	
	/** for debugging**/
	public void printHashMapKeys(HashMap map)
	{
		Iterator iter= map.keySet().iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
	}
	
	public void printCurrentCards()
	{
		//iterate through hashset that is current cards and print stuff
		Iterator iter= currentCards.iterator();
		while(iter.hasNext())
		{
			Card currentCard= (Card)iter.next();
			currentCard.toString();
			
		}
	}
	
	public HashMap<String,ArrayList<Card>> getMapForRun()
	{
		return mapForRun;
	}
	
	public void printList(ArrayList<Card> list)
	{
		StringBuilder s= new StringBuilder("");
		for(Card c:list)
		{
			s.append(c.toString());
			s.append(" ");
		}
		System.out.println(s.toString());
		//System.out.println("kaya af" +s);
	}
	
	public int calcScore()
	{
		int score=0;
		//go through currentCard
		for(Card c:currentCards)
		{
			score+=c.getCost();
		}
		System.out.println("The score is "+score);
		return score;
	}
	
	public static void main(String[] args)
	{
		//Scrummy game= new Scrummy("AH 2H 3H 5C 5D 5S JH JD QH KH 9H 9C 9D 9S");
		Scrummy game= new Scrummy("KS 2H 3H 4H 5H 5D 5S");
		System.out.println("??????????");
		game.findSet();
		System.out.println("7777777777");
		game.findRun();
		System.out.println("9999999999");
		
		
		
		
		game.calcScore();
	}
		
	
}
