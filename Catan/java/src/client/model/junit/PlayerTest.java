package client.model.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.ResourceType;
import client.model.Player;

public class PlayerTest {

	@Test
	public void testGetColor() {
		Player test=new Player("green", "George", 2);
		assertEquals("Color should be green", "green", test.getColor());
		test.setColor("red");
		assertEquals("Color should be red", "red", test.getColor());
	}

	@Test
	public void testGetName() {
		Player test=new Player("green", "George", 2);
		assertEquals("Name should be George", "George", test.getName());
		test.setName("Henry");
		assertEquals("Name should be Henry", "Henry", test.getName());
	}

	@Test
	public void testGetPlayerIndex() {
		Player test=new Player("green", "George", 2);
		assertEquals("Player index should be 2", 2, test.getPlayerIndex());
		test.setPlayerIndex(0);
		assertEquals("Player index should be 0", 0, test.getPlayerIndex());
	}

	@Test
	public void testGetResources() {
		Player test=new Player("green", "George", 2);
		assertEquals("Player should have 0 resources", 0, test.getResources().size());
		test.getBrick();
		test.getBrick();
		test.getWood();
		assertEquals("Player should have 3 resouces", 3, test.getResources().size());
		assertEquals("Player should have 2 brick", ResourceType.BRICK, test.getResources().get(0));
		assertEquals("Player should have 2 brick", ResourceType.BRICK, test.getResources().get(1));
		assertEquals("Player should have 1 wood", ResourceType.WOOD, test.getResources().get(2));
	}

	@Test
	public void testGetVictoryPoints() {
		Player test=new Player("green", "George", 2);
		assertEquals("Player should have 2 points", 2, test.getVictoryPoints());
		test.buildBuilding();
		test.buildBuilding();
		assertEquals("Player should have 4 points", 4, test.getVictoryPoints());
	}

}
