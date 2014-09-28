package shared.locations;

import client.exceptions.ClientModelException;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
	
	public static VertexDirection determineDirection(String dir) throws ClientModelException {
		switch(dir) {
		case "NorthWest":
			return VertexDirection.NorthWest;
		case "West":
			return VertexDirection.West;
		case "NorthEast":
			return VertexDirection.NorthEast;
		case "SouthEast":
			return VertexDirection.SouthEast;
		case "East":
			return VertexDirection.East;
		case "SouthWest":
			return VertexDirection.SouthWest;
		default:
			throw new ClientModelException("Invalid direction string given to static method VertexDirection determineDirection in VertexDirection class");
		}
	}
}

