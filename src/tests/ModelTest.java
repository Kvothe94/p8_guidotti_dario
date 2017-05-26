/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import main.AssociationStanfordStrategy;
import main.Context;
import main.Requirement;
import patterns.*;

/**
 * @author Guidotti Dario
 * @version 1.0
 *
 */
@RunWith(Parameterized.class)
public class ModelTest {
	
	private static AssociationStanfordStrategy myStrategy;
	private static List<FSPattern> modelList;
	private static List<Requirement> reqList;
	
	@Parameters
	public static Collection<Object[]> data() {
		
		modelList = new ArrayList<FSPattern>();
		reqList = new ArrayList<Requirement>();
		int id = 0;
		
		for(int i = 0; i <= 4; i++) {
			
			Scope auxScope = new Scope();
			auxScope.setType(i);
			auxScope.setScopeVar1("{R>=0}");
			auxScope.setScopeVar2("{Q==1}");
			
			Scope reqScope = new Scope();
			reqScope.setType(i);
			reqScope.setScopeVar1("{R} is greater or equal than 0");
			reqScope.setScopeVar2("{Q} is false");
			
			for(int j = 0; j <= 4; j++) {
				
				FSPattern reqPattern = null;
				FSPattern auxReq = null;
				Requirement req = null;
				
				switch (j) {
				case 0:
			
					for(int m = 0; m < 2; m++) {
						
						reqPattern = new DurationPattern();
						reqPattern.setPatternVar1("{P<100}");
						reqPattern.setPatternVar2("{S>150.0}");
						reqPattern.setPatternVar3("{T==10.50}");
						reqPattern.setPatternVar4("{Z==1}");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
						
						auxReq = new DurationPattern();
						auxReq.setPatternVar1("{P} is less than 100");
						auxReq.setPatternVar2("{S} is greater than 150.0");
						auxReq.setPatternVar3("{T} is equal to 10.50");
						auxReq.setPatternVar4("{Z} is true");
						auxReq.setType(m);
						auxReq.setScope(reqScope);
						req = new Requirement();
						req.setRequirement(auxReq.asString());
						req.setId(id++);
						reqList.add(req);
						
					}
					break;
					
				case 1:
					for(int m = 0; m < 4; m++) { 
						
						reqPattern = new OccurencePattern();
						reqPattern.setPatternVar1("{P<100}");
						reqPattern.setPatternVar2("{S>150.0}");
						reqPattern.setPatternVar3("{T==10.50}");
						reqPattern.setPatternVar4("{Z==1}");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
						
						auxReq = new OccurencePattern();
						auxReq.setPatternVar1("{P} is less than 100");
						auxReq.setPatternVar2("{S} is greater than 150.0");
						auxReq.setPatternVar3("{T} is equal to 10.50");
						auxReq.setPatternVar4("{Z} is true");
						auxReq.setType(m);
						auxReq.setScope(reqScope);
						req = new Requirement();
						req.setRequirement(auxReq.asString());
						req.setId(id++);
						reqList.add(req);
						
					}
					break;
					
				case 2:
					for(int m = 0; m < 7; m++) {
						
						reqPattern = new OrderPattern();
						reqPattern.setPatternVar1("{P<100}");
						reqPattern.setPatternVar2("{S>150.0}");
						reqPattern.setPatternVar3("{T==10.50}");
						reqPattern.setPatternVar4("{Z==1}");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
						
						auxReq = new OrderPattern();
						auxReq.setPatternVar1("{P} is less than 100");
						auxReq.setPatternVar2("{S} is greater than 150.0");
						auxReq.setPatternVar3("{T} is equal to 10.50");
						auxReq.setPatternVar4("{Z} is true");
						auxReq.setType(m);
						auxReq.setScope(reqScope);
						req = new Requirement();
						req.setRequirement(auxReq.asString());
						req.setId(id++);
						reqList.add(req);
						
					}
					break;
					
				case 3:
					
					reqPattern = new PeriodicPattern();
					reqPattern.setPatternVar1("{P<100}");
					reqPattern.setPatternVar2("{S>150.0}");
					reqPattern.setPatternVar3("{T==10.50}");
					reqPattern.setPatternVar4("{Z==1}");
					reqPattern.setType(0);
					reqPattern.setScope(auxScope);
					modelList.add(reqPattern);
					
					auxReq = new PeriodicPattern();
					auxReq.setPatternVar1("{P} is less than 100");
					auxReq.setPatternVar2("{S} is greater than 150.0");
					auxReq.setPatternVar3("{T} is equal to 10.50");
					auxReq.setPatternVar4("{Z} is true");
					auxReq.setType(0);
					auxReq.setScope(reqScope);
					req = new Requirement();
					req.setRequirement(auxReq.asString());
					req.setId(id++);
					reqList.add(req);
					
					break;
					
				case 4:
					
					for(int m = 0; m < 2; m++) {
						
						reqPattern = new RTOrderPattern();
						reqPattern.setPatternVar1("{P<100}");
						reqPattern.setPatternVar2("{S>150.0}");
						reqPattern.setPatternVar3("{T==10.50}");
						reqPattern.setPatternVar4("{Z==1}");
						reqPattern.setType(m);
						reqPattern.setScope(auxScope);
						modelList.add(reqPattern);
						
						auxReq = new RTOrderPattern();
						auxReq.setPatternVar1("{P} is less than 100");
						auxReq.setPatternVar2("{S} is greater than 150.0");
						auxReq.setPatternVar3("{T} is equal to 10.50");
						auxReq.setPatternVar4("{Z} is true");
						auxReq.setType(m);
						auxReq.setScope(reqScope);
						req = new Requirement();
						req.setRequirement(auxReq.asString());
						req.setId(id++);
						reqList.add(req);
						
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
			// TODO: handle exception
			e.printStackTrace();
		}
		
		myStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		Object[][] data = new String[80][2];
		for(int i = 0; i < modelList.size(); i++){
			
			data[i][0] = myStrategy.associatePattern(reqList.get(i)).asString();
			data[i][1] = modelList.get(i).asString();
			
		}
		
		return Arrays.asList(data);
		
	}
	
	@Parameter(0) public String result;
	@Parameter(1) public String expResult;
	
	/**
	 * Test method for {@link main.AssociationStanfordStrategy#associatePattern(main.Requirement)}.
	 */
	@Test
	public final void testAssociatePattern() {
			
		System.out.println("Mod: " + expResult);
		//auxPattern = myStrategy.associatePattern(reqList.get(i));
		System.out.println("Req: " + result);
		System.out.println("");
		assertEquals(expResult, result);
		
	}

}
