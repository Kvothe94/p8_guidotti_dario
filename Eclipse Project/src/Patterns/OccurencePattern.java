package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class OccurencePattern extends QualitativePattern {

	private static String[] map = {"it is never the case that %s holds.", "it is always the case that %s holds.",
			"%s eventually holds.", "transitions to states in which %s holds occur at most twice."};
	
	public OccurencePattern() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass(){
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar(){
		
		int numVar = -1;
		switch (this.type) {
		case 0:
		case 1:
		case 2:
		case 3:
			numVar = 1;
			break;
		
		default:
			break;
		}
		
		return numVar;
		
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public String asString() {
		
		if((this.patternVar1 == null) || (this.type < 0 || this.type >= OccurencePattern.map.length) 
				|| (this.scope.asString() == null)){
			return null;
		}
		String stringVer = this.scope.asString() + String.format(OccurencePattern.map[type], this.patternVar1);
		return stringVer;
		
	}

}
