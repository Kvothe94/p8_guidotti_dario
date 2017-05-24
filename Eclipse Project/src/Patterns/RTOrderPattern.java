package Patterns;
/**
 * 
 */

/**
 * Classe concreta che implementa i pattern che rientrano nella categoria di
 * RTOrder Pattern.
 * 
 * @author Guidotti Dario
 *
 */
public class RTOrderPattern extends RealTimePattern {
	
	private static String[] map = {"it is always the case that if %s holds, " + 
			"then %s holds after at most %s time unit(s)",
			"it is always the case that if %s holds, " + 
			"then %s holds for at least %s time unit(s)"};
	
	public RTOrderPattern() {
		super();
	}

	/* (non-Javadoc)
	 * @see Pattern#asString()
	 */
	@Override
	public int getPatternClass() {
		return 4;
	}
	
	/* (non-Javadoc)
	 * @see Pattern#getNumVar()
	 */
	@Override
	public int getNumVar() {
		
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
		
		String stringVer = null;
		
		if((type < 0) || (type >= RTOrderPattern.map.length)) {
			return null;
		}
		
		if((patternVar1 != null) && (patternVar2 != null)
				&& (patternVar3 != null)) {
			
			stringVer = String.format(RTOrderPattern.map[type],
					patternVar1, patternVar3, patternVar2);
		
		}
		
		if((stringVer != null) && (scope.asString() != null)) {
			return scope.asString() + stringVer;
		}
		
		return stringVer;
		
	}

}
