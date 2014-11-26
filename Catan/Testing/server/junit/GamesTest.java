package server.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerModel;

public class GamesTest {
	private IGamesFacade games;
	
	@Before 
	public void setUp() {
		List<ServerModel> serverModels = new ArrayList<ServerModel>();
		games = new GamesFacadeStub(serverModels);
	}
	@Test
	public void testListGames(){ 
		List<GameDescription> descriptions = games.listGames();
		assertEquals("First game's name should match stub description name for first game", descriptions.get(0).getTitle(), "Empty Game");
		assertEquals("Second game's name should match stub description name for second game", descriptions.get(1).getTitle(), "Second Game");
		assertEquals("Second Game should have 4 players listed.", descriptions.get(1).getPlayerDescriptions().size(), 4);
		@SuppressWarnings("serial")
		ArrayList<String> secondGameNames = new ArrayList<String>(){{ 
			add("Bobby");
			add("Billy");
			add("Sandy");
			add("Cathy");
		}};
		
		List<PlayerDescription> secondGamePlayers = descriptions.get(2).getPlayerDescriptions(); 
		for(int i = 0; i < secondGameNames.size(); i++) {
			String shouldBeName = secondGameNames.get(i);
			String nameFromList = secondGamePlayers.get(i).getName();
			assertEquals("Player name + " + i + " in Second Game should be " + shouldBeName + "." , shouldBeName, nameFromList);
		}
	}
	@Test
	public void testCreateGame() { 
		int gamesListSize = games.listGames().size();
		String newGameName = "Another Default Game";
		try {
			games.createGame(new CreateGameRequest(false, false, false, newGameName));
			gamesListSize++;
		} catch (InvalidGamesRequest e) {
			fail("Unable to handle valid create game request!");
		}
		List<GameDescription> gamesList = games.listGames();
		int listSizeToCheck = gamesList.size();
		assertEquals("Games list size failed to increment one when adding a game", gamesListSize, listSizeToCheck);
		String gameNameToTest = gamesList.get(gamesListSize - 1).getTitle();
		assertEquals("New game title should show up at end of gamesList.", newGameName, gameNameToTest);
	}
	@Test
	public void testJoinGame() { 
		// valid join
		try {
			games.joinGame(new JoinGameRequest(0, "blue"), "TestUser", 999);
			assertEquals("Failed to add player name to empty game in games list.", "TestUser", games.listGames().get(0).getPlayerDescriptions().get(0).getName());
		} catch (InvalidGamesRequest e) {
			fail("Failed to accept valid joinGame request.");
		}
		
		boolean invalid = false;
		try {
			invalid = !games.joinGame(new JoinGameRequest(0, "invalid"), "TestUser", 999);
		} catch (InvalidGamesRequest e) {
			invalid = true;
		}
		assertTrue("Failed to catch invalid color in join game request.", invalid);
		
		invalid = false;
		try {
			invalid = !games.joinGame(new JoinGameRequest(0, "blue"), "Bobby", 0);
		} catch (InvalidGamesRequest e) {
			invalid = true;
		}
		System.out.println(invalid);
		assertTrue("Failed to catch duplicate color (already in use by another player) in join game request.", invalid);
	
		invalid = false;
		try {
			invalid = !games.joinGame(new JoinGameRequest(1, "brown"), "TestUser", 11);
		} catch (InvalidGamesRequest e) {
			invalid = true;
		}
		assertTrue("Failed to catch/deny user request to join full game.", invalid);
	}
	
	
	@Test
	public void testSaveGame() { 
		try {
			games.saveGame(0, "testsave");
			assertTrue("Saved game correctly and was suppose to.", true);
		} catch (IOException e) {
			assertTrue("Game did not save correctly and was suppose to.", false);
		}
	}
	
	@Test
	public void testLoadGame() { 
		//There MUST be a "testsave.xml" file in the saves/ folder for this to pass.
		try {
			int id = games.loadGame("testsave");
			boolean condition = false;
			if(id == games.listGames().size()-1){
				condition = true;
			}
			else condition = false;
			assertTrue("Loaded game correctly and was suppose to.", condition);
		} catch (IOException e) {
			assertTrue("Game did not load correctly and was suppose to.", false);
		}
	}
}
