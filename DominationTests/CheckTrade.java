package DominationTests;

import static org.junit.Assert.*;

import net.yura.domination.engine.core.Card;
import net.yura.domination.engine.core.RiskGame;

import org.junit.*;

public class CheckTrade {

	final int DEFAULT_TIMEOUT = 3000;
	RiskGame game = new RiskGame(0);		
	Card Cavalry = new Card(Card.CAVALRY, null);
	Card Cannon = new Card(Card.CANNON, null);
	Card Infantry = new Card(Card.INFANTRY, null);
	Card Wildcard = new Card(Card.WILDCARD, null);
	
	// Test ID 29
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTrade3Cavalry(){		
		assertTrue(game.checkTrade(Cavalry,Cavalry,Cavalry));	
	}
	
	// Test ID 30
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTrade3Wildcard(){		
		assertTrue(game.checkTrade(Wildcard,Wildcard,Wildcard));
	}
	
	// Test ID 31
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTradeCannonWildcardWildcard(){		
		assertTrue(game.checkTrade(Cannon,Wildcard,Wildcard));
	}
	
	// Test ID 32
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTradeCannonCannonWildcard(){		
		assertTrue(game.checkTrade(Cannon,Cannon,Wildcard));
	}
	
	// Test ID 33
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTradeCavalryCannonInfantry(){		
		assertTrue(game.checkTrade(Cavalry,Cannon,Infantry));
	}
	
	// Test ID 34
	@Test(timeout = DEFAULT_TIMEOUT)
	public void AbleTradeCavalryCannonWildcard(){		
		assertTrue(game.checkTrade(Cavalry,Cannon,Wildcard));
	}
	
	// Test ID 35
	@Test(timeout = DEFAULT_TIMEOUT)
	public void UnableTradeCannonCannonInfantry(){		
		assertFalse(game.checkTrade(Cannon,Cannon,Infantry));
	}
	
	// Test ID 36
	@Test(expected = NullPointerException.class, timeout = DEFAULT_TIMEOUT)
	public void UnableTradeCannonCannon(){	
		game.checkTrade(Cannon,Cannon,null);	
	}
}
