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

import patterns.RTOrderPattern;
import patterns.Scope;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class RTOrderPatternTest {

	RTOrderPattern myRTOrderPattern;
	
	@Parameters
	public static Collection<Object[]> data(){
		
		Object[][] data = {
				{0,3},
				{1,3},
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
		myRTOrderPattern = new RTOrderPattern();
	}

	/**
	 * Test method for {@link patterns.RTOrderPattern#getPatternClass()}.
	 */
	@Test
	public final void testGetPatternClass() {
		assertEquals(myRTOrderPattern.getPatternClass(), 4);
	}

	/**
	 * Test method for {@link patterns.RTOrderPattern#getNumVar()}.
	 */
	@Test
	public final void testGetNumVar() {
		
		myRTOrderPattern.setType(ptype);
		assertEquals(myRTOrderPattern.getNumVar(), pnumVar);
		
	}

	/**
	 * Test method for {@link patterns.RTOrderPattern#asString()}.
	 */
	@Test
	public final void testAsString() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myRTOrderPattern.setScope(auxScope);
		myRTOrderPattern.setPatternVar1("patternVar1");
		myRTOrderPattern.setPatternVar2("patternVar2");
		myRTOrderPattern.setPatternVar3("patternVar3");
		myRTOrderPattern.setPatternVar4("patternVar4");
		
		myRTOrderPattern.setType(0);
		assertNotNull(myRTOrderPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.RTOrderPattern#asString()}.
	 */
	@Test
	public final void testAsStringTypeError() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		
		myRTOrderPattern.setType(50);
		assertNull(myRTOrderPattern.asString());
		
	}
	
	/**
	 * Test method for {@link patterns.RTOrderPattern#asString()}.
	 */
	@Test
	public final void testAsStringScopeError() {
		
		Scope auxScope = new Scope();
		
		auxScope.setType(6);
		myRTOrderPattern.setScope(auxScope);
		myRTOrderPattern.setPatternVar1("patternVar1");
		myRTOrderPattern.setPatternVar2("patternVar2");
		myRTOrderPattern.setPatternVar3("patternVar3");
		myRTOrderPattern.setPatternVar4("patternVar4");
		myRTOrderPattern.setType(0);
		assertNull(myRTOrderPattern.asString());
		
		
	}

	/**
	 * Test method for {@link patterns.RTOrderPattern#RTOrderPattern()}.
	 */
	@Test
	public final void testRTOrderPattern() {
		RTOrderPattern auxDP = new RTOrderPattern();
		assertNotNull(auxDP);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getType()}.
	 */
	@Test
	public final void testGetType() {
		myRTOrderPattern.setType(1);
		assertEquals(myRTOrderPattern.getType(), 1);
	}

	/**
	 * Test method for {@link patterns.FSPattern#getScope()}.
	 */
	@Test
	public final void testGetScope() {
		
		Scope auxScope = new Scope();
		auxScope.setType(0);
		myRTOrderPattern.setScope(auxScope);
		assertEquals(auxScope, myRTOrderPattern.getScope());
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar1()}.
	 */
	@Test
	public final void testGetPatternVar1() {
		
		myRTOrderPattern.setPatternVar1("patternVar1");
		assertEquals(myRTOrderPattern.getPatternVar1(), "patternVar1");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar2()}.
	 */
	@Test
	public final void testGetPatternVar2() {
	
		myRTOrderPattern.setPatternVar2("patternVar2");
		assertEquals(myRTOrderPattern.getPatternVar2(), "patternVar2");
	
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar3()}.
	 */
	@Test
	public final void testGetPatternVar3() {

		myRTOrderPattern.setPatternVar3("patternVar3");
		assertEquals(myRTOrderPattern.getPatternVar3(), "patternVar3");
		
	}

	/**
	 * Test method for {@link patterns.FSPattern#getPatternVar4()}.
	 */
	@Test
	public final void testGetPatternVar4() {
		
		myRTOrderPattern.setPatternVar4("patternVar4");
		assertEquals(myRTOrderPattern.getPatternVar4(), "patternVar4");
		
	}

}
