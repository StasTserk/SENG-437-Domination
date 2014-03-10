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
}
