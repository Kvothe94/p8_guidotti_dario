import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Patterns.DurationPattern;
import Patterns.FSPattern;
import Patterns.OccurencePattern;
import Patterns.OrderPattern;
import Patterns.PeriodicPattern;
import Patterns.RTOrderPattern;
import Patterns.Scope;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.common.ParserQuery;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.util.ScoredObject;

/**
 * 
 */

/**
 * Classe concreta che rappresenta una strategia di traduzione dei requisiti
 * in Formal Specification Pattern.
 * Contiene gli attributi e i metodi necessari per compiere la traduzione 
 * da linguaggio naturale a pattern.
 * Per compiere la traduzione utilizza alcune librerie esterne quali StanfordNLP (per
 * costruire gli alberi sintattici).
 * 
 * @author Guidotti Dario
 *
 */
public class AssociationStanfordStrategy extends AssociationStrategy {
	
	/**
	 * Indica il massimo numero di diversi alberi che verranno restituiti
	 * dal parser di stanford in risposta al requisito in formato di stringa.
	 */
	private static final int NUM_TREE = 5;
	private static final int WORST_SCORE = -1000000;
	
	/**
	 * Lista in cui verranno memorizzati gli alberi sintattici (con punteggio associato)
	 * generati a partire dal requisito in forma di stringa.
	 */
	private List<ScoredObject<Tree>> reqTrees;
	
	/**
	 * Lista degli alberi ottenuti applicando il parser di stanford ai modelli di requisiti.
	 */
	private List<Tree> modelTrees;
	
	private LexicalizedParser lexParser;

	/**
	 * @param context contiene l'ontologia e i metodi di accesso a essa.
	 * @param patternsModel contiene la lista dei modelli dei pattern.
	 */
	public AssociationStanfordStrategy(Context context, List<FSPattern> patternsModel) {
		
		super(context, patternsModel);
		
		/* Inizializzazione del parser di stanford. */
		String parserModel = "englishPCFG.ser.gz";
		lexParser = LexicalizedParser.loadModel(parserModel);
		TreebankLanguagePack tlp = lexParser.getOp().langpack();
		
		modelTrees = new ArrayList<Tree>();
		Iterator<FSPattern> patternIter = patternsModel.iterator();
		
		/* Generazione degli alberi dei modelli di pattern memorizzati in patternsModel. */
		while(patternIter.hasNext()) {
			
			FSPattern auxPattern = patternIter.next();
			
			Tokenizer<? extends HasWord> toke =
			        tlp.getTokenizerFactory().getTokenizer(
			        		new StringReader(auxPattern.asString()));
			
			List<? extends HasWord> mySentence = toke.tokenize();
			ParserQuery pq = lexParser.parserQuery();
			pq.parse(mySentence);
			modelTrees.add(pq.getBestParse());
			
		}
		
	}

	/**
	 * @return the reqTrees
	 */
	public List<ScoredObject<Tree>> getReqTrees() {
		return reqTrees;
	}

	/**
	 * @param reqTrees the reqTrees to set
	 */
	public void setReqTrees(List<ScoredObject<Tree>> reqTrees) {
		this.reqTrees = reqTrees;
	}

	/**
	 * @return the modelTrees
	 */
	public List<Tree> getModelTrees() {
		return modelTrees;
	}

	/**
	 * @param modelTrees the modelTrees to set
	 */
	public void setModelTrees(List<Tree> modelTrees) {
		this.modelTrees = modelTrees;
	}


	/**
	 * @return the lexParser
	 */
	public LexicalizedParser getLexParser() {
		return lexParser;
	}


	/**
	 * @param lexParser the lexParser to set
	 */
	public void setLexParser(LexicalizedParser lexParser) {
		this.lexParser = lexParser;
	}


