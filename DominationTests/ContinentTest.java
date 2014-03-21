package DominationTests;

import static org.junit.Assert.*;
import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;

import java.util.Vector;

import org.junit.Test;

public class ContinentTest {

	private final int TIMEOUT = 3000;
	
	@Test(timeout = TIMEOUT)
	public void testAddTerritories() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		
		continent.addTerritoriesContained(country);
		
		Vector<Country> countries = continent.getTerritoriesContained();
		assertTrue(countries.contains(country));
	}
	
	@Test(timeout = TIMEOUT)
	public void testEquals() {
		Continent continent1 = new Continent("id1", "Continent", 0, 0xFF00FF);
		Continent continent2 = new Continent("id2", "Continent", 0, 0xFF00FF);
		Continent continent3 = new Continent("id1", "Continent2", 0, 0xFF00FF);
		Continent continent4 = new Continent("id2", "Continent", 1, 0xFF00FF);
		Continent continent5 = new Continent("id2", "Continent", 0, 0xFFFFFF);
		
		assertTrue(continent1.equals(continent1));
		assertFalse(continent1.equals(continent2));
		assertFalse(continent1.equals(continent3));
		assertFalse(continent1.equals(continent4));
		assertFalse(continent1.equals(continent5));
		
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetOwnerPositive() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		
		country.setOwner(p);
		
		assertEquals(continent.getOwner(), p);
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetOwnerNegative() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Player p2 = new Player(Player.PLAYER_HUMAN, "Bob", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		
		country.setOwner(p);
		
		assertNotEquals(continent.getOwner(), p2);
	
	}
	@Test(timeout = TIMEOUT)
	public void testGetOwnerMutation(){
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		Country country2 = new Country(0xFF00FF, "id3", "County2", continent, 0, 0);
		continent.addTerritoriesContained(country2);
		
		country.setOwner(p);
		country2.setOwner(p);
		
		assertEquals(continent.getOwner(), p);
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testIsOwnedPositive() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		
		country.setOwner(p);
		
		assertTrue(continent.isOwned(p));
	}
	
	@Test(timeout = TIMEOUT)
	public void testIsOwnedNegative() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Player p2 = new Player(Player.PLAYER_HUMAN, "Bob", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		
		country.setOwner(p);
		
		assertFalse(continent.isOwned(p2));
	
	}
	
	@Test(timeout = TIMEOUT)
	public void testNumberOwned() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		Player p = new Player(Player.PLAYER_HUMAN, "Steve", 0x00FF00, "localhost");
		Player p2 = new Player(Player.PLAYER_HUMAN, "Bob", 0x00FF00, "localhost");
		Country country = new Country(0xFF00FF, "id2", "County", continent, 0, 0);
		continent.addTerritoriesContained(country);
		
		country.setOwner(p);
		
		assertEquals(continent.getNumberOwned(p), 1);
		assertEquals(continent.getNumberOwned(p2), 0);
	
	}
	
	@Test(timeout = TIMEOUT)
	public void testToString() {
		Continent continent = new Continent("id1", "Continent", 0, 0xFF00FF);
		String expected = "id1";
		assertEquals(expected, continent.toString());
	
		continent.setArmyValue(4);
		expected = "id1 [4]";
		assertEquals(expected, continent.toString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetBorderCountries() {
		Continent continent1 = new Continent("id1", "Continent1", 0, 0xFF00FF);
		Continent continent2 = new Continent("id2", "Continent2", 0, 0xFF00FF);
		Country country1 = new Country(0xFF00FF, "id3", "County", continent1, 0, 0);
		Country country2 = new Country(0x00FF00, "id4", "Neighbour", continent2, 0, 0);
		continent1.addTerritoriesContained(country1);
		continent2.addTerritoriesContained(country2);
		
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		
		Vector<Country> neighbours = continent1.getBorderCountries();
		
		assertTrue(neighbours.contains(country1));
		assertFalse(neighbours.contains(country2));
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetBorderCountriesMutation() {
		Continent continent1 = new Continent("id1", "Continent1", 0, 0xFF00FF);
		Continent continent2 = new Continent("id2", "Continent2", 0, 0x00FF00);
		Country country1 = new Country(0xFF00FF, "id3", "County", continent1, 0, 0);
		Country country2 = new Country(0x00FF00, "id4", "Neighbour1", continent1, 0, 0);
		Country country3 = new Country(0xFF00FF, "id5", "Neighbour2", continent2, 0, 0);
		Country country4 = new Country(0x00FF00, "id6", "Neighbour3", continent2, 0, 0);
		
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		country1.addNeighbour(country3);
		country3.addNeighbour(country1);
		country1.addNeighbour(country4);
		country4.addNeighbour(country1);
		
		continent1.addTerritoriesContained(country1);
		continent1.addTerritoriesContained(country2);
		continent2.addTerritoriesContained(country3);
		continent2.addTerritoriesContained(country4);
		
		Vector<Country> neighbours = continent1.getBorderCountries();
		
		assertFalse(neighbours.contains(country3));
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testNeighboringCountries() {
		Continent continent1 = new Continent("id1", "Continent1", 0, 0xFF00FF);
		Continent continent2 = new Continent("id2", "Continent2", 0, 0xFF00FF);
		Country country1 = new Country(0xFF00FF, "id3", "County", continent1, 0, 0);
		Country country2 = new Country(0x00FF00, "id4", "Neighbour", continent2, 0, 0);
		continent1.addTerritoriesContained(country1);
		continent2.addTerritoriesContained(country2);
		
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		
		Vector<Country> neighbours = continent1.getBorderCountries();
		
		assertTrue(neighbours.contains(country1));
		assertFalse(neighbours.contains(country2));
	}
}