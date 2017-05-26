/*
 * 
 */
package patterns;
/**
 * 
 */

/**
 * Classe che fornisce la struttura dei vari scope applicabili ai pattern.
 * Fornisce attributi e metodi per la creazione e la gestione di tali scope.
 * 
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class Scope {
	
	/**
	 * Fornisce il formato in stringa parametrizzato sulle variabili dei
	 * diversi tipi di Scope.
	 * 
	 */
	private static String[] map = {"Globally, ", "Before %s, ",
			"After %s, ", "Between %s and %s, ", "After %s until %s, "};
	
	private int type;
	private String scopeVar1;
	private String scopeVar2;
	
	/*
	 * Costruttore della classe: setta le variabili ai loro valori di
	 * default.
	 */
	public Scope() {
		
		this.type = -1;
		this.scopeVar1 = null;
		this.scopeVar2 = null;
	
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the scopeVar1
	 */
	public String getScopeVar1() {
		return scopeVar1;
	}

	/**
	 * @param scopeVar1 the scopeVar1 to set
	 */
	public void setScopeVar1(String scopeVar1) {
		this.scopeVar1 = scopeVar1;
	}

	/**
	 * @return the scopeVar2
	 */
	public String getScopeVar2() {
		return scopeVar2;
	}

	/**
	 * @param scopeVar2 the scopeVar2 to set
	 */
	public void setScopeVar2(String scopeVar2) {
		this.scopeVar2 = scopeVar2;
	}

	/**
	 * 
	 * @return il numero delle variabili che devono essere settate affinchè
	 *         sia possibile considerare lo scope completo.
	 */
	public int getNumVar() {
	
		int numVar = -1;
		switch (type) {
		case 0:
			numVar = 0;
			break;
		
		case 1:
		case 2:
			numVar = 1;
			break;
		
		case 3:
		case 4:
			numVar = 2;
			break;
		
		default:
			break;
		}
		
		return numVar;
	}
	
	/**
	 * @return lo scope in formato di stringa. Nel caso non siano state
	 *         settati tutti gli attributi necessari alla completezza dello scope
	 *         viene ritornato null.
	 */
	public String asString(){
		
		String stringVer = null;
		
		if((type < 0) || (type >= Scope.map.length)) {
			return null;
		}
		
		switch (type) {
		case 0:
			stringVer = String.format(Scope.map[0], scopeVar1, scopeVar2);
			break;
			
		case 1:
			if(scopeVar1 != null) {
				stringVer = String.format(Scope.map[1], scopeVar1, scopeVar2);
			}
			break;
			
		case 2:
			if(scopeVar1 != null) {
				stringVer = String.format(Scope.map[2], scopeVar1, scopeVar2);
			}
			break;
			
		case 3:
			if((scopeVar1 != null) && (scopeVar2 != null)) {
				stringVer = String.format(Scope.map[3], scopeVar1, scopeVar2);
			}
			break;
			
		case 4:
			if((scopeVar1 != null) && (scopeVar2 != null)) {
				stringVer = String.format(Scope.map[4], scopeVar1, scopeVar2);
			}
			break;

		default:
			break;
		}
		
		return stringVer;
		
	}
	
}
