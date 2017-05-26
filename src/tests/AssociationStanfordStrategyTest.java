/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ScoredObject;
import patterns.*;
import main.AssociationStanfordStrategy;
import main.Context;
import main.Requirement;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class AssociationStanfordStrategyTest {
	
	private AssociationStanfordStrategy myStrategy;
	private List<Requirement> reqs;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		reqs = new ArrayList<Requirement>();
		
		for(int i = 0; i <= 4; i++){
			
			Scope auxScope = new Scope();
			auxScope.setType(i);
			auxScope.setScopeVar1("R");
			auxScope.setScopeVar2("Q");
			
			for(int j = 0; j <= 4; j++) {
				
				FSPattern reqPattern = null;
				switch (j) {
				case 0:
			
					for(int m = 0; m < 2; m++){
						reqPattern = new DurationPattern();
						reqPattern.setPatternVar1("P");
						reqPattern.setPatternVar2("S");
						reqPattern.setPatternVar3("T");
						reqPattern.setPatternVar4("Z");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
					}
					break;
					
				case 1:
					for(int m = 0; m < 4; m++){
						reqPattern = new OccurencePattern();
						reqPattern.setPatternVar1("P");
						reqPattern.setPatternVar2("S");
						reqPattern.setPatternVar3("T");
						reqPattern.setPatternVar4("Z");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
					}
					break;
					
				case 2:
					for(int m = 0; m < 7; m++){
						reqPattern = new OrderPattern();
						reqPattern.setPatternVar1("P");
						reqPattern.setPatternVar2("S");
						reqPattern.setPatternVar3("T");
						reqPattern.setPatternVar4("Z");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
					}
					break;
					
				case 3:
					reqPattern = new PeriodicPattern();
					reqPattern.setPatternVar1("P");
					reqPattern.setPatternVar2("S");
					reqPattern.setPatternVar3("T");
					reqPattern.setPatternVar4("Z");
					reqPattern.setType(0);
					reqPattern.setScope(auxScope);
					modelList.add(reqPattern);
					break;
					
				case 4:
					for(int m = 0; m < 2; m++){
						reqPattern = new RTOrderPattern();
						reqPattern.setPatternVar1("P");
						reqPattern.setPatternVar2("S");
						reqPattern.setPatternVar3("T");
						reqPattern.setPatternVar4("Z");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
					}
					break;

				default:
					break;
				}
				
			}
			
		}
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myStrategy = new AssociationStanfordStrategy(myContext, modelList);
		String[] strReqs = {
			
			"After {P} is less or equal than 10 until {S} is equal to 1," + 
					" it is always the case that nothing holds and for no reason" +
					" this requirement shall ever be correct",
			"After asd is less or equal than 10 until asd is equal to 1, asd",
			
		};
		
		int i = 0;
		for (String string : strReqs) {
			Requirement aux = new Requirement();
			aux.setId(i++);
			aux.setRequirement(string);
			reqs.add(aux);
		}
		
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#AssociationStanfordStrategy(main.Context, java.util.List)}.
	 */
	@Test
	public final void testAssociationStanfordStrategy() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		assertNotNull(auxStrategy);
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#getReqTrees()}.
	 */
	@Test
	public final void testGetReqTrees() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		assertNotNull(auxStrategy);
		
		List<ScoredObject<Tree>> auxTrees = new ArrayList<ScoredObject<Tree>>();
		auxStrategy.setReqTrees(auxTrees);
		assertNotNull(auxStrategy.getReqTrees());
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#setReqTrees(java.util.List)}.
	 */
	@Test
	public final void testSetReqTrees() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		assertNotNull(auxStrategy);
		
		List<ScoredObject<Tree>> auxTrees = new ArrayList<ScoredObject<Tree>>();
		auxStrategy.setReqTrees(auxTrees);
		assertNotNull(auxStrategy.getReqTrees());
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#getModelTrees()}.
	 */
	@Test
	public final void testGetModelTrees() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		assertNotNull(auxStrategy);
		
		auxStrategy.setModelTrees(null);
		assertNull(auxStrategy.getModelTrees());
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#setModelTrees(java.util.List)}.
	 */
	@Test
	public final void testSetModelTrees() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		assertNotNull(auxStrategy);
		
		auxStrategy.setModelTrees(null);
		assertNull(auxStrategy.getModelTrees());
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#getLexParser()}.
	 */
	@Test
	public final void testGetLexParser() {
		assertNotNull(myStrategy.getLexParser());
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#setLexParser(edu.stanford.nlp.parser.lexparser.LexicalizedParser)}.
	 */
	@Test
	public final void testSetLexParser() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		auxStrategy.setLexParser(null);
		assertNull(auxStrategy.getLexParser());
		
	}

	/**
	 * Test method for {@link main.AssociationStrategy#getContext()}.
	 */
	@Test
	public final void testGetContext() {
		assertNotNull(myStrategy.getContext());
	}

	/**
	 * Test method for {@link main.AssociationStrategy#setContext(main.Context)}.
	 */
	@Test
	public final void testSetContext() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		auxStrategy.setContext(null);
		assertNull(auxStrategy.getContext());
		
	}

	/**
	 * Test method for {@link main.AssociationStrategy#getPatternsModel()}.
	 */
	@Test
	public final void testGetPatternsModel() {
		assertNotNull(myStrategy.getPatternsModel());
	}

	/**
	 * Test method for {@link main.AssociationStrategy#setPatternsModel(java.util.List)}.
	 */
	@Test
	public final void testSetPatternsModel() {
		
		Context myContext = null;
		try {
			myContext = new Context(
					"http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
		AssociationStanfordStrategy auxStrategy = new AssociationStanfordStrategy(myContext, modelList);
		auxStrategy.setPatternsModel(null);
		assertNull(auxStrategy.getPatternsModel());
		
	}

	/**
	 * Test method for {@link main.AssociationStanfordStrategy#associatePattern(main.Requirement)}.
	 */
	@Test
	public final void testAssociatePattern() {
		
		for(int i = 0; i < reqs.size(); i++){
			assertNull(myStrategy.associatePattern(reqs.get(i)).asString());
		}
		
	}

}
