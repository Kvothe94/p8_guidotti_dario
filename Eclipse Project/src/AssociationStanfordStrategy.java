import java.io.StringReader;
import java.util.List;
import Patterns.Pattern;
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
	
	private final int NUM_TREE = 5;
	private List<ScoredObject<Tree>> trees;

	/**
	 * @param context
	 * @param patternsModel
	 */
	public AssociationStanfordStrategy(Context context, List<Pattern> patternsModel) {
		super(context, patternsModel);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the trees
	 */
	public List<ScoredObject<Tree>> getTrees() {
		return trees;
	}

	/**
	 * @param trees the trees to set
	 */
	public void setTrees(List<ScoredObject<Tree>> trees) {
		this.trees = trees;
	}

	/* (non-Javadoc)
	 * @see AssociationStrategy#associatePattern(Requirement)
	 */
	@Override
	public Pattern associatePattern(Requirement requirement) {
		
		String parserModel = "englishPCFG.ser.gz";
		LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
		TreebankLanguagePack tlp = lp.getOp().langpack();
		Tokenizer<? extends HasWord> toke =
		        tlp.getTokenizerFactory().getTokenizer(new StringReader(requirement.getRequirement()));
		List<? extends HasWord> mySentence = toke.tokenize();
		ParserQuery pq = lp.parserQuery();
		pq.parse(mySentence);
		trees = pq.getKBestParses(NUM_TREE);
		
		
		
		
		
		return null;
	}

}
