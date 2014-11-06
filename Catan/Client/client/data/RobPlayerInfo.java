package client.data;

import java.util.Comparator;

/**
 * Used to pass player information into the rob view<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * <li>NumCards: Number of development cards the player has (>= 0)</li>
 * </ul>
 * 
 */
public class RobPlayerInfo extends PlayerInfo implements Comparable
{
	
	private int numCards;
	
	public RobPlayerInfo()
	{
		super();
	}
	public RobPlayerInfo(int cards){
		numCards=cards;
	}
	
	public int getNumCards()
	{
		return numCards;
	}
	
	public void setNumCards(int numCards)
	{
		this.numCards = numCards;
	}
	public static Comparator<RobPlayerInfo> RobPlayerInfoComparator 
	= new Comparator<RobPlayerInfo>() {

		@Override
		public int compare(RobPlayerInfo arg0, RobPlayerInfo arg1) {
			String name1 = arg0.getName().toUpperCase();
			String name2 = arg1.getName().toUpperCase();
	 
			//ascending order
			return name1.compareTo(name2);
		}

	};

	@Override
	public int compareTo(Object o) {
		int compareQuantity = ((RobPlayerInfo) o).getNumCards(); 
		 
		//ascending order
		return this.getNumCards() - compareQuantity;
	}
	
}

