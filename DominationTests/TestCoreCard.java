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
	final int DEFAULT_TIMEOUT = 3000;
	
	// Test ID 25
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetCannonName()
	{
		Card card = new Card(Card.CANNON, null);
		assertEquals(card.getName(), Card.CANNON);
	}
	
	// Test ID 26
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetCavalryName()
	{
		Card card = new Card(Card.CAVALRY, null);
		assertEquals(card.getName(), Card.CAVALRY);
	}
	
	// Test ID 27
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetInfantryName()
	{
		Card card = new Card(Card.INFANTRY, null);
		assertEquals(card.getName(), Card.INFANTRY);
	}
	
	// Test ID 28
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testGetWildcardName()
	{
		Card card = new Card(Card.WILDCARD, null);
		assertEquals(card.getName(), Card.WILDCARD);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testInvalidName()
	{
		Card card = new Card("Cards", null);
		assertEquals(card.getName(), Card.WILDCARD);
	}
}
