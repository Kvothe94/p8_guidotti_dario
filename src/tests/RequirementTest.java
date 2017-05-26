/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Requirement;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class RequirementTest {

	private Requirement myRequirement;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		myRequirement = new Requirement();
		
	}
	
	/**
	 * Test method for {@link main.Requirement#Requirement()}.
	 */
	@Test
	public final void testRequirement() {
		
		Requirement auxReq = new Requirement();
		assertNotNull(auxReq);
		
	}

	/**
	 * Test method for {@link main.Requirement#getId()}.
	 */
	@Test
	public final void testGetId() {
		myRequirement.setId(5);
		assertEquals(myRequirement.getId(), 5);
	}

	/**
	 * Test method for {@link main.Requirement#getRequirement()}.
	 */
	@Test
	public final void testGetRequirement() {
		myRequirement.setRequirement("Requirement");
		assertEquals(myRequirement.getRequirement(), "Requirement");
	}

}
