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

import patterns.OrderPattern;
import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class OrderPatternTest {

	OrderPattern myOrderPattern;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,2},
				{1,3},
				{2,3},
				{3,2},
				{4,3},
				{5,4},
				{6,6},
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
		myOrderPattern = new OrderPattern();
	}

	/**
	 * Test method for {@link patterns.OrderPattern#getPatternClass()}.
	 */
	@Test
	public final void testGetPatternClass() {
		assertEquals(myOrderPattern.getPatternClass(), 2);
	}

	/**
	 * Test method for {@link patterns.OrderPattern#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myOrderPattern.setType(ptype);
		assertEquals(myOrderPattern.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.OrderPattern#asString()}.
	 */
	@Test
	public final void testAsString() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myOrderPattern.setScope(auxScope);
		myOrderPattern.setPatternVar1("patternVar1");
		myOrderPattern.setPatternVar2("patternVar2");
		myOrderPattern.setPatternVar3("patternVar3");
		myOrderPattern.setPatternVar4("patternVar4");
		
		myOrderPattern.setType(ptype);
		assertNotNull(myOrderPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.OrderPattern#asString()}.
	 */
	@Test
	public final void testAsStringTypeError() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myOrderPattern.setType(50);
		assertNull(myOrderPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.OrderPattern#asString()}.
	 */
	@Test
	public final void testAsStringScopeError() {
		
		Scope auxScope = new Scope();
		
		auxScope.setType(6);
		myOrderPattern.setScope(auxScope);
		myOrderPattern.setPatternVar1("patternVar1");
		myOrderPattern.setPatternVar2("patternVar2");
		myOrderPattern.setPatternVar3("patternVar3");
		myOrderPattern.setPatternVar4("patternVar4");
		myOrderPattern.setType(0);
		assertNull(myOrderPattern.asString());
		
		
	}

	/**
	 * Test method for {@link patterns.OrderPattern#OrderPattern()}.
	 */
	@Test
	public final void testOrderPattern() {
		OrderPattern auxDP = new OrderPattern();
		assertNotNull(auxDP);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getType()}.
	 */
	@Test
	public final void testGetType() {
		myOrderPattern.setType(1);
		assertEquals(myOrderPattern.getType(), 1);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getScope()}.
	 */
	@Test
	public final void testGetScope() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		myOrderPattern.setScope(auxScope);
		assertEquals(auxScope, myOrderPattern.getScope());
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar1()}.
	 */
	@Test
	public final void testGetPatternVar1() {
		
		myOrderPattern.setPatternVar1("patternVar1");
		assertEquals(myOrderPattern.getPatternVar1(), "patternVar1");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar2()}.
	 */
	@Test
	public final void testGetPatternVar2() {
	
		myOrderPattern.setPatternVar2("patternVar2");
		assertEquals(myOrderPattern.getPatternVar2(), "patternVar2");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar3()}.
	 */
	@Test
	public final void testGetPatternVar3() {

		myOrderPattern.setPatternVar3("patternVar3");
		assertEquals(myOrderPattern.getPatternVar3(), "patternVar3");
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar4()}.
	 */
	@Test
	public final void testGetPatternVar4() {
		
		myOrderPattern.setPatternVar4("patternVar4");
		assertEquals(myOrderPattern.getPatternVar4(), "patternVar4");
		
	}

}
