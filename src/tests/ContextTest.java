/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import main.Context;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class ContextTest {

	private Context myContext;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		myContext = new Context(
				"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
				"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		
	}

	/**
	 * Test method for {@link main.Context#Context(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testContext() {
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertNotNull(auxContext);
		
	}
	
	/**
	 * Test method for {@link main.Context#Context(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public final void testContextFileError() {
		
		try {
			@SuppressWarnings("unused")
			Context auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "inexistentFile");
		} catch (Exception e) {
			assertTrue(e.getClass() == FileNotFoundException.class);
		}
		
		
	}

	/**
	 * Test method for {@link main.Context#getOntPath()}.
	 */
	@Test
	public final void testGetOntPath() {
		
		assertEquals(myContext.getOntPath(),
				"http://protege.stanford.edu/ontologies/pizza/pizza.owl");
		
	}

	/**
	 * Test method for {@link main.Context#setOntPath(java.lang.String)}.
	 */
	@Test
	public final void testSetOntPath() {

		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setOntPath("newOntPath");
		assertEquals(auxContext.getOntPath(), "newOntPath");
		
	}

	/**
	 * Test method for {@link main.Context#getOntModel()}.
	 */
	@Test
	public final void testGetOntModel() {
		
		OntModel newOntModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM );
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setOntModel(newOntModel);
		assertEquals(newOntModel, auxContext.getOntModel());
		
		
		
	}

	/**
	 * Test method for {@link main.Context#setOntModel(org.apache.jena.ontology.OntModel)}.
	 */
	@Test
	public final void testSetOntModel() {
		
		OntModel newOntModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM );
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setOntModel(newOntModel);
		assertEquals(newOntModel, auxContext.getOntModel());
		
	}

	/**
	 * Test method for {@link main.Context#getOntNS()}.
	 */
	@Test
	public final void testGetOntNS() {
		assertEquals(myContext.getOntNS(), "http://www.co-ode.org/ontologies/pizza/pizza.owl#");
	}

	/**
	 * Test method for {@link main.Context#setOntNS(java.lang.String)}.
	 */
	@Test
	public final void testSetOntNS() {
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setOntNS("newNS");
		assertEquals(auxContext.getOntNS(), "newNS");
	}

	/**
	 * Test method for {@link main.Context#getSignalMap()}.
	 */
	@Test
	public final void testGetSignalMap() {
		
		HashMap<String, String> signalMap = new HashMap<String, String>();
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setSignalMap(signalMap);
		assertEquals(auxContext.getSignalMap(), signalMap);
		
	}

	/**
	 * Test method for {@link main.Context#setSignalMap(java.util.HashMap)}.
	 */
	@Test
	public final void testSetSignalMap() {

		HashMap<String, String> signalMap = new HashMap<String, String>();
		
		Context auxContext = null;
		try {
			auxContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		auxContext.setSignalMap(signalMap);
		assertEquals(auxContext.getSignalMap(), signalMap);
		
	}

	/**
	 * Test method for {@link main.Context#isResource(java.lang.String)}.
	 */
	@Test
	public final void testIsResource() {
		
		assertTrue(myContext.isResource("Pizza"));
		
	}
	
	/**
	 * Test method for {@link main.Context#isResource(java.lang.String)}.
	 */
	@Test
	public final void testIsResourceError() {
		
		assertFalse(myContext.isResource("dipinto"));
		
	}

	/**
	 * Test method for {@link main.Context#isEntity(java.lang.String)}.
	 */
	@Test
	public final void testIsEntity() {
		assertTrue(myContext.isResource("Pizza"));
	}
	
	/**
	 * Test method for {@link main.Context#isEntity(java.lang.String)}.
	 */
	@Test
	public final void testIsEntityError() {
		assertFalse(myContext.isResource("dipinto"));
	}

	/**
	 * Test method for {@link main.Context#isProperty(java.lang.String)}.
	 */
	@Test
	public final void testIsProperty() {
		assertTrue(myContext.isProperty("hasTopping"));
	}
	
	/**
	 * Test method for {@link main.Context#isProperty(java.lang.String)}.
	 */
	@Test
	public final void testIsPropertyError() {
		assertFalse(myContext.isProperty("hasBanana"));
	}

	/**
	 * Test method for {@link main.Context#isDomain(java.lang.String)}.
	 */
	@Test
	public final void testIsDomain() {
		assertTrue(myContext.isDomain("Pizza"));
	}
	
	/**
	 * Test method for {@link main.Context#isDomain(java.lang.String)}.
	 */
	@Test
	public final void testIsDomainError() {
		assertFalse(myContext.isDomain("Dipinto"));
	}

	/**
	 * Test method for {@link main.Context#isRange(java.lang.String)}.
	 */
	@Test
	public final void testIsRange() {
		assertTrue(myContext.isRange("PizzaTopping"));
	}
	
	/**
	 * Test method for {@link main.Context#isRange(java.lang.String)}.
	 */
	@Test
	public final void testIsRangeError() {
		assertFalse(myContext.isRange("Dipinto"));
	}

	/**
	 * Test method for {@link main.Context#getProperties(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetProperties() {
		assertNotNull(myContext.getProperties("Pizza", "PizzaTopping"));
	}

	/**
	 * Test method for {@link main.Context#getProperty(java.lang.String)}.
	 */
	@Test
	public final void testGetProperty() {
		assertNotNull(myContext.getProperty("hasTopping"));
	}

	/**
	 * Test method for {@link main.Context#getResource(java.lang.String)}.
	 */
	@Test
	public final void testGetResource() {
		assertNotNull(myContext.getResource("Pizza"));
	}

	/**
	 * Test method for {@link main.Context#getEntity(java.lang.String)}.
	 */
	@Test
	public final void testGetEntity() {
		assertNotNull(myContext.getEntity("Pizza"));
	}

}
