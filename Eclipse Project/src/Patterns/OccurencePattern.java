package Patterns;
/**
 * 
 */

/**
 * Classe concreta che implementa i pattern che rientrano nella categoria di
 * Occurence Pattern.
 * 
 * @author Guidotti Dario
 *
 */
public class OccurencePattern extends QualitativePattern {

	/**
	 * Fornisce il formato in stringa parametrizzato sulle variabili dei
	 * diversi tipi di OccurencePattern.
	 * 
	 */
	private static String[] map = {
			"it is never the case that %s holds.",
			"it is always the case that %s holds.",
			"%s eventually holds.",
			"transitions to states in which %s holds occur at most twice."};
	
	public OccurencePattern() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass() {
		return 1;
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
		
		if((patternVar1 == null) || (type < 0) 
				|| (type >= OccurencePattern.map.length) 
				|| (scope.asString() == null)) {
			
			return null;
		}
		
		String stringVer = scope.asString()
				+ String.format(OccurencePattern.map[type], patternVar1);
		
		return stringVer;
		
	}

}
