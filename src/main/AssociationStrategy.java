/*
 * 
 */
package main;
/**
 * 
 */

import java.util.List;

import patterns.*;

/**
 * Classe astratta che fornisce la struttura generale che le strategie
 * di traduzione dei requisiti da linguaggio naturale a FSPattern
 * devono seguire.
 * 
 * @author Guidotti Dario
 * @version 1.0
 */
public abstract class AssociationStrategy {
	
	protected Context context;
	protected List<FSPattern> patternsModel;
	
	/**
	 * @param context
	 * @param patternsModel
	 */
	public AssociationStrategy(Context context, List<FSPattern> patternsModel){
		this.context = context;
		this.patternsModel = patternsModel;
	}
	
	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the patternsModel
	 */
	public List<FSPattern> getPatternsModel() {
		return patternsModel;
	}

	/**
	 * @param patternsModel the patternsModel to set
	 */
	public void setPatternsModel(List<FSPattern> patternsModel) {
		this.patternsModel = patternsModel;
	}

	/**
	 * Metodo che compie la traduzione di un requisito in linguaggio
	 * naturale in Formal Specification Pattern.
	 * 
	 * @param requirement il requisito in linguaggio naturale in forma
	 *        di oggetto Requirement.
	 * @return il FSPattern corrispondente al requisito passato
	 *         come argomento
	 */
	public abstract FSPattern associatePattern(Requirement requirement);
	
}
