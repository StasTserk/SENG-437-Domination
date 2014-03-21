package DominationTests;

import static org.junit.Assert.*;

import java.util.Vector;

import net.yura.domination.engine.core.Continent;
import net.yura.domination.engine.core.Country;
import net.yura.domination.engine.core.Player;
import net.yura.domination.engine.core.RiskGame;

import org.junit.Test;

public class STests {
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
	
	RiskGame risk_board = new RiskGame(0);
	Country mechanical = new Country(0,"","",null,0,0);;
	Country software = new Country(0,"","",null,0,0);;
	Country electrical = new Country(0,"","",null,0,0);;
	Country civil = new Country(0,"","",null,0,0);;
	Country chemical = new Country(0,"","",null,0,0);; 
	Continent schulich = new Continent("","",0,0);
	Player player1 = new Player(1, "James", 1, "idgaf");
	
	// Test ID 1
	@Test
    public void CreateRiskTest(){
        try {
                risk_board = new RiskGame(-1);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

	// Test ID 2
    @SuppressWarnings("rawtypes")
	@Test
    public void AddPlayers_getPlayersTest(){
    	risk_board.setGamesState(STATE_NEW_GAME);
        risk_board.addPlayer(1, "James", 1, "idgaf");
        risk_board.addPlayer(1, "Cole", 2, "idgaf");
        risk_board.addPlayer(2, "Lukas", 3, "idgaf");

        Vector players = risk_board.getPlayers();
        int x = players.size();
        assertEquals(x, 3);
        
    }
    
	// Test ID 3
    @Test
    public void set_getIDstringTest(){
        schulich.setIdString("c0");
        String id_string = schulich.getIdString();
        assertEquals("c0", id_string);
    }
    
	// Test ID 4
    @Test
    public void set_getNameTest(){
        
        schulich.setName("Engineering");
        String name_string = schulich.getName();
        assertEquals("Engineering", name_string);

    }
    
	// Test ID 5
    @Test
    public void set_getArmyValueTest(){
        
        schulich.setArmyValue(10);
        int army_value = schulich.getArmyValue();
        assertEquals(10, army_value);

    }
    
	// Test ID 6
    @Test
    public void set_getColorTest(){
        
        schulich.setColor(1);
        int color = schulich.getColor();
        assertEquals(1, color);
    }
    
	// Test ID 7
    //TESTS FOR MAP
    @SuppressWarnings("rawtypes")
	@Test
    public void addCountriesToContinent(){
        
        schulich.addTerritoriesContained(mechanical);
        schulich.addTerritoriesContained(software);
        schulich.addTerritoriesContained(electrical);
        schulich.addTerritoriesContained(civil);
        schulich.addTerritoriesContained(chemical);
    
        Vector country_vector = schulich.getTerritoriesContained();
        int number_of_countries = country_vector.size();
        
        assertEquals(5, number_of_countries);
    }
    
	// Test ID 8
    //TESTS FOR TROOP PLACEMENT
    @Test
    //Checks countries owned
    public void checkCountriesOwned(){
        
        player1.newCountry(mechanical);
        player1.newCountry(software);
        player1.newCountry(electrical);
        player1.newCountry(civil);
        player1.newCountry(chemical);    
        
        mechanical.setOwner(player1);
        software.setOwner(player1);
        electrical.setOwner(player1);
        civil.setOwner(player1);
        chemical.setOwner(player1);
        
        
        int number_of_countries = player1.getNoTerritoriesOwned();
    
        assertEquals(5, number_of_countries);
    }
    
	// Test ID 9
    @Test
    //Checks continents owned
    public void checkContinentsOwned(){
        
        boolean cowner= schulich.isOwned(player1);
    
        assertEquals(cowner, true);
    }
    
	// Test ID 10
    @Test
    //Troops earned from trading in cards and territories and continents owned
    public void add_getArmies(){
        
        player1.addArmies(10);
        int armies = player1.getExtraArmies();
        assertEquals(10, armies);
    }


}
