/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import patterns.*;
import main.AssociationStanfordStrategy;
import main.Context;
import main.Requirement;
import visitors.TreeComparingVisitor;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class TreeComparingVisitorTest {

	private TreeComparingVisitor treeCompVis;
	private AssociationStanfordStrategy strategy;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		treeCompVis = new TreeComparingVisitor();
		
		List<FSPattern> modelList = new ArrayList<FSPattern>();
		
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
		
		Context myContext = new Context("http://protege.stanford.edu/ontologies/pizza/pizza.owl",
				"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		strategy = new AssociationStanfordStrategy(myContext, modelList);

		
	}

	/**
	 * Test method for {@link visitors.TreeComparingVisitor#visit(main.AssociationStanfordStrategy, int)}.
	 */
	@Test
	public final void testVisit() {
		
		Requirement auxReq = new Requirement();
		auxReq.setRequirement("After {Power-up} becomes true, " + 
				"{Tin} shall {Req2} be set to false.");
		auxReq.setId(0);
		
		strategy.associatePattern(auxReq);
		treeCompVis.visit(strategy, 0);
		assert(treeCompVis.getBestModelIndex() != -1);
		
	}

	/**
	 * Test method for {@link visitors.TreeComparingVisitor#TreeComparingVisitor()}.
	 */
	@Test
	public final void testTreeComparingVisitor() {
		
		TreeComparingVisitor myVis = new TreeComparingVisitor();
		assertNotNull(myVis);
		
	}

	/**
	 * Test method for {@link visitors.TreeComparingVisitor#TreeComparingVisitor(float, float, float)}.
	 */
	@Test
	public final void testTreeComparingVisitorFloatFloatFloat() {
		
		TreeComparingVisitor myVis = new TreeComparingVisitor(3, 3, 3);
		assertNotNull(myVis);
		
	}

	/**
	 * Test method for {@link visitors.TreeComparingVisitor#getBestModelIndex()}.
	 */
	@Test
	public final void testGetBestModelIndex() {
		
		treeCompVis.setBestModelIndex(5);
		assertEquals(treeCompVis.getBestModelIndex(), 5);
		
	}

	/**
	 * Test method for {@link visitors.TreeComparingVisitor#setBestModelIndex(int)}.
	 */
	@Test
	public final void testSetBestModelIndex() {
		
		treeCompVis.setBestModelIndex(5);
		assertEquals(treeCompVis.getBestModelIndex(), 5);
		
	}

}
