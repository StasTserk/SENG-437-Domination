/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stas
 */
package DominationTests;

import net.yura.domination.engine.core.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCoreCard {
	
	@Test
	public void testGetName()
	{
		Card card = new Card(Card.CANNON, null);
		assertEquals(card.getName(), Card.CANNON);
	}
}
