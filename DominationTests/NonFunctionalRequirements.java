package DominationTests;

import static org.junit.Assert.*;


import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;

import org.junit.Before;
import org.junit.Test;

public class NonFunctionalRequirements {
	
	final int DEFAULT_TIMEOUT = 3000;
	final int SHORT_TIMEOUT = 4;
	
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
	Country Attacking; 
	Country Defending; 

	@Before
	public void Startup(){
		game.setGamesState(STATE_NEW_GAME);
		Attacking = new Country(0,"","",null,0,0);
		Defending = new Country(0,"","",null,0,0);
		game.addPlayer(0, "james", 0, "");
		game.addPlayer(0, "cole", 1, "");
		game.setMaxDefendDice(2);
		Attacking.addArmies(20);
		Defending.addArmies(20);
		Attacking.addNeighbour(Defending);	
		game.setCurrentPlayer(0);
		Attacking.setOwner((Player)game.getPlayers().get(0));
		Defending.setOwner((Player)game.getPlayers().get(1));	
	}
	
	// Test ID 24
	@Test(timeout = SHORT_TIMEOUT)
	public void BattleSpeed() {
		game.setGamesState(STATE_ATTACKING);
		int i = 20;
		for(i = 20; i >= 3; i--){
			game.attack(Attacking, Defending);
			int []DefenderResults = {6,3};
			int []AttackerResults = {6,4,1};
			game.setGamesState(STATE_DEFEND_YOURSELF); 
			game.battle(AttackerResults, DefenderResults);
		}
		
		game.attack(Attacking, Defending);
		int []DefenderResults = {5,3};
		int []AttackerResults = {6,4};
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		game.battle(AttackerResults, DefenderResults);
		
		game.attack(Attacking, Defending);
		int []DefenderResult = {3};
		int []AttackerResult = {6,4};
		game.setGamesState(STATE_DEFEND_YOURSELF); 
		game.battle(AttackerResult, DefenderResult);
		
		assertFalse(false);

	}
	
}
