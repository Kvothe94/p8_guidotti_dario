package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class PeriodicPattern extends RealTimePattern {
	
	private static String[] map = {"it is always the case that %s holds at least every %s time unit(s)."};
	
	public PeriodicPattern(){
		super();
	}

	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public String asString() {
		
		if(this.type < 0 || this.type >= PeriodicPattern.map.length){
			return null;
		}
		
		String stringVer = null;
		if(this.patternVar1 != null && this.patternVar2 != null){
			stringVer = String.format(PeriodicPattern.map[this.type], this.patternVar1, this.patternVar2);
		}
		
		if(stringVer != null && this.scope.asString() != null){
			return this.scope.asString() + stringVer;
		}
		
		return null;
		
	}

}
