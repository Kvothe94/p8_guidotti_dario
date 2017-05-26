/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class ScopeTest {

	private Scope myScope;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,0},
				{1,1},
				{2,1},
				{3,2},
				{4,2}
		};
		
		return Arrays.asList(data);
		
	}
	
	@Parameter(0) public int ptype;
	@Parameter(1) public int pnumVar;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		myScope = new Scope();
		
	}


	/**
	 * Test method for {@link patterns.Scope#Scope()}.
	 */
	@Test
	public final void testScope() {
		
		Scope auxScope = new Scope();
		assertNotNull(auxScope);
	
	}

	/**
	 * Test method for {@link patterns.Scope#getType()}.
	 */
	@Test
	public final void testGetType() {

		myScope.setType(3);
		assertEquals(myScope.getType(), 3);
		
	}

	/**
	 * Test method for {@link patterns.Scope#setType(int)}.
	 */
	@Test
	public final void testSetType() {
		
		myScope.setType(3);
		assertEquals(myScope.getType(), 3);
		
	}

	/**
	 * Test method for {@link patterns.Scope#getScopeVar1()}.
	 */
	@Test
	public final void testGetScopeVar1() {
		
		myScope.setScopeVar1("ScopeVar1");
		assertEquals(myScope.getScopeVar1(), "ScopeVar1");
		
	}

	/**
	 * Test method for {@link patterns.Scope#setScopeVar1(java.lang.String)}.
	 */
	@Test
	public final void testSetScopeVar1() {

		myScope.setScopeVar1("ScopeVar1");
		assertEquals(myScope.getScopeVar1(), "ScopeVar1");
		
	}

	/**
	 * Test method for {@link patterns.Scope#getScopeVar2()}.
	 */
	@Test
	public final void testGetScopeVar2() {
		
		myScope.setScopeVar2("ScopeVar2");
		assertEquals(myScope.getScopeVar2(), "ScopeVar2");
		
	}

	/**
	 * Test method for {@link patterns.Scope#setScopeVar2(java.lang.String)}.
	 */
	@Test
	public final void testSetScopeVar2() {
		
		myScope.setScopeVar2("ScopeVar2");
		assertEquals(myScope.getScopeVar2(), "ScopeVar2");
		
	}

	/**
	 * Test method for {@link patterns.Scope#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myScope.setType(ptype);
		assertEquals(myScope.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.Scope#asString()}.
	 */
	@Test
	public final void testAsString() {

		myScope.setScopeVar1("ScopeVar1");
		myScope.setScopeVar2("ScopeVar2");
		myScope.setType(ptype);
		assertNotNull(myScope.asString());
		
		
		
	}
	
	/**
	 * Test method for {@link patterns.Scope#asString()}.
	 */
	@Test
	public final void testAsStringError() {

		myScope.setType(10);
		assertNull(myScope.asString());
		
	}

}
