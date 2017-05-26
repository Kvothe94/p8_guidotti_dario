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

import patterns.DurationPattern;
import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class DurationPatternTest {

	private DurationPattern myDurationPattern;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,2},
				{1,2},
				{2,-1}
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
		myDurationPattern = new DurationPattern();
	}

	/**
	 * Test method for {@link patterns.DurationPattern#getPatternClass()}.
	 */
	@Test
	public final void testGetPatternClass() {
		assertEquals(myDurationPattern.getPatternClass(), 0);
	}

	/**
	 * Test method for {@link patterns.DurationPattern#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myDurationPattern.setType(ptype);
		assertEquals(myDurationPattern.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.DurationPattern#asString()}.
	 */
	@Test
	public final void testAsString() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myDurationPattern.setType(1);
		myDurationPattern.setScope(auxScope);
		myDurationPattern.setPatternVar1("patternVar1");
		myDurationPattern.setPatternVar2("patternVar2");
		assertNotNull(myDurationPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.DurationPattern#asString()}.
	 */
	@Test
	public final void testAsStringTypeError() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myDurationPattern.setType(50);
		assertNull(myDurationPattern.asString());
		
	}

	/**
	 * Test method for {@link patterns.DurationPattern#DurationPattern()}.
	 */
	@Test
	public final void testDurationPattern() {
		DurationPattern auxDP = new DurationPattern();
		assertNotNull(auxDP);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getType()}.
	 */
	@Test
	public final void testGetType() {
		myDurationPattern.setType(1);
		assertEquals(myDurationPattern.getType(), 1);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getScope()}.
	 */
	@Test
	public final void testGetScope() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		myDurationPattern.setScope(auxScope);
		assertEquals(auxScope, myDurationPattern.getScope());
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar1()}.
	 */
	@Test
	public final void testGetPatternVar1() {
		
		myDurationPattern.setPatternVar1("patternVar1");
		assertEquals(myDurationPattern.getPatternVar1(), "patternVar1");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar2()}.
	 */
	@Test
	public final void testGetPatternVar2() {
	
		myDurationPattern.setPatternVar2("patternVar2");
		assertEquals(myDurationPattern.getPatternVar2(), "patternVar2");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar3()}.
	 */
	@Test
	public final void testGetPatternVar3() {

		myDurationPattern.setPatternVar3("patternVar3");
		assertEquals(myDurationPattern.getPatternVar3(), "patternVar3");
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar4()}.
	 */
	@Test
	public final void testGetPatternVar4() {
		
		myDurationPattern.setPatternVar4("patternVar4");
		assertEquals(myDurationPattern.getPatternVar4(), "patternVar4");
		
	}

}
