package shared.locations;

import client.exceptions.ClientModelException;

/**
 * An enum which describes an Edge Direction
 * <br><b>Domain:</b> NorthWest, North, NorthEast, SouthEast, South, SouthWest
 *
 */
public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	/**
	 * Gets the opposite direction of the Edge Direction
	 * @pre none
	 * @post Returns the opposite direction of the Edge Direction
	 */
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
	/**
	 * Determines which direction enum to return based off of the string given as a parameter
	 * @param dir
	 * @pre none
	 * @post Returns an enum assocaited to the specified direction
	 * @throws ClientModelException
	 */
	public static EdgeDirection determineDirection(String dir) throws ClientModelException {
		switch(dir) {
		case "NorthWest":
			return EdgeDirection.NorthWest;
		case "North":
			return EdgeDirection.North;
		case "NorthEast":
			return EdgeDirection.NorthEast;
		case "SouthEast":
			return EdgeDirection.SouthEast;
		case "South":
			return EdgeDirection.South;
		case "SouthWest":
			return EdgeDirection.SouthWest;
		case "NW":
			return EdgeDirection.NorthWest;
		case "N":
			return EdgeDirection.North;
		case "NE":
			return EdgeDirection.NorthEast;
		case "SE":
			return EdgeDirection.SouthEast;
		case "S":
			return EdgeDirection.South;
		case "SW":
			return EdgeDirection.SouthWest;
		default:
			throw new ClientModelException("Invalid direction string given to static method EdgeDirection determineDirectionin EdgeDirection class");
		}
	}
}

