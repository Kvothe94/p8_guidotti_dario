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
		patternVar1 = null;
		this.scope = new Scope();
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getPatternVar1() {
		return patternVar1;
	}

	public void setPatternVar1(String patternVar1) {
		this.patternVar1 = patternVar1;
	}
	
	public abstract String asString();
	
}
