package DominationTests;
import static org.junit.Assert.*;
import net.yura.domination.engine.core.Card;
import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import net.yura.domination.engine.core.Mission;

import java.util.Vector;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

public class PlayerTest {

	private final int TIMEOUT = 3000;
	private Player p;
	
	@Before
	public void initialize()
	{
		p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetAddress() {
		String expected = "localhost";
		assertEquals(expected, p.getAddress());
		
		expected = "www.google.ca";
		p.setAddress("www.google.ca");
		
		assertEquals(expected, p.getAddress());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetAutodefend()
	{
		p.setAutoDefend(true);
		assertTrue(p.getAutoDefend());
		
		p.setAutoDefend(false);
		assertFalse(p.getAutoDefend());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetAutoEndGo()
	{
		p.setAutoEndGo(true);
		assertTrue(p.getAutoEndGo());
		
		p.setAutoEndGo(false);
		assertFalse(p.getAutoEndGo());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetType()
	{
		p.setType(Player.PLAYER_AI_AVERAGE);
		assertEquals(Player.PLAYER_AI_AVERAGE, p.getType());
	}
	
	@Test(timeout = TIMEOUT)
	public void testTerritoriesOwned()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		
		p.newCountry(c1);
		p.newCountry(c2);
		
		Vector owned = p.getTerritoriesOwned();
		
		assertTrue(owned.contains(c1));
		assertTrue(owned.contains(c2));
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetCapital()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		p.setCapital(c1);
		
		assertEquals(c1, p.getCapital());
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testRename()
	{
		assertEquals("Steve", p.getName());
		p.rename("Bob");
		
		assertEquals("Bob", p.getName());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetCard()
	{
		Vector cards = p.getCards();
		
		assertEquals(0, cards.size());
		
		Card newCard = new Card(Card.CANNON, null);
		p.giveCard(newCard);
		
		cards = p.getCards();
		
		assertTrue(cards.contains(newCard));
	}
	
	@Test(timeout = TIMEOUT)
	public void testTradeInCardsNoOwned_NoneOwned()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		Country c3 = new Country(0xFF0000, "id3", "County3", null, 0, 0);
		
		Card card1 = new Card(Card.CANNON, c1);
		Card card2 = new Card(Card.CANNON, c2);
		Card card3 = new Card(Card.CANNON, c3);
		
		p.giveCard(card1);
		p.giveCard(card2);
		p.giveCard(card3);
		
		p.tradeInCards(card1, card2, card3);
		
		assertEquals(0, c1.getArmies());
		assertEquals(0, c2.getArmies());
		assertEquals(0, c3.getArmies());
	}
	
	@Test(timeout = TIMEOUT)
	public void testTradeInCardsNoOwned_OneOwned()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		Country c3 = new Country(0xFF0000, "id3", "County3", null, 0, 0);
		
		Card card1 = new Card(Card.CANNON, c1);
		Card card2 = new Card(Card.CANNON, c2);
		Card card3 = new Card(Card.CANNON, c3);
		
		p.giveCard(card1);
		p.giveCard(card2);
		p.giveCard(card3);
		
		p.newCountry(c1);
		
		p.tradeInCards(card1, card2, card3);
		
		assertEquals(2, c1.getArmies());
		assertEquals(0, c2.getArmies());
		assertEquals(0, c3.getArmies());
	}
	
	@Test(timeout = TIMEOUT)
	public void testTradeInCardsNoOwned_TwoOwned()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		Country c3 = new Country(0xFF0000, "id3", "County3", null, 0, 0);
		
		Card card1 = new Card(Card.CANNON, c1);
		Card card2 = new Card(Card.CANNON, c2);
		Card card3 = new Card(Card.CANNON, c3);
		
		p.giveCard(card1);
		p.giveCard(card2);
		p.giveCard(card3);
		
		p.newCountry(c2);
		
		p.tradeInCards(card1, card2, card3);
		
		assertEquals(0, c1.getArmies());
		assertEquals(2, c2.getArmies());
		assertEquals(0, c3.getArmies());
	}
	
	@Test(timeout = TIMEOUT)
	public void testTradeInCardsNoOwned_ThreeOwned()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		Country c3 = new Country(0xFF0000, "id3", "County3", null, 0, 0);
		
		Card card1 = new Card(Card.CANNON, c1);
		Card card2 = new Card(Card.CANNON, c2);
		Card card3 = new Card(Card.CANNON, c3);
		
		p.giveCard(card1);
		p.giveCard(card2);
		p.giveCard(card3);
		
		p.newCountry(c3);
		
		p.tradeInCards(card1, card2, card3);
		
		assertEquals(0, c1.getArmies());
		assertEquals(0, c2.getArmies());
		assertEquals(2, c3.getArmies());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetNoArmies()
	{
		Country c1 = new Country(0xFF00FF, "id1", "County1", null, 0, 0);
		Country c2 = new Country(0xFFFFFF, "id2", "County2", null, 0, 0);
		
		p.newCountry(c1);
		p.newCountry(c2);
		
		assertEquals(0, p.getNoArmies());
		
		c1.addArmies(2);
		assertEquals(2, p.getNoArmies());
		
		c2.addArmies(2);
		assertEquals(4, p.getNoArmies());
	}
	
	@Test(timeout = TIMEOUT)
	public void testIsAlive()
	{
		assertFalse(p.isAlive());
		
		p.addArmies(1);
		
		assertTrue(p.isAlive());
		
		p.loseExtraArmy(1);
		
		Country c = new Country(0, null, null, null, 0, 0);
		
		p.newCountry(c);
		
		assertTrue(p.isAlive());
	}
	
	@Test(timeout = TIMEOUT)
	public void testPlayersEliminated()
	{
		Player p2 = new Player(0, null, 0, null);
		
		p.addPlayersEliminated(p2);
		
		Vector<Player> players = p.getPlayersEliminated();
		
		assertTrue(players.contains(p2));
	}
	
	@Test(timeout = TIMEOUT)
	public void testTakeCard()
	{
		Card c = new Card(Card.CAVALRY, null);
		
		p.giveCard(c);
		Card actual = p.takeCard();
		assertEquals(c, actual);
		
		Vector cards = p.getCards();
		assertEquals(0, cards.size());
	}
	
	@Test(timeout = TIMEOUT)
	public void testTaceCardTrimming()
	{
		Card c1 = new Card(Card.CAVALRY, null);
		Card c2 = new Card(Card.CANNON, null);
		p.giveCard(c1);
		p.giveCard(c2);;
		
		Card actual = p.takeCard();
		assertEquals(c1, actual);
		
		Vector cards = p.getCards();
		assertEquals(1, cards.size());
		
		actual = p.takeCard();
		assertEquals(c2, actual);
		
		cards = p.getCards();
		assertEquals(0, cards.size());
	}
	
	
	@Test(timeout = TIMEOUT)
	public void testToString()
	{
		assertEquals("Steve", p.toString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetSetMission()
	{
		Mission m = new Mission(p, 0, 0,null, null, null, "Description");
		p.setMission(m);
		assertEquals(m, p.getMission());
	}
}