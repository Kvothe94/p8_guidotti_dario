package Patterns;
/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public abstract class RealTimePattern extends Pattern{
	
	protected String patternVar2;
	
	public RealTimePattern(){
		super();
		this.patternVar2 = null;
	}

	public String getPatternVar2() {
		return patternVar2;
	}

	public void setPatternVar2(String patternVar2) {
		this.patternVar2 = patternVar2;
	}
	
}
