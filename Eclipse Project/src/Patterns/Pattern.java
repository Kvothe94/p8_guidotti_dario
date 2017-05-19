package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public abstract class Pattern {

	protected int type;
	protected Scope scope;
	protected String patternVar1;
	
	public Pattern(){
		this.type = -1;
		this.scope = null;
		this.patternVar1 = null;
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
	 * @return the scope
	 */
	public Scope getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	/**
	 * @return the patternVar1
	 */
	public String getPatternVar1() {
		return patternVar1;
	}

	/**
	 * @param patternVar1 the patternVar1 to set
	 */
	public void setPatternVar1(String patternVar1) {
		this.patternVar1 = patternVar1;
	}
	
	/**
	 * 
	 * @return the id of the concrete class of Pattern
	 * DurationPattern = 0;
	 * OccurencePattern = 1;
	 * OrderPattern = 2;
	 * PeriodicPattern = 3;
	 * RTOrderPattern = 4;
	 */
	public abstract int getPatternClass();

	public abstract String asString();
	
}
