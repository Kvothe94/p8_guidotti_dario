package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class DurationPattern extends RealTimePattern {
	
	private static String[] map = {"it is always the case that once %s becomes satisfied, it holds for at least %s time unit(s).",
			"it is always the case that once %s becomes satisfied, it holds for less than %s time unit(s)."};
	
	public DurationPattern(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public int getPatternClass(){
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public String asString() {
		
		if(this.type < 0 || this.type >= DurationPattern.map.length){
			return null;
		}
		
		String stringVerSco = this.scope.asString();
		String stringVer = null;
		if(this.patternVar1 != null && this.patternVar2 != null && stringVerSco != null){
			stringVer = stringVerSco + String.format(DurationPattern.map[this.type], this.patternVar1, this.patternVar2);
		}
		
		return stringVer;
	}

}
