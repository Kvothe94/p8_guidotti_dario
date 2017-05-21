import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Patterns.FSPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.common.ParserQuery;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.util.ScoredObject;

public class Main2 {

	public static void main(String[] args) {
				
				//Verifica funzionamento parser stanford
				
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
		
		Pattern myPattern = Pattern.compile("\\{(([a-zA-z0-9]+[\\-]*[a-zA-z0-9]+)*)\\}");
		Matcher myMatcher = myPattern.matcher(reqs[12]);
		while(myMatcher.find()){
			System.out.println(myMatcher.group(0));
		}
		
		myPattern = Pattern.compile("(greater|less|equal)[(or equal)]* (than|to) ([0-9]+\\.*[0-9]*)");
		myMatcher = myPattern.matcher(reqs[12]);
		while(myMatcher.find()){
			System.out.println(myMatcher.group(0));
		}
		
		myPattern = Pattern.compile("(becomes|set to) (true|false)");
		myMatcher = myPattern.matcher(reqs[12]);
		while(myMatcher.find()){
			System.out.println(myMatcher.group(0));
		}
		
		myPattern = Pattern.compile("Req[0-9*]");
		myMatcher = myPattern.matcher(reqs[12]);
		while(myMatcher.find()){
			System.out.println(myMatcher.group(0));
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		Context myContext = new Context("http://protege.stanford.edu/ontologies/pizza/pizza.owl",
				"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
		ArrayList<FSPattern> patternsModel = new ArrayList<FSPattern>();
		AssociationStanfordStrategy myStrategy = new AssociationStanfordStrategy(myContext, patternsModel);
		
		List<String> vars;
		for(int i = 0; i < reqs.length; i++){
			
			System.out.println(reqs[i]);
			vars = myStrategy.constructVariables(reqs[i]);
			Iterator<String> myIter = vars.iterator();
			while(myIter.hasNext()){
				System.out.println(myIter.next());
			}
			
			System.out.println("");
			
		}
		
		
			/*	
			String parserModel = "englishPCFG.ser.gz";
			LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
			Context myContext = new Context("http://protege.stanford.edu/ontologies/pizza/pizza.owl",
					"http://www.co-ode.org/ontologies/pizza/pizza.owl#", "signals.csv");
			ArrayList<FSPattern> patternsModel = new ArrayList<FSPattern>();
			AssociationStanfordStrategy myStrategy = new AssociationStanfordStrategy(myContext, patternsModel);
			TreebankLanguagePack tlp = lp.getOp().langpack();
			for(int i = 0; i < reqs.length; i++){
					
				Tokenizer<? extends HasWord> toke =
				        tlp.getTokenizerFactory().getTokenizer(new StringReader(reqs[i]));
					
				List<? extends HasWord> mySentence = toke.tokenize();
				ParserQuery pq = lp.parserQuery();
				pq.parse(mySentence);
				List<ScoredObject<Tree>> treeList = pq.getKBestParses(1);
				for (ScoredObject<Tree> scoredObject : treeList) {
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("Req" + i + ":");
					scoredObject.object().pennPrint();
					System.out.println(scoredObject.score());
						
					System.out.println("");
					System.out.println("");
					Tree myTree = scoredObject.object();
					String myOnlineTree = myTree.toString();
					myOnlineTree = myOnlineTree.replaceAll("\\(", "{");
					myOnlineTree = myOnlineTree.replaceAll("\\)", "}");
					System.out.print("ONLINE: " + myOnlineTree);
					System.out.println("");
					System.out.println("");
					
					System.out.println("TREGEX PROVA: ");
					System.out.println("");
					System.out.println("VARNAMES: ");
					TregexPattern myPattern = TregexPattern.compile("NP < NNP");
					TregexMatcher myMatcher = myPattern.matcher(myTree);
					while (myMatcher.findNextMatchingNode()) { 
						Tree match = myMatcher.getMatch(); 
						match.pennPrint();
						if(match.numChildren() >= 2){
							System.out.println(match.getChild(1).firstChild().label().toString());
						}
					
					}
					
					System.out.println("");
					System.out.println("VARVALUES: ");
					myPattern = TregexPattern.compile("ADJP < JJ");
					myMatcher = myPattern.matcher(myTree);
					while (myMatcher.findNextMatchingNode()) { 
						Tree match = myMatcher.getMatch(); 
						match.pennPrint();
					
					}
					
					myPattern = TregexPattern.compile("QP < CD");
					myMatcher = myPattern.matcher(myTree);
					while (myMatcher.findNextMatchingNode()) { 
						Tree match = myMatcher.getMatch(); 
						match.pennPrint();
					
					}
					
					System.out.println("");
					System.out.println("PATTERNVAR: ");
					
					List<String> patternVar = myStrategy.constructVariables(myTree);
					Iterator<String> paIter = patternVar.iterator();
					while(paIter.hasNext()){
						System.out.println(paIter.next());
					}
					
					System.out.println("");
					System.out.println("Single Tree Print:");
					List<Tree> leaves = myTree.getLeaves();
					Iterator<Tree> iterLeaves = leaves.iterator();
					while(iterLeaves.hasNext()){
						Tree auxTree = iterLeaves.next();
						auxTree.ancestor(1, myTree).pennPrint();
						
						
					}
				}
			}
			*/
		}

	}
