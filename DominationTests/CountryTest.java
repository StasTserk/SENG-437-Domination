package DominationTests;
import static org.junit.Assert.*;
import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import java.util.Vector;
import java.util.List;

import org.junit.Test;

public class CountryTest {

	private final int TIMEOUT = 3000;
	
	//MISING JAMES CODE. ADDARMY. GETDISTANCE
	
	@Test(timeout = TIMEOUT)
	public void testGetSetColour() {
		Country country = new Country(0xFF00FF, "id1", "County", null, 0, 0);
		
		country.setColor(0xFF00FF);
		assertEquals(0xFF00FF, country.getColor());
	}
	
	@Test(timeout = TIMEOUT)
	public void testGetDistance() {
		Country country = new Country(0xFF00FF, "id1", "County", null, 0, 0);
		
		country.setX(0);
		country.setY(0);
		
		assertEquals(0, country.getX());
		assertEquals(0, country.getY());
		assertEquals(5, country.getDistanceTo(3, 4));
	}
	
	@Test(timeout = TIMEOUT)
	public void testCrossContinentNeighbours() {
		Continent continent1 = new Continent("id1", "Continent1", 0, 0xFF00FF);
		Continent continent2 = new Continent("id2", "Continent2", 0, 0xFF00FF);
		
		Country country = new Country(0xFF00FF, "id3", "Country", continent1, 0, 0);
		Country ally = new Country(0xFF00FF, "id4", "AllyCountry", continent1, 0, 0);
		Country crossNeighbour = new Country(0xFF00FF, "id3", "neighbour", continent2, 0, 0);
		
		country.addNeighbour(ally);
		country.addNeighbour(crossNeighbour);
		
		ally.addNeighbour(crossNeighbour);
		crossNeighbour.addNeighbour(country);
		
		List <Country> crossNeighbours = country.getCrossContinentNeighbours();
		
		assertTrue(crossNeighbours.contains(crossNeighbour));
		assertFalse(crossNeighbours.contains(ally));
		
	}
	
	@Test(timeout = TIMEOUT)
	public void testSetGetIDString()
	{
		Country country = new Country(0xFF00FF, "id1", "Country", null, 0, 0);
		
		assertEquals("id1", country.getIdString());
		country.setIdString("id2");
		
		assertEquals("id2", country.getIdString());
	}
	
	@Test(timeout = TIMEOUT)
	public void testSetGetName()
	{
		Country country = new Country(0xFF00FF, "id1", "Country", null, 0, 0);
		
		assertEquals("Country", country.getName());
		country.setName("Country2");
		
		assertEquals("Country2", country.getName());
	}
	
	@Test(timeout = TIMEOUT)
	public void testSetContinent()
	{
		Continent continent1 = new Continent("id1", "Continent1", 0, 0xFF00FF);
		Country country = new Country(0xFF00FF, "id2", "Country", null, 0, 0);
		
		assertNull(country.getContinent());
		country.setContinent(continent1);
		assertEquals(continent1, country.getContinent());
	}
	
	@Test(timeout = TIMEOUT)
	public void testToString()
	{
		Country country = new Country(0xFF00FF, "id1", "Country", null, 0, 0);
		
		assertEquals("id1 (16711935)", country.toString());
	}
	
	@Test(timeout = TIMEOUT)
    public void testGetDistanceNotUsingZero() {
           Country country = new Country(0xFF00FF, "id1", "County", null, 0, 0);
          
           country.setX(2);
           country.setY(2);
          
           assertEquals(2, country.getX());
           assertEquals(2, country.getY());
           assertEquals(13, country.getDistanceTo(7, 14));
    }
}