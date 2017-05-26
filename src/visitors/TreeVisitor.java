package visitors;
/**
 * 
 */

import main.AssociationStanfordStrategy;

/**
 * Classe astratta che stabilisce il formato per i visitor degli
 * oggetti di tipo Tree memorizzati all'interno di un oggetto di
 * tipo AssociationStanfordStrategy.
 * 
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public abstract class TreeVisitor {
	
	/**
	 * Metodo che, a seconda dell'implementazione nelle classi concrete,
	 * compie un diverso tipo di visita sull'albero, memorizzato all'interno
	 * di strategy, di indice treeId.
	 * 
	 * @param strategy oggetto che contiene al suo interno l'albero da
	 *        analizzare e gli eventuali oggetti necessari all'analisi.
	 *        (ex: Ontologia nel caso del TreeOntologyVisitor)
	 *        
	 * @param treeId indice dell'albero da analizzare all'interno dell'attributo
	 *        reqTrees dell'oggetto strategy.
	 *        
	 */
	public abstract void visit(AssociationStanfordStrategy strategy, int treeId);
	
}
