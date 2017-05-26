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

import patterns.PeriodicPattern;
import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class PeriodicPatternTest {

	PeriodicPattern myPeriodicPattern;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,2},
				{1,-1},
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
		myPeriodicPattern = new PeriodicPattern();
	}

	/**
	 * Test method for {@link patterns.PeriodicPattern#getPatternClass()}.
	 */
	@Test
	public final void testGetPatternClass() {
		assertEquals(myPeriodicPattern.getPatternClass(), 3);
	}

	/**
	 * Test method for {@link patterns.PeriodicPattern#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myPeriodicPattern.setType(ptype);
		assertEquals(myPeriodicPattern.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.PeriodicPattern#asString()}.
	 */
	@Test
	public final void testAsString() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myPeriodicPattern.setScope(auxScope);
		myPeriodicPattern.setPatternVar1("patternVar1");
		myPeriodicPattern.setPatternVar2("patternVar2");
		myPeriodicPattern.setPatternVar3("patternVar3");
		myPeriodicPattern.setPatternVar4("patternVar4");
		
		myPeriodicPattern.setType(0);
		assertNotNull(myPeriodicPattern.asString());
		
		
	}
	
	/**
	 * Test method for {@link patterns.PeriodicPattern#asString()}.
	 */
	@Test
	public final void testAsStringTypeError() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myPeriodicPattern.setType(50);
		assertNull(myPeriodicPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.PeriodicPattern#asString()}.
	 */
	@Test
	public final void testAsStringScopeError() {
		
		Scope auxScope = new Scope();
		
		auxScope.setType(6);
		myPeriodicPattern.setScope(auxScope);
		myPeriodicPattern.setPatternVar1("patternVar1");
		myPeriodicPattern.setPatternVar2("patternVar2");
		myPeriodicPattern.setPatternVar3("patternVar3");
		myPeriodicPattern.setPatternVar4("patternVar4");
		myPeriodicPattern.setType(0);
		assertNull(myPeriodicPattern.asString());
		
		
	}

	/**
	 * Test method for {@link patterns.PeriodicPattern#PeriodicPattern()}.
	 */
	@Test
	public final void testPeriodicPattern() {
		PeriodicPattern auxDP = new PeriodicPattern();
		assertNotNull(auxDP);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getType()}.
	 */
	@Test
	public final void testGetType() {
		myPeriodicPattern.setType(1);
		assertEquals(myPeriodicPattern.getType(), 1);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getScope()}.
	 */
	@Test
	public final void testGetScope() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		myPeriodicPattern.setScope(auxScope);
		assertEquals(auxScope, myPeriodicPattern.getScope());
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar1()}.
	 */
	@Test
	public final void testGetPatternVar1() {
		
		myPeriodicPattern.setPatternVar1("patternVar1");
		assertEquals(myPeriodicPattern.getPatternVar1(), "patternVar1");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar2()}.
	 */
	@Test
	public final void testGetPatternVar2() {
	
		myPeriodicPattern.setPatternVar2("patternVar2");
		assertEquals(myPeriodicPattern.getPatternVar2(), "patternVar2");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar3()}.
	 */
	@Test
	public final void testGetPatternVar3() {

		myPeriodicPattern.setPatternVar3("patternVar3");
		assertEquals(myPeriodicPattern.getPatternVar3(), "patternVar3");
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar4()}.
	 */
	@Test
	public final void testGetPatternVar4() {
		
		myPeriodicPattern.setPatternVar4("patternVar4");
		assertEquals(myPeriodicPattern.getPatternVar4(), "patternVar4");
		
	}

}
