import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.OntProperty;

import Patterns.DurationPattern;
import Patterns.OccurencePattern;
import Patterns.OrderPattern;
import Patterns.Pattern;
import Patterns.PeriodicPattern;
import Patterns.RTOrderPattern;
import Patterns.Scope;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ScoredObject;

public class Main {

	public static void main(String[] args) {
		
		//Prova Scope
		Scope myScope = new Scope();
		myScope.setType(4);
		myScope.setScopeVar1("sA");
		myScope.setScopeVar2("sB");
		System.out.println(myScope.asString());
		
		//Prova OccurencePattern
		OccurencePattern myOccurencePattern = new OccurencePattern();
		myOccurencePattern.setType(0);
		myOccurencePattern.setPatternVar1("A");
		myOccurencePattern.setScope(myScope);
		System.out.println(myOccurencePattern.asString());
		
		//Prova OrderPattern
		OrderPattern myOrderPattern = new OrderPattern();
		myOrderPattern.setType(6);
		myOrderPattern.setPatternVar1("A");
		myOrderPattern.setPatternVar2("B");
		myOrderPattern.setPatternVar3("C");
		myOrderPattern.setPatternVar4("D");
		myOrderPattern.setScope(myScope);
		System.out.println(myOrderPattern.asString());
		
		//Prova DurationPattern
		DurationPattern myDurationPattern = new DurationPattern();
		myDurationPattern.setType(1);
		myDurationPattern.setPatternVar1("A");
		myDurationPattern.setPatternVar2("B");
		myDurationPattern.setScope(myScope);
		System.out.println(myDurationPattern.asString());
		
		//Prova PeriodicPattern
		PeriodicPattern myPeriodicPattern = new PeriodicPattern();
		myPeriodicPattern.setType(0);
		myPeriodicPattern.setPatternVar1("A");
		myPeriodicPattern.setPatternVar2("B");
		myPeriodicPattern.setScope(myScope);
		System.out.println(myPeriodicPattern.asString());
		
		//Prova RTOrderPattern
		RTOrderPattern myRTOrderPattern = new RTOrderPattern();
		myRTOrderPattern.setType(1);
		myRTOrderPattern.setPatternVar1("A");
		myRTOrderPattern.setPatternVar2("B");
		myRTOrderPattern.setPatternVar3("C");
		myRTOrderPattern.setScope(myScope);
		System.out.println(myOrderPattern.asString());

		//Prova Context
		Context myContext = new Context("http://protege.stanford.edu/ontologies/pizza/pizza.owl",
				"http://www.co-ode.org/ontologies/pizza/pizza.owl#");
		
		if(myContext.isResource("IceCream")){
			System.out.println("è risorsa");
		}
		
		if(myContext.isEntity("IceCream")){
			System.out.println("è entità");
		}
		
		if(myContext.isDomain("Pizza")){
			System.out.println("è dominio");
		}
		
		if(myContext.isRange("Food")){
			System.out.println("è range");
		}
		
		List<OntProperty> myList = myContext.getProperties("Pizza", "PizzaTopping");
		Iterator<OntProperty> myIter = myList.iterator();
		while(myIter.hasNext()){
			System.out.println(myIter.next().toString());
		}
		
		//Prova AssociationStanfordStrategy
		ArrayList<Pattern> patternsModel = new ArrayList<Pattern>();
		patternsModel.add(myRTOrderPattern);
		patternsModel.add(myDurationPattern);
		patternsModel.add(myOccurencePattern);
		patternsModel.add(myOrderPattern);
		patternsModel.add(myPeriodicPattern);
		AssociationStanfordStrategy myStrategy = new AssociationStanfordStrategy(myContext, patternsModel);
		Requirement myRequirement = new Requirement();
		myRequirement.setId(0);
		myRequirement.setRequirement("When {Tdistance} is greater than 10000, {Tleave} shall {Req10} be set to false.");
		myStrategy.associatePattern(myRequirement);
		Iterator<ScoredObject<Tree>> myTreesIter = myStrategy.getReqTrees().iterator();
		
		while(myTreesIter.hasNext()){
		
			myTreesIter.next().object().pennPrint();
		
		}
		
		System.out.println("Single Tree Print:");
		myTreesIter = myStrategy.getReqTrees().iterator();
		Tree myTree = myTreesIter.next().object();
		List<Tree> leaves = myTree.getLeaves();
		Iterator<Tree> iterLeaves = leaves.iterator();
		while(iterLeaves.hasNext()){
			Tree auxTree = iterLeaves.next();
			auxTree.ancestor(1, myTree).pennPrint();
			
		}
		
		
		
		
		
		
		
		
	}

}
