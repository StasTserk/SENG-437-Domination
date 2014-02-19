package DominationTests;

import static org.junit.Assert.*;

import java.util.Vector;

import net.yura.domination.engine.Risk;
import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;
import net.yura.domination.engine.translation.MapTranslator;

import org.junit.Test;


public class NegativeTests {
	static Risk myrisk;
    static RiskGame risk_board;
    
    MapTranslator map = new MapTranslator();
    
    static protected Continent schulich = new Continent("c1", "schulich", 8, 0);
    
    static protected Country mechanical = new Country(0, "country0", "mechanical", schulich, 0, 0);
    static protected Country software = new Country(0, "country1", "software", schulich, 0, 0);
    static protected Country electrical = new Country(0, "country2", "electrical", schulich, 0, 0);
    static protected Country civil =new Country(0, "country3", "civil", schulich, 0, 0);
    static protected Country chemical = new Country(0, "country4", "chemical", schulich, 0, 0);
    
    static protected Player player1 = new Player(0, "lukas", 0, "p0");
    static protected Player player2 = new Player(1, "sebastian", 1, "p1");


    // Test ID 37
    @Test(expected = Exception.class)
    public void set_getIDstringNegativeTest(){
        schulich.setIdString(null);
        @SuppressWarnings("unused")
		String id_string = schulich.getIdString();
    }
    // Test ID 38
    @Test (expected = Exception.class)
    public void set_getNameNegativeTest(){
        
        schulich.setName(null);
        @SuppressWarnings("unused")
		String name_string = schulich.getName();
        
    }

    // Test ID 39
    @Test
    //Bug found. Function should not be able to take negaitve values as input
    public void set_getArmyValueNegativeTest(){
            
        schulich.setArmyValue(-10);
        int army_value = schulich.getArmyValue();
        assertEquals(null, army_value);

    }
    
    // Test ID 40
    @Test
    //Bug found. Function should not be able to take in int values not defined to a color
    public void set_getColorNegativeTest(){
        
        schulich.setColor(-99);
        int color = schulich.getColor();
        assertEquals(null, color);
    }
    
    // Test ID 41
    @SuppressWarnings("rawtypes")
	@Test
    //Bug Found. Should not be able to add null input as country. Should not be able to add same country more than once
    public void addCountriesToContinentNegative(){
        
        Continent schulich = new Continent("c1", "schulich", 8, 0);
        
        schulich.addTerritoriesContained(null);
        schulich.addTerritoriesContained(software);
        schulich.addTerritoriesContained(software);
        schulich.addTerritoriesContained(civil);
        schulich.addTerritoriesContained(chemical);
    
        Vector country_vector = schulich.getTerritoriesContained();
        int number_of_countries = country_vector.size();
        
        assertEquals(3, number_of_countries);
    }
    
    // Test ID 42
    @Test
    //Bug Found. Multiples of same countries and null values should not be able to be inputted
    public void checkCountriesOwnedNegative(){
        
        player1.newCountry(mechanical);
        player1.newCountry(software);
        player1.newCountry(software);
        player1.newCountry(null);
        player1.newCountry(chemical);    
        
        mechanical.setOwner(player1);
        software.setOwner(player1);
        chemical.setOwner(player1);
        
        
        int number_of_countries = player1.getNoTerritoriesOwned();
    
        assertEquals(3, number_of_countries);
    }
    
    // Test ID 43
    @Test
    //Bug Found. Returns true even thought schulich is owned by player1 and not null
    public void checkContinentsOwnedNegative(){
        
        boolean cowner= schulich.isOwned(null);

        assertEquals(cowner, false);
    }
    
    @Test
    //Bug Found. Should not be able to take negative values as input. 
    public void add_getArmiesNegative(){
        
        player1.addArmies(-37);
        int armies = player1.getExtraArmies();
        assertEquals(null, armies);
    }


}
