package shared.locations;

import client.exceptions.ClientModelException;

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
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
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
		default:
			throw new ClientModelException("Invalid direction string given to static method EdgeDirection determineDirectionin EdgeDirection class");
		}
	}
}

