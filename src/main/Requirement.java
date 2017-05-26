/*
 * 
 */
package main;
/**
 * 
 */

/**
 * Classe che fornisce i metodi e gli attributi per le memorizzazione dei requirement
 * in linguaggio naturale.
 * 
 * @author Guidotti Dario
 *
 */
public class Requirement {
	
	private int id;
	private String requirement;
	
	public Requirement(){
		id = -1;
		requirement = null;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}
	
	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
	
	
}
