import java.util.List;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ScoredObject;

/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public class TreeOntologyVisitor extends TreeVisitor {

	/**
	 * 
	 */
	public TreeOntologyVisitor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see TreeVisitor#visit(AssociationStanfordStrategy)
	 */
	@Override
	public void visit(AssociationStanfordStrategy strategy, int treeId) {
		
		ScoredObject<Tree> scoredTree = strategy.getTrees().get(treeId);
		Tree tree = scoredTree.object();
		//tree.

	}

}
