import java.util.Iterator;
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

	private int score;
	
	/**
	 * 
	 */
	public TreeOntologyVisitor() {
		this.setScore(0);
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/* (non-Javadoc)
	 * @see TreeVisitor#visit(AssociationStanfordStrategy)
	 */
	@Override
	public void visit(AssociationStanfordStrategy strategy, int treeId) {
		
		setScore(0);
		ScoredObject<Tree> scoredTree = strategy.getReqTrees().get(treeId);
		Tree tree = scoredTree.object();
		List<Tree> leaves = tree.getLeaves();
		Iterator<Tree> leavesIter = leaves.iterator();
		Context auxContext = strategy.getContext();
		
		while(leavesIter.hasNext()){
			Tree auxLeaf = leavesIter.next();
			String auxLeafLabel = auxLeaf.ancestor(1, tree).label().toString();
			if(auxLeafLabel.equals("NN") || auxLeafLabel.equals("NNS") || auxLeafLabel.equals("NNP") ||
					auxLeafLabel.equals("NNPS")){
				if(!auxContext.isEntity(auxLeaf.nodeString())){
					setScore(getScore() - 1);
				}
			}
		}
		
		return;

	}

}
