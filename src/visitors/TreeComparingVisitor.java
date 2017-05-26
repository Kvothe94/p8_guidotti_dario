/*
 * 
 */
package visitors;
import java.util.Iterator;
import java.util.List;

import costmodel.PerEditOperationStringNodeDataCostModel;
import distance.APTED;
import edu.stanford.nlp.trees.Tree;
import main.AssociationStanfordStrategy;
import node.Node;
import node.StringNodeData;
import parser.BracketStringInputParser;

/**
 * 
 */

/**
 * Classe che si occupa di comparare il requisito che vogliamo tradurre da
 * linguaggio naturale a Formal Specification Pattern con gli alberi sintattici
 * dei modelli e quindi di selezionare il modello che presenta minor distanza.
 * La classe si appoggia all'API APTED per quanto riguarda il calcolo della distanza
 * degli alberi.
 * 
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class TreeComparingVisitor extends TreeVisitor {

	private static final float MAX_DIS = 1000000;
	private static final float DEL_COST = 1;
	private static final float INS_COST = 1;
	private static final float REN_COST = 1;
	
	private int bestModelIndex;
	private APTED<PerEditOperationStringNodeDataCostModel,
		StringNodeData> distanceComputer;
	
	/**
	 * Costruttore di default del visitor: inizializza gli attributi ai loro
	 * valori di default e inizializza il distanceComputer con i valori di default
	 * per quanto riguarda il modello di costo.
	 */
	public TreeComparingVisitor() {
		this.bestModelIndex = -1;
		PerEditOperationStringNodeDataCostModel costModel = 
				new PerEditOperationStringNodeDataCostModel(DEL_COST,
						INS_COST, REN_COST);
		
		this.distanceComputer = new APTED<PerEditOperationStringNodeDataCostModel,
				StringNodeData>(costModel);
		
	}
	
	/**
	 * In questo caso il costruttore inizializza il modello di costo
	 * con i valori passati come argomenti.
	 * 
	 * @param DEL_COST costo della delete di un nodo
	 * @param INS_COST costo dell'inserimento di un nodo
	 * @param REN_COST costo del rename di un nodo
	 * 
	 */
	public TreeComparingVisitor(float DEL_COST, float INS_COST, float REN_COST) {
		
		this.bestModelIndex = -1;
		PerEditOperationStringNodeDataCostModel costModel =
				new PerEditOperationStringNodeDataCostModel(DEL_COST,
						INS_COST, REN_COST);
		
		this.distanceComputer = new APTED<PerEditOperationStringNodeDataCostModel,
				StringNodeData>(costModel);
		
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
		
		/*
		 * Portiamo l'albero del requisito nel formato adatto
		 * per poter essere processato utilizzando APTED
		 */
		BracketStringInputParser aptPars = new BracketStringInputParser();
		Tree treeReq = strategy.getReqTrees().get(treeId).object();
		String stringTreeReq = aptedFormatInit(treeReq);
		stringTreeReq = stringTreeReq.replaceAll(" ", "");
		Node<StringNodeData> reqNode = aptPars.fromString(stringTreeReq);
		
		/*
		 * Confrontiamo l'albero del requisito con tutti gli
		 * alberi dei modelli e scegliamo quello che presenta
		 * distanza minore.
		 */
		List<Tree> modelsTrees = strategy.getModelTrees();
		Iterator<Tree> modelIter = modelsTrees.iterator();
		while(modelIter.hasNext()) {
			
			Tree auxTreeModel = modelIter.next();
			String stringTreeMod = aptedFormatInit(auxTreeModel);
			stringTreeMod = stringTreeMod.replaceAll(" ", "");
			Node<StringNodeData> modNode = aptPars.fromString(stringTreeMod);
			auxDis = distanceComputer.computeEditDistance(modNode, reqNode);
			
			if(auxDis < minDis) {
				minDis = auxDis;
				bestIndex = i;
			}
			
			i++;
			
		}
		
		bestModelIndex = bestIndex;
		
	}
	
	/**
	 * Metodo che porta l'albero passato come argomento
	 * nel formato richiesto da APTED.
	 * 
	 * @param tree l'albero da trasformare
	 * @return L'albero nel formato richiesto da apted.
	 */
	private String aptedFormatInit(Tree tree) {
		
		String onLineTree = tree.toString();
		onLineTree = onLineTree.replaceAll("\\(", "{");
		onLineTree = onLineTree.replaceAll("\\)", "}");
		return onLineTree;
		
	}

}
