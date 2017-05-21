import java.util.Iterator;
import java.util.List;

import costmodel.PerEditOperationStringNodeDataCostModel;
import distance.APTED;
import edu.stanford.nlp.trees.Tree;
import node.Node;
import node.StringNodeData;
import parser.BracketStringInputParser;

/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public class TreeComparingVisitor extends TreeVisitor {

	private static final float MAX_DIS = 1000000;
	public float DEL_COST = 1;
	public float INS_COST = 1;
	public float REN_COST = 2;
	
	private int bestModelIndex;
	private APTED<PerEditOperationStringNodeDataCostModel, StringNodeData> distanceComputer;
	
	/**
	 * 
	 */
	public TreeComparingVisitor(float DEL_COST, float INS_COST, float REN_COST) {
		this.bestModelIndex = -1;
		PerEditOperationStringNodeDataCostModel costModel = new PerEditOperationStringNodeDataCostModel(DEL_COST, INS_COST, REN_COST);
		this.distanceComputer = new APTED<PerEditOperationStringNodeDataCostModel, StringNodeData>(costModel);
	}
	
	public TreeComparingVisitor() {
		this.bestModelIndex = -1;
		PerEditOperationStringNodeDataCostModel costModel = new PerEditOperationStringNodeDataCostModel(DEL_COST, INS_COST, REN_COST);
		this.distanceComputer = new APTED<PerEditOperationStringNodeDataCostModel, StringNodeData>(costModel);
	}

	/**
	 * @return the bestModelIndex
	 */
	public int getBestModelIndex() {
		return bestModelIndex;
	}

	/**
	 * @param bestModelIndex the bestModelIndex to set
	 */
	public void setBestModelIndex(int bestModelIndex) {
		this.bestModelIndex = bestModelIndex;
	}

	/* (non-Javadoc)
	 * @see TreeVisitor#visit(AssociationStanfordStrategy, int)
	 */
	@Override
	public void visit(AssociationStanfordStrategy strategy, int treeId) {
		
		int i = 0;
		int bestIndex = -1;
		float minDis = MAX_DIS;
		float auxDis;
		BracketStringInputParser aptPars = new BracketStringInputParser();
		
		Tree treeReq = strategy.getReqTrees().get(treeId).object();
		String stringTreeReq = aptedFormatInit(treeReq);
		stringTreeReq = stringTreeReq.replaceAll(" ", "");
		Node<StringNodeData> reqNode = aptPars.fromString(stringTreeReq);
		
		List<Tree> modelsTrees = strategy.getModelTrees();
		Iterator<Tree> modelIter = modelsTrees.iterator();
		while(modelIter.hasNext()){
			
			Tree auxTreeModel = modelIter.next();
			String stringTreeMod = aptedFormatInit(auxTreeModel);
			stringTreeMod = stringTreeMod.replaceAll(" ", "");
			Node<StringNodeData> modNode = aptPars.fromString(stringTreeMod);
			auxDis = distanceComputer.computeEditDistance(modNode, reqNode);
			if(auxDis < minDis){
				minDis = auxDis;
				bestIndex = i;
			}
			i++;
			
		}
		
		bestModelIndex = bestIndex;
		
	}
	
	private String aptedFormatInit(Tree tree){
		
		String onLineTree = tree.toString();
		onLineTree = onLineTree.replaceAll("\\(", "{");
		onLineTree = onLineTree.replaceAll("\\)", "}");
		return onLineTree;
		
	}

}