	/* (non-Javadoc)
	 * @see AssociationStrategy#associatePattern(Requirement)
	 */
	@Override
	public FSPattern associatePattern(Requirement requirement) {
		
		/* Generazione dei NUM_TREE possibili alberi sintattici del requisito. */
		TreebankLanguagePack tlp = lexParser.getOp().langpack();
		
		Tokenizer<? extends HasWord> toke =
		        tlp.getTokenizerFactory().getTokenizer(
		        		new StringReader(requirement.getRequirement()));
		
		List<? extends HasWord> mySentence = toke.tokenize();
		ParserQuery pq = lexParser.parserQuery();
		pq.parse(mySentence);
		reqTrees = pq.getKBestParses(NUM_TREE);
		
		int bestIndex = -1;
		int bestScore = WORST_SCORE;
		TreeOntologyVisitor ontVis = new TreeOntologyVisitor();
		TreeComparingVisitor comVis = new TreeComparingVisitor();
		
		/*
		 * Trattamento degli alberi utilizzando l'ontologia: per il momento è una semplice verifica
		 * che le parole indicate come nomi corrispondano a entità all'interno dell'ontologia.
		 * Eventualmente si dovrebbe migliorare il trattamento dei dati.
		 */
		for(int i = 0; i < reqTrees.size(); i++) {
			
			ontVis.visit(this, i);
			if(ontVis.getScore() > bestScore){
				bestScore = ontVis.getScore();
				bestIndex = i;
			}
			
		}
		
		/*
		 * Una volta selezionato l'albero sintattico "migliore" si cerca utilizzando
		 * il comparing visitor il modello da cui presenza distanza minore
		 * quindi si ritorna il pattern costruito a partire dal requirement.
		 */
		comVis.visit(this, bestIndex);
		int bestModelIndex = comVis.getBestModelIndex();
		
		return generatePattern(requirement.getRequirement(), bestModelIndex);
		
	}
	
	/**
	 * Metodo che implementa la traduzione del requisito in formato di
	 * stringa in FSPattern.
	 * 
	 * @param req il requisito del quale vogliamo generare il FSPattern
	 * @param bestModelIndex l'indice del modello più somigliante al
	 *        nostro requisito all'interno della lista patternsModel.
	 *        
	 * @return il FSPattern corrispondente al requisito passato come
	 *         parametro.
	 */
	private FSPattern generatePattern(String req, int bestModelIndex){
		
		FSPattern bestModel = patternsModel.get(bestModelIndex);
		
		/*
		 * Analizzo req in modo da ottenere le variabili che andranno
		 * inserite all'interno del pattern nel formato corretto e le
		 * memorizzo all'interno di una lista.
		 */
		List<String> vars = constructVariables(req);
		List<String> patternVars = new ArrayList<String>();
		List<String> scopeVars = new ArrayList<String>();
		Iterator<String> varIter = vars.iterator();
		int scopeType = bestModel.getScope().getType();
		int patternClass = bestModel.getPatternClass();
		int patternType = bestModel.getType();
		
		/*
		 * Costruisco lo Scope del pattern: a seconda del tipo dovranno
		 * essere settate diverse variabili.
		 */
		Scope reqScope = new Scope();
		reqScope.setType(scopeType);
		switch (scopeType) {
		case 0:
			
			break;
			
		case 1:
		case 2:
			if(varIter.hasNext()) {
				reqScope.setScopeVar1(varIter.next());
			}
			break;
			
		case 3:
		case 4:
			for(int i = 0; i < 2; i++) {
				if(varIter.hasNext()) {
					scopeVars.add(varIter.next());
				} else {
					scopeVars.add(null);
				}
			}
			reqScope.setScopeVar1(scopeVars.get(0));
			reqScope.setScopeVar2(scopeVars.get(1));
			break;

		default:
			break;
		}
		
		/*
		 * Inizializzo il nuovo pattern facendo riferimento al
		 * modello indicato tramite il parametro bestModelIndex.
		 */
		FSPattern reqPattern = null;
		switch (patternClass) {
		case 0:
			reqPattern = new DurationPattern();
			break;
			
		case 1:
			reqPattern = new OccurencePattern();
			break;
			
		case 2:
			reqPattern = new OrderPattern();
			break;
			
		case 3:
			reqPattern = new PeriodicPattern();
			break;
			
		case 4:
			reqPattern = new RTOrderPattern();
			break;

		default:
			break;
		}
		
		/*
		 * Setto le variabili del Pattern a seconda del valore
		 * restituito da getNumVar().
		 */
		reqPattern.setType(patternType);
		int numVars = reqPattern.getNumVar();
		switch (numVars) {
		case 1:
			if(varIter.hasNext()) {
				reqPattern.setPatternVar1(varIter.next());
			}
			break;

		case 2:
			for(int i = 0; i < 2; i++) {
				if(varIter.hasNext()) {
					patternVars.add(varIter.next());
				} else {
					patternVars.add(null);
				}
			}
			reqPattern.setPatternVar1(patternVars.get(0));
			reqPattern.setPatternVar2(patternVars.get(1));
			break;

		case 3:
			for(int i = 0; i < 3; i++) {
				if(varIter.hasNext()) {
					patternVars.add(varIter.next());
				} else {
					patternVars.add(null);
				}
			}
			reqPattern.setPatternVar1(patternVars.get(0));
			reqPattern.setPatternVar2(patternVars.get(1));
			reqPattern.setPatternVar3(patternVars.get(2));
			break;

		case 4:
			for(int i = 0; i < 4; i++) {
				if(varIter.hasNext()) {
					patternVars.add(varIter.next());
				} else {
					patternVars.add(null);
				}
			}
			reqPattern.setPatternVar1(patternVars.get(0));
			reqPattern.setPatternVar2(patternVars.get(1));
			reqPattern.setPatternVar3(patternVars.get(2));
			reqPattern.setPatternVar4(patternVars.get(3));
			break;

		default:
			break;
		}
		
		reqPattern.setScope(reqScope);
		
		return reqPattern;
	}
	
