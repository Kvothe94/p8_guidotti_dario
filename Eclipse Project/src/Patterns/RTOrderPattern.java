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
	 * @see FSPattern#getPatternVar4()
	 */
	public String getPatternVar4(){
		return null;
	}

	/* (non-Javadoc)
	 * @see FSPattern#setPatternVar4()
	 */
	public void setPatternVar4(String patternVar1){}

	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public int getPatternClass(){
		return 4;
	}
	
	/* (non-Javadoc)
	 * @see Pattern#getNumVar()
	 */
	@Override
	public int getNumVar(){
		
		int numVar = -1;
		switch (type) {
		case 0:
		case 1:
			numVar = 3;
			break;
		
		default:
			break;
		}
		
		return numVar;
		
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
