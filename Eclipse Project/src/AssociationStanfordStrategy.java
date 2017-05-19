import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Patterns.DurationPattern;
import Patterns.Pattern;
import Patterns.Scope;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.common.ParserQuery;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.util.ScoredObject;

/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public class AssociationStanfordStrategy extends AssociationStrategy {
	
	private static final int NUM_TREE = 5;
	private static final int WORST_SCORE = -1000000;
	private List<ScoredObject<Tree>> reqTrees;
	private List<Tree> modelTrees;
	private LexicalizedParser lexParser;

	/**
	 * @param context
	 * @param patternsModel
	 */
	public AssociationStanfordStrategy(Context context, List<Pattern> patternsModel) {
		
		super(context, patternsModel);
		String parserModel = "englishPCFG.ser.gz";
		this.lexParser = LexicalizedParser.loadModel(parserModel);
		TreebankLanguagePack tlp = lexParser.getOp().langpack();
		this.modelTrees = new ArrayList<Tree>();
		Iterator<Pattern> patternIter = patternsModel.iterator();
		
		while(patternIter.hasNext()){
			
			Pattern auxPattern = patternIter.next();
			Tokenizer<? extends HasWord> toke =
			        tlp.getTokenizerFactory().getTokenizer(new StringReader(auxPattern.asString()));
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
	public Pattern associatePattern(Requirement requirement) {
		
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
		TreeComparingVisitor comVis = new TreeComparingVisitor();
		
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
		
		return null;
	}
	
	private Pattern generatePatternFromTree(int bestTreeIndex, int bestModelIndex){
		
		Tree bestTree = reqTrees.get(bestTreeIndex).object();
		Pattern bestModel = this.patternsModel.get(bestModelIndex);
		
		Scope newScope = generateScopeFromTree(bestModel.getScope().getType(),
				bestTreeIndex);
		
		
		int patternClass = bestModel.getPatternClass();
		switch (patternClass) {
		case 0:
			
			break;

		case 1:
			
			break;

		case 2:
			
			break;

		case 3:
			
			break;

		case 4:
			
			break;

		default:
			break;
		}
		
		
		
		
		
		return null;
	}
	
	private Scope generateScopeFromTree(int scopeType, int treeIndex){
		
		Scope newScope = new Scope();
		newScope.setType(scopeType);
		
		switch (scopeType) {
		
		case 0:
			break;
			
		case 1:
				
			break;
				
		case 2:
				
			break;
				
		case 3:
				
			break;
				
		case 4:
				
			break;

		default:
			break;
		}
		
		
		return null;
		
	}
	
	private DurationPattern durationPatternFromTree(int treeIndex){
		
		
		
		
		
		return null;
		
	}

}






























