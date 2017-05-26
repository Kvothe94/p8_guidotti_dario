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

import patterns.OccurencePattern;
import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class OccurencePatternTest {

	OccurencePattern myOccurencePattern;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,1},
				{1,1},
				{2,1},
				{3,1},
				{4,-1},
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
		myOccurencePattern = new OccurencePattern();
	}

	/**
	 * Test method for {@link patterns.OccurencePattern#getPatternClass()}.
	 */
	@Test
	public final void testGetPatternClass() {
		assertEquals(myOccurencePattern.getPatternClass(), 1);
	}

	/**
	 * Test method for {@link patterns.OccurencePattern#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myOccurencePattern.setType(ptype);
		assertEquals(myOccurencePattern.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.OccurencePattern#asString()}.
	 */
	@Test
	public final void testAsString() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myOccurencePattern.setType(1);
		myOccurencePattern.setScope(auxScope);
		myOccurencePattern.setPatternVar1("patternVar1");
		assertNotNull(myOccurencePattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.DurationPattern#asString()}.
	 */
	@Test
	public final void testAsStringTypeError() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myOccurencePattern.setType(50);
		assertNull(myOccurencePattern.asString());
		
	}

	/**
	 * Test method for {@link patterns.OccurencePattern#OccurencePattern()}.
	 */
	@Test
	public final void testOccurencePattern() {
		OccurencePattern auxDP = new OccurencePattern();
		assertNotNull(auxDP);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getType()}.
	 */
	@Test
	public final void testGetType() {
		myOccurencePattern.setType(1);
		assertEquals(myOccurencePattern.getType(), 1);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getScope()}.
	 */
	@Test
	public final void testGetScope() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		myOccurencePattern.setScope(auxScope);
		assertEquals(auxScope, myOccurencePattern.getScope());
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar1()}.
	 */
	@Test
	public final void testGetPatternVar1() {
		
		myOccurencePattern.setPatternVar1("patternVar1");
		assertEquals(myOccurencePattern.getPatternVar1(), "patternVar1");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar2()}.
	 */
	@Test
	public final void testGetPatternVar2() {
	
		myOccurencePattern.setPatternVar2("patternVar2");
		assertEquals(myOccurencePattern.getPatternVar2(), "patternVar2");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar3()}.
	 */
	@Test
	public final void testGetPatternVar3() {

		myOccurencePattern.setPatternVar3("patternVar3");
		assertEquals(myOccurencePattern.getPatternVar3(), "patternVar3");
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar4()}.
	 */
	@Test
	public final void testGetPatternVar4() {
		
		myOccurencePattern.setPatternVar4("patternVar4");
		assertEquals(myOccurencePattern.getPatternVar4(), "patternVar4");
		
	}

}
