/*
 * 
 */
package patterns;
/**
 * 
 */

/**
 * Classe concreta che implementa i pattern che rientrano nella categoria di
 * Periodic Pattern.
 * 
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class PeriodicPattern extends RealTimePattern {
	
	/**
	 * Fornisce il formato in stringa parametrizzato sulle variabili dei
	 * diversi tipi di PeriodicPattern.
	 * 
	 */
	private static String[] map = {"it is always the case that %s " +
			"holds at least every %s time unit(s)."};
	
	public PeriodicPattern() {
		super();
	}

	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass() {
		return 3;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar() {
		
		int numVar = -1;
		switch (type) {
		case 0:
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
		
		if((type < 0) || (type >= PeriodicPattern.map.length)) {
			return null;
		}
		
		String stringVer = null;
		if((patternVar1 != null) && (patternVar2 != null)) {
			
			stringVer = String.format(PeriodicPattern.map[type],
					patternVar1, patternVar2);
		
		}
		
		if((stringVer != null) && (scope.asString() != null)) {
			return scope.asString() + stringVer;
		}
		
		return null;
		
	}

}