	/**
	 * Metodo che costruisce la lista delle variabili del pattern
	 * corrispondente al requisito passato in forma di stringa
	 * come parametro.
	 * 
	 * @param req requisito in linguaggio naturale di cui si vogliono
	 *        estrapolare le variabili che andranno settate nel
	 *        corrispondente FSPattern.
	 *        
	 * @return La lista delle variabili in forma di stringa.
	 * 
	 */
	public List<String> constructVariables(String req) {
		
		List<String> varNames = new ArrayList<String>();
		List<String> vars = new ArrayList<String>();
		String varValue = null;
		String varOperator = null;
		
		/*
		 * Definizione dei Pattern (java.util) che verranno usati come
		 * espressioni regolari per recuperare i diversi tipi di
		 * variabili.
		 */
		Pattern varNamePattern = Pattern.compile(
				"\\{(([a-zA-z0-9]+[\\-]*[a-zA-z0-9]+)*)\\}");
		
		Pattern numVarAssignPattern = Pattern.compile(
				"(greater|less|equal) ?[(or equal)]* (than|to) " + 
				"([0-9]+\\.*[0-9]*)");
		
		Pattern boolVarAssignPattern = Pattern.compile(
				"(becomes|set to|is) (true|false)");
		
		Pattern reqPattern = Pattern.compile("Req[0-9*]");
		
		Matcher nameMatcher = varNamePattern.matcher(req);
		Matcher numMatcher = numVarAssignPattern.matcher(req);
		Matcher boolMatcher = boolVarAssignPattern.matcher(req);
		Matcher auxMatcher = null;
		
		String varName = null;
		String varType = null;
		String numAssign = null;
		String boolAssign = null;
		
		/* Troviamo i nomi delle variabili */
		while(nameMatcher.find()) {
			
			varName = nameMatcher.group(1);
			auxMatcher = reqPattern.matcher(varName);
			if(!auxMatcher.find()) {
				varNames.add(varName);
			}
			
		}
		
		/*
		 * Per ogni nome di variabile trovato cerchiamo il tipo
		 * e il valore dell'assegnazione abbinata alla variabile.
		 * Per il momento si suppone di avere solo variabili di tipo
		 * booleano o numerico.
		 */
		Iterator<String> varNameIter = varNames.iterator();
		while(varNameIter.hasNext()) {
			
			varName = varNameIter.next();
			varType = context.getSignalMap().get(varName);
			varOperator = null;
			varValue = null;
			
			switch (varType) {
			case "Integer":
			case "Real":
				
				if(numMatcher.find()) {
					numAssign = numMatcher.group(0);
					auxMatcher = Pattern.compile("(greater|less|equal)" + 
							" ?[(or equal)]* (than|to)").matcher(numAssign);
					
					if(auxMatcher.find()) {

						switch (auxMatcher.group(0)) {
						
						case "greater than":
							varOperator = ">";
							break;
							
						case "greater or equal than":
							varOperator = ">=";
							break;
							
						case "less than":
							varOperator = "<";
							break;
							
						case "less or equal than":
							varOperator = "<=";
							break;
							
						case "equal to":
							varOperator = "=";
							break;

						default:
							break;
						}
					}
					
					auxMatcher = Pattern.compile(
							"([0-9]+\\.*[0-9]*)").matcher(numAssign);
					
					if(auxMatcher.find()) {
						varValue = auxMatcher.group(0);
					}
					
				}
				
				break;
				
			case "Boolean":
				
				varOperator = "=";
				if(boolMatcher.find()) {
					boolAssign = boolMatcher.group(0);
					auxMatcher = Pattern.compile(
							"(true|false)").matcher(boolAssign);
					
					if(auxMatcher.find()) {
						switch (auxMatcher.group(0)) {
						case "true":
							varValue = "1";
							break;
							
						case "false":
							varValue = "0";
							break;

						default:
							break;
						}
					}
					
				}
				
				break;

			default:
				break;
			}
			
			vars.add("{" + varName + varOperator + varValue + "}");
			
		}
		
		return vars;
		
	}
	
