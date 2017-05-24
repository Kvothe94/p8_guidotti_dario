import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Patterns.DurationPattern;
import Patterns.FSPattern;
import Patterns.OccurencePattern;
import Patterns.OrderPattern;
import Patterns.PeriodicPattern;
import Patterns.RTOrderPattern;
import Patterns.Scope;

/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public class Main3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String reqs[] = {"After {Power-up} becomes true, {Tapproaching} shall {Req1} be set to false.",
				"After {Power-up} becomes true, {Tin} shall {Req2} be set to false.",
				"After {Power-up} becomes true, {Tleave} shall {Req3} be set to false.",
				"After {Power-up} becomes true, {Gopen} shall {Req4} be set to true.",
				"When {Tdistance} is less than 3000, {Tapproaching} shall {Req5} be set to true.",
				"If {Tapproaching} is true, then {Gopen} shall {Req6} be set to false.",
				"If {Tdistance} is less than 1000, {Tin} shall {Req7} be set to true.",
				"After {Tin} becomes true, when {Tdistance} is greater than 3000, {Tleave} shall {Req8} be set to true.",
				"If {Tleave} is true, then {Gopen} shall {Req9} be set to true.",
				"When {Tdistance} is greater than 10000, {Tleave} shall {Req10} be set to false.",
				"When {Tdistance} is greater than 10000, {Tin} shall {Req11} be set to false.",
				"When {Tdistance} is greater than 10000, {Tapproaching} shall {Req12} be set to false.",
				"After {Power-up} becomes true until {Tdistance} is greater or equal than 10000, {Gopen} shall {Req4} be set to true."};
		
		int indexMod[] = {36, 36, 36, 36, 9, 9, 9, 41, 9, 9, 9, 9, 68};
		
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
		
		Iterator<FSPattern> myIter = modelList.iterator();
		int k = 0;
		while(myIter.hasNext()){
			System.out.println(k + ": " + myIter.next().asString());
			k++;
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		List<FSPattern> myPatterns = new ArrayList<FSPattern>();
		Context myContext = new Context("http://protege.stanford.edu/ontologies/pizza/pizza.owl",
				"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		AssociationStanfordStrategy myStrategy = new AssociationStanfordStrategy(myContext, modelList);
		
		float DEL_COST = 0;
		float INS_COST = 0;
		float REN_COST = 0;
		int bestPoint = 0;
		int auxPoint = 0;
		int counter = 0;
		for(float i = 0; i < 10; i += 1){
			
			for(float j = 0; j < 10; j += 1){
				
				for(float l = 0; l < 10; l += 1){
					
					auxPoint = 0;
					for (int n = 0; n < reqs.length; n++) {
						Requirement auxReq = new Requirement();
						auxReq.setId(n);
						auxReq.setRequirement(reqs[n]);
						if(myStrategy.testVis(auxReq, i, j, l) == indexMod[n]){
							auxPoint++;
						}
					}
					
					System.out.println(counter++);
					if(auxPoint > bestPoint){
						DEL_COST = i;
						INS_COST = j;
						REN_COST = l;
						bestPoint = auxPoint;
					}
					
				}
				
			}
			
		}
		
		System.out.println("DEL_COST = " + DEL_COST);
		System.out.println("INS_COST = " + INS_COST);
		System.out.println("REN_COST = " + REN_COST);
		System.out.println("POINT = " + bestPoint);
		
		
		
		
		
		
		
		/*
		
		
		
		for (int i = 0; i < reqs.length; i++) {
			Requirement auxReq = new Requirement();
			auxReq.setId(i);
			auxReq.setRequirement(reqs[i]);
			myPatterns.add(myStrategy.associatePattern(auxReq));
		}
		
		myIter = myPatterns.iterator();
		while(myIter.hasNext()){
			System.out.println(k + ": " + myIter.next().asString());
			k++;
		}
		*/
		

	}

}
