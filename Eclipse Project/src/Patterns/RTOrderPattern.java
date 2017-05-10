package Patterns;
/**
 * 
 */

/**
 * @author Kvothe
 *
 */
public class RTOrderPattern extends RealTimePattern {
	
	private static String[] map = {"it is always the case that if %s holds, then %s holds after at most %s time unit(s)",
			"it is always the case that if %s holds, then %s holds for at least %s time unit(s)"};
	
	private String patternVar3;
	
	public RTOrderPattern() {
		super();
		this.patternVar3 = null;
	}

	public String getPatternVar3() {
		return patternVar3;
	}

	public void setPatternVar3(String patternVar3) {
		this.patternVar3 = patternVar3;
	}

	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public String asString() {
		
		if(this.type < 0 || this.type >= RTOrderPattern.map.length){
			return null;
		}
		
		String stringVer = null;
		if(this.patternVar1 != null && this.patternVar2 != null && this.patternVar3 != null){
			stringVer = String.format(RTOrderPattern.map[this.type], this.patternVar1, this.patternVar3, this.patternVar2);
		}
		
		if(stringVer != null && this.scope.asString() != null){
			return this.scope.asString() + stringVer;
		}
		
		return stringVer;
		
	}

}