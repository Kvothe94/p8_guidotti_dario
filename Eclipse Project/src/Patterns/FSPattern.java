/**
 * 
 */
package Patterns;

/**
 * @author Guidotti Dario
 *
 */
public abstract class FSPattern {

	protected int type;
	protected Scope scope;
	protected String patternVar1;
	
	public FSPattern(){
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
	 * @return the patternVar2
	 */
	public abstract String getPatternVar2();

	/**
	 * @param patternVar2 the patternVar2 to set
	 */
	public abstract void setPatternVar2(String patternVar1);
	
	/**
	 * @return the patternVar3
	 */
	public abstract String getPatternVar3();

	/**
	 * @param patternVar3 the patternVar3 to set
	 */
	public abstract void setPatternVar3(String patternVar1);
	
	/**
	 * @return the patternVar4
	 */
	public abstract String getPatternVar4();

	/**
	 * @param patternVar4 the patternVar4 to set
	 */
	public abstract void setPatternVar4(String patternVar1);
	
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
	
	/**
	 * 
	 * @return the number of variable necessary for the completeness
	 * of the pattern
	 */
	public abstract int getNumVar();

	public abstract String asString();

}
