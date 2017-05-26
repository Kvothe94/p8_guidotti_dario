/*
 * 
 */
package patterns;
/**
 * 
 */

/**
 * Classe concreta che implementa i pattern che rientrano nella categoria di
 * Order Pattern.
 * 
 * @author Guidotti Dario
 * @version 1.0
 *
 */
public class OrderPattern extends QualitativePattern {
	
	/**
	 * Fornisce il formato in stringa parametrizzato sulle variabili dei
	 * diversi tipi di OrderPattern.
	 * 
	 */
	private static String[] map = {
			"it is always the case that if %s holds, then %s previously held.",
			"it is always the case that if %s holds and is succeeded by %s, " + 
			"then %s previously held.",
			"it is always the case that if %s holds, " +
			"then %s previously held and was preceded by %s.",
			"it is always the case that if %s holds, then %s eventually holds.",
			"it is always the case that if %s holds, " +
			"then %s eventually holds and is succeeded by %s.",
			"it is always the case that if %s holds and is succeeded by %s, " +
			"then %s eventually holds after %s.",
			"it is always the case that if %s holds, " +
			"then %s eventually holds and is succeeded by %s, " + 
			"where %s does not hold between %s and %s."};
	
	public OrderPattern() {
		super();
	}

	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass() {
		return 2;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar() {
		
		int numVar = -1;
		switch (type) {
		case 0:
		case 3:
			numVar = 2;
			break;
			
		case 1:
		case 2:
		case 4:
			numVar = 3;
			break;
			
		case 5:
			numVar = 4;
			break;
			
		case 6:
			numVar = 6;
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
		
		if((type < 0) || (type >= OrderPattern.map.length)) {
			return null;
		}
		
		String stringVerPat = null;
		switch (type) {
		case 0:
		case 3:
			if((patternVar1 != null) && (patternVar2 != null)) {
				stringVerPat = String.format(OrderPattern.map[type],
						patternVar1, patternVar2);
			}
			break;
			
		case 1:
		case 2:
		case 4:
			if((patternVar1 != null) && (patternVar2 != null)
					&& (patternVar3 != null)) {
				
				stringVerPat = String.format(OrderPattern.map[type],
						patternVar1, patternVar2, patternVar3);
				
			}
			break;
			
		case 5:
			if((patternVar1 != null) && (patternVar2 != null)
					&& (patternVar3 != null)) {
				
				stringVerPat = String.format(OrderPattern.map[type],
						patternVar1, patternVar2,
						patternVar3, patternVar2);
				
			}
			break;
			
		case 6:
			if((patternVar1 != null) && (patternVar2 != null)
					&& (this.patternVar3 != null)) {
				
				stringVerPat = String.format(OrderPattern.map[type],
						patternVar1, patternVar2,
						patternVar3, patternVar4,
						patternVar2, patternVar3);
				
			}
			break;

		default:
			break;
		}
		
		if((stringVerPat != null) && (this.scope.asString() != null)) {
			return this.scope.asString() + stringVerPat;
		}
		
		return null; 
		
	}

}