	private DurationPattern durationPatternFromTree(int treeIndex){
		
		
		
		
		
		return null;
		
	}

	public int testVis(Requirement requirement, float DEL_COST, float INS_COST, float REN_COST){
		
		TreebankLanguagePack tlp = lexParser.getOp().langpack();
		Tokenizer<? extends HasWord> toke =
		        tlp.getTokenizerFactory().getTokenizer(new StringReader(requirement.getRequirement()));
		List<? extends HasWord> mySentence = toke.tokenize();
		ParserQuery pq = lexParser.parserQuery();
		pq.parse(mySentence);
		reqTrees = pq.getKBestParses(NUM_TREE);
		
		int bestIndex = -1;
		int bestScore = WORST_SCORE;
		TreeOntologyVisitor ontVis = new TreeOntologyVisitor();
		TreeComparingVisitor comVis = new TreeComparingVisitor(DEL_COST, INS_COST, REN_COST);
		
		/*
		 * Trattamento degli alberi utilizzando l'ontologia: per il momento è una semplice verifica
		 * che le parole indicate come nomi corrispondano a entità all'interno dell'ontologia.
		 * Eventualmente si dovrebbe migliorare il trattamento dei dati.
		 */
		for(int i = 0; i < reqTrees.size(); i++){
			
			ontVis.visit(this, i);
			if(ontVis.getScore() > bestScore){
				bestScore = ontVis.getScore();
				bestIndex = i;
			}
			
		}
		
		comVis.visit(this, bestIndex);
		int bestModelIndex = comVis.getBestModelIndex();
		return bestModelIndex;
	}

