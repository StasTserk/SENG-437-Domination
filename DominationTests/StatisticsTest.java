package DominationTests;

import static org.junit.Assert.*;
import net.yura.domination.engine.core.Card;
import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import net.yura.domination.engine.core.StatType;
import net.yura.domination.engine.core.Statistic;

import java.util.Vector;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

public class StatisticsTest {
	Statistic stats;
	
	@Before
	public void initialize()
	{
		stats = new Statistic();
	}
	
	@Test
	public void testEndGo()
	{
		stats.endGoStatistics(1, 2, 3, 4, 5);
		
		assertTrue(stats.get(StatType.COUNTRIES) == 1);
		assertTrue(stats.get(StatType.ARMIES) == 2);
		assertTrue(stats.get(StatType.CONTINENTS) == 3);
		assertTrue(stats.get(StatType.CONNECTED_EMPIRE) == 4);
		assertTrue(stats.get(StatType.CARDS) == 5);
	}
	@Test
	public void testAddKill()
	{
		stats.addKill();
		assertTrue(stats.get(StatType.KILLS) == 1);
	}
	@Test
	public void testAddCasualty()
	{
		stats.addCasualty();
		assertTrue(stats.get(StatType.CASUALTIES) == 1);
	}
	@Test
	public void testAddReinforcement()
	{
		

	}
	@Test
	public void testAddAttack() 
	{
		stats.addAttack();
		assertTrue(stats.get(StatType.ATTACKS) == 1);	
	}
	@Test
	public void testAddAttacked() {
		stats.addAttacked();
		assertTrue(stats.get(StatType.ATTACKED) == 1);	
	}
	@Test
	public void testAddRetreat() {
		stats.addRetreat();
		assertTrue(stats.get(StatType.RETREATS) == 1);	
	}
	@Test
	public void testAddCountriesWon() {
		stats.addCountriesWon();
		assertTrue(stats.get(StatType.COUNTRIES_WON) == 1);	
	}
	@Test
	public void testAddCountriesLost() {
		stats.addCountriesLost();
		assertTrue(stats.get(StatType.COUNTRIES_LOST) == 1);	
	}
	
	@Test
	public void testAddDiceOnce()
	{
		stats.addDice(1);
		double diceVal = stats.get(StatType.DICE);
		
		assertTrue(2 == diceVal);
	}

	@Test
	public void testAddDiceTwice()
	{
		stats.addDice(1);
		stats.addDice(2);
		double diceVal = stats.get(StatType.DICE);
		
		assertTrue(2.5 == diceVal);
	}
}
