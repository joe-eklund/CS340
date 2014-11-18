package client.junit;

import org.junit.*;

import proxy.ITranslator;
import proxy.TranslatorJSON;
import static org.junit.Assert.*;
import shared.definitions.ResourceType;
import shared.model.Player;

public class PlayerTest {
	private Player test; 
	
	@Before 
	public void setUp() { 
		test = new Player("green", "George", 2); 
	}
	@Test
	public void testGetColor() {
		assertEquals("Color should be green", "green", test.getColor());
		test.setColor("red");
		assertEquals("Color should be red", "red", test.getColor());
	}

	@Test
	public void testGetName() {
		assertEquals("Name should be George", "George", test.getName());
		test.setName("Henry");
		assertEquals("Name should be Henry", "Henry", test.getName());
	}

	@Test
	public void testGetPlayerIndex() {
		assertEquals("Player index should be 2", 2, test.getPlayerIndex());
		test.setPlayerIndex(0);
		assertEquals("Player index should be 0", 0, test.getPlayerIndex());
	}

	@Test
	public void testGetResources() {
		assertEquals("Player should have 0 resources", 0, test.getResources().totalResourcesCount());
		test.setBrick(2);
		test.setWood(1);
		assertEquals("Player should have 3 resouces", 3, test.getResources().totalResourcesCount());
		assertEquals("Player should have 2 brick", 2, test.getResources().getBrick());
		assertEquals("Player should have 1 wood", 1, test.getResources().getWood());
	}

	@Test
	public void testGetVictoryPoints() {
		assertEquals("Player should have 0 points", 0, test.getVictoryPoints());
		test.buildBuilding();
		test.buildBuilding();
		assertEquals("Player should have 2 points", 2, test.getVictoryPoints());
	}
	@Test
	public void testBuilding() {
		assertEquals("Player shouldn't be able to build a road", false, test.canBuildRoad());
		test.setBrick(1);
		test.setWood(1);
		assertEquals("Player should be able to build a road", true, test.canBuildRoad());
		assertEquals("Player shouldn't be able to build a settlement", false, test.canBuildSettlement());
		test.setSheep(1);
		test.setWheat(1);
		assertEquals("Player should be able to build a settlement", true, test.canBuildSettlement());
		test.setOre(3);
		assertEquals("Player shouldn't be able to build a city",false,test.canBuildCity());
		test.setWheat(2);
		assertEquals("Player should be able to build a city",true,test.canBuildCity());
	}
	
	public static void main(String[] args) 
	{
		String[] testClasses = new String[] 
		{
				"client.PlayerTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}
