package Patterns;
/**
 * 
 */

/**
 * Classe concreta che implementa i pattern che ricadono nella categoria di Duration Pattern.
 *
 * @author Guidotti Dario
 *
 */
public class DurationPattern extends RealTimePattern {
	
	/**
	 * Fornisce il formato in stringa parametrizzato sulle variabili dei
	 * diversi tipi di DurationPattern.
	 * 
	 */
	private static String[] map = {
			"it is always the case that once %s " +
			"becomes satisfied, it holds for at least %s time unit(s).",
			"it is always the case that once %s becomes satisfied, " + 
			"it holds for less than %s time unit(s)."};
	
	public DurationPattern() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar() {
		
		int numVar = -1;
		switch (type) {
		case 0:
		case 1:
			numVar = 2;
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
		
		if((type < 0) || (type >= DurationPattern.map.length)) {
			return null;
		}
		
		String stringVerSco = scope.asString();
		String stringVer = null;
		if((patternVar1 != null) && (patternVar2 != null)
				&& (stringVerSco != null)) {
			
			stringVer = stringVerSco + 
					String.format(DurationPattern.map[type],
							patternVar1, patternVar2);
			
		}
		
		return stringVer;
	}

}
