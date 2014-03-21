package DominationTests;

import static org.junit.Assert.*;

import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;

import org.junit.Test;

public class CombatTests {
	
	final int DEFAULT_TIMEOUT = 3000;
	final int SHORT_TIMEOUT = 300;
	
	public final static int STATE_NEW_GAME = 0;
	public final static int STATE_TRADE_CARDS = 1;
	public final static int STATE_PLACE_ARMIES = 2;
	public final static int STATE_ATTACKING = 3;
	public final static int STATE_ROLLING = 4;
	public final static int STATE_BATTLE_WON = 5;
	public final static int STATE_FORTIFYING = 6;
	public final static int STATE_END_TURN = 7;
	public final static int STATE_GAME_OVER = 8;
	public final static int STATE_SELECT_CAPITAL = 9;
	public final static int STATE_DEFEND_YOURSELF = 10;
	
	RiskGame game = new RiskGame(0);
	Country Attacking = new Country(0,"","",null,0,0);
	Country Secondary = new Country(0,"","",null,0,0);
	Country Defending = new Country(0,"","",null,0,0);
	Continent []Only ={ new Continent("","",0,0)};
	
	// Test ID 11
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AttackerHasMoreThanOneArmy() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmies(5);
		Defending.addArmy();
		Attacking.addNeighbour(Defending);
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		assertTrue(game.attack(Attacking, Defending));		
	}
	
	// Test ID 12
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AttackerOnlyHasOneArmy() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmy();
		Defending.addArmy();
		Attacking.addNeighbour(Defending);
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));		
		assertFalse(game.attack(Attacking, Defending));	
	}
	
	
	// Test ID 13
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AttackerLoseArmy() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmies(5);
		Defending.addArmies(5);
		Attacking.addNeighbour(Defending);
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		Attacking.looseArmy();
		int expected = Attacking.getArmies();
		assertEquals(expected,4);	
	}
	
	// Test ID 14
	@Test(timeout = DEFAULT_TIMEOUT)
	public void DefenderLoseArmy() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmies(5);
		Defending.addArmies(5);
		Attacking.addNeighbour(Defending);
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		Defending.looseArmy();
		int expected = Defending.getArmies();
		assertEquals(expected,4);	
	}
	
	// Test ID 15
	@Test(timeout = DEFAULT_TIMEOUT)
	public void DiceOrder() {		
		int[] dice  = game.rollDice(3);		
		assertTrue((dice[0] >= dice[1])&&(dice[1] >= dice[2]));	
	}
	
	// Test ID 16
	@Test(timeout = DEFAULT_TIMEOUT)
	public void RollAttackerDiceAllowed() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmies(4);
		Defending.addArmy();
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		game.setGamesState(STATE_ROLLING);
		assertTrue(game.rollA(3));
	}
	
	// Test ID 17
	@Test(timeout = DEFAULT_TIMEOUT)
	public void RollDefenderDiceAllowed() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(4);
		Defending.addArmies(2);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		assertTrue(game.rollD(2));
	}
	
	// Test ID 18
	@Test(timeout = DEFAULT_TIMEOUT)
	public void RollAttackerDiceNotAllowed() {
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		Attacking.addArmies(2);
		Defending.addArmy();
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		game.setGamesState(STATE_ROLLING);
		assertFalse(game.rollA(3));
	}
	
	// Test ID 19
	@Test(timeout = DEFAULT_TIMEOUT)
	public void RollDefenderDiceNotAllowed() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(4);
		Defending.addArmies(1);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		assertFalse(game.rollD(2));
	}
	
	// Test ID 20
	@Test(timeout = DEFAULT_TIMEOUT)
	public void DiceComparisonTestAttackerWinsTwo() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(4);
		Defending.addArmies(2);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		int []AttackerResults = {6,6,1};
		int []DefenderResults = {5,4};
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		int []results = game.battle(AttackerResults, DefenderResults);
			
		assertEquals(2,results[2]);
	}
	
	// Test ID 21
	@Test(timeout = DEFAULT_TIMEOUT)
	public void DiceComparisonTestDefenderWinsTwo() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(4);
		Defending.addArmies(2);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		int []DefenderResults = {6,6};
		int []AttackerResults = {6,4,1};
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		int []results = game.battle(AttackerResults, DefenderResults);			
		assertEquals(2,results[1]);
	}
	
	// Test ID 22
	@Test(timeout = DEFAULT_TIMEOUT)
	public void DiceComparisonTestOneLostEach() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(4);
		Defending.addArmies(2);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));
		game.attack(Attacking, Defending);
		int []DefenderResults = {6,3};
		int []AttackerResults = {6,4,1};
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		int []results = game.battle(AttackerResults, DefenderResults);			
		assertTrue(results[1]==1 && results[2]==1);
	}
	
	// Test ID 23
	@Test(timeout = DEFAULT_TIMEOUT)
	public void MovementTest() {	
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");		
		Attacking.addArmies(10);
		Secondary.addArmies(10);
		Defending.addArmies(5);
		game.setContinents(Only);
		Attacking.addNeighbour(Secondary);		
		game.setCurrentPlayer(0);	
		game.setGamesState(STATE_ATTACKING);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Secondary.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(0));
		game.setGamesState(STATE_FORTIFYING);
		game.moveArmy(Attacking,Secondary,3);
		assertTrue((Attacking.getArmies() == 7) && (Secondary.getArmies() == 13));
	}
	 
    @Test(timeout = DEFAULT_TIMEOUT)
    public void TestAddArmy() {
           Defending.addArmy();
          
           assertEquals(1,Defending.getArmies());         
    }
}