	private Scope generateScopeFromTree(Scope modelScope, int treeIndex){
		
		Scope newScope = new Scope();
		newScope.setType(modelScope.getType());
		Tree myTree = reqTrees.get(treeIndex).object();
		
		/*
		 * Recupero dei nomi delle variabili
		 */
		TregexPattern myPattern = TregexPattern.compile("NP < NNP");
		TregexMatcher myMatcher = myPattern.matcher(myTree);
		List<String> varNames = new ArrayList<String>();
		List<String> varValues = new ArrayList<String>();
		List<String> varOperator = new ArrayList<String>();
		
		while (myMatcher.findNextMatchingNode()) { 
			Tree match = myMatcher.getMatch();
			if(match.numChildren() >= 2){	//Significa che non è l'etichetta requirement
				varNames.add(match.getChild(1).label().toString());
			}
		
		}
		
		/*
		 * Recupero Valori delle variabili
		 */
		
		Iterator<String> myIter = varNames.iterator();
		while(myIter.hasNext()){
			String name = myIter.next();
			if(context.getSignalMap().get(name).equals("Integer")){
				
				/*
				 * Nel caso il segnale sia un integer andiamo a cercare il FSPattern
				 * di un confronto con un valore numerico.
				 * Per =, <=, >= bisogna sviluppare ulteriormente l'algoritmo.
				 */
				myPattern = TregexPattern.compile("QP < CD");
				myMatcher = myPattern.matcher(myTree);
				while (myMatcher.findNextMatchingNode()) { 
					
					Tree match = myMatcher.getMatch(); 
					if(match.numChildren() >= 3){
						
						varValues.add(match.getChild(2).label().toString());
						varOperator.add(match.getChild(0).label().toString());
						
					}
				
				}
				
			} else {
				
				/*
				 * Per il momento se non è un Integer deve essere un Boolean.
				 */
				myPattern = TregexPattern.compile("ADJP < JJ");
				myMatcher = myPattern.matcher(myTree);
				while (myMatcher.findNextMatchingNode()) { 
					
					Tree match = myMatcher.getMatch(); 
					if(match.numChildren() >= 1){
						
						varValues.add(match.getChild(0).label().toString());
						varOperator.add("assignment");
						
					}
					
				}
				
				
				
				
			}
		}
		
		
		return null;
		
	}

	public List<String> constructVariables(Tree tree){
		
		/*
		 * Recupero dei nomi delle variabili
		 */
		List<String> varNames = new ArrayList<String>();
		List<String> integerVarValues = new ArrayList<String>();
		List<String> booleanVarValues = new ArrayList<String>();
		List<String> varOperator = new ArrayList<String>();
		TregexPattern myPattern = TregexPattern.compile("NP < NNP");
		TregexMatcher myMatcher = myPattern.matcher(tree);
		
		while (myMatcher.findNextMatchingNode()) { 
			Tree match = myMatcher.getMatch();
			if(match.numChildren() >= 2){	//Significa che non è l'etichetta requirement
				varNames.add(match.getChild(1).firstChild().label().toString());
			}
		
		}
		
		/*
		 * Recupero Valori Variabili Intere
		 */
		myPattern = TregexPattern.compile("QP < CD");
		myMatcher = myPattern.matcher(tree);
		while (myMatcher.findNextMatchingNode()) { 
			
			Tree match = myMatcher.getMatch(); 
			if(match.numChildren() >= 3){
				
				integerVarValues.add(match.getChild(2).firstChild().label().toString());
				switch (match.getChild(0).firstChild().label().toString()) {
				
				case "greater":
					varOperator.add(">");
					break;
	
				case "less":
					varOperator.add("<");
					break;
					
				default:
					break;
				}
				
			}
		
		}
		
		/*
		 * Recupero Valori Variabili Booleane
		 */
		myPattern = TregexPattern.compile("ADJP < JJ");
		myMatcher = myPattern.matcher(tree);
		while (myMatcher.findNextMatchingNode()) { 
			
			Tree match = myMatcher.getMatch(); 
			if(match.numChildren() >= 1){
				
				switch (match.getChild(0).firstChild().label().toString()) {
				case "true":
					booleanVarValues.add("1");
					break;
					
				case "false":
					booleanVarValues.add("0");
					break;
	
				default:
					break;
				}
				varOperator.add("=");
				
			}
			
		}
		
		List<String> varList = new ArrayList<String>();
		int k = 0;
		int m = 0;
		for(int i = 0; i < varNames.size(); i++){
			
			String type = context.getSignalMap().get(varNames.get(i));
			if(type.equals("Integer")){
				varList.add("{" + varNames.get(i) + varOperator.get(i) + integerVarValues.get(k) + "}");
				k++;
			} else {
				varList.add("{" + varNames.get(i) + varOperator.get(i) + booleanVarValues.get(m) + "}");
				m++;
			}
			
		}
		
		return varList;
		
	}

}






























