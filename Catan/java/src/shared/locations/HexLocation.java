package shared.locations;

/**
 * Represents the location of a hex on a hex map
 */
public class HexLocation
{
	
	private int x;
	private int y;
	
	/**
	 * Class constructor
	 * @param x
	 * @param y
	 */
	public HexLocation(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	/**
	 * @obvious
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * @obvious
	 * @param x
	 */
	private void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * @obvious
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * @obvious
	 * @param y
	 */
	private void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Returns a string representation of the Hes Location
	 * @pre
	 * @post returns a string
	 */
	@Override
	public String toString()
	{
		return "HexLocation [x=" + x + ", y=" + y + "]";
	}
	
	/**
	 * @obvious
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	/**
	 * @obvious
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		HexLocation other = (HexLocation)obj;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		return true;
	}
	
	/**
	 * Gets the Hex Location of the neighboring hex in the direction specified.
	 * @param dir
	 * @pre none
	 * @post returns the Hex Location of the neighboring hex in the directino specified
	 */
	public HexLocation getNeighborLoc(EdgeDirection dir)
	{
		switch (dir)
		{
			case NorthWest:
				return new HexLocation(x - 1, y);
			case North:
				return new HexLocation(x, y - 1);
			case NorthEast:
				return new HexLocation(x + 1, y - 1);
			case SouthWest:
				return new HexLocation(x - 1, y + 1);
			case South:
				return new HexLocation(x, y + 1);
			case SouthEast:
				return new HexLocation(x + 1, y);
			default:
				assert false;
				return null;
		}
	}
}

