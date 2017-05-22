package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class OrderPattern extends QualitativePattern {
	
	private static String[] map = {"it is always the case that if %s holds, then %s previously held.",
			"it is always the case that if %s holds and is succeeded by %s, then %s previously held.",
			"it is always the case that if %s holds, then %s previously held and was preceded by %s.",
			"it is always the case that if %s holds, then %s eventually holds.",
			"it is always the case that if %s holds, then %s eventually holds and is succeeded by %s.",
			"it is always the case that if %s holds and is succeeded by %s, then %s eventually holds after %s.",
			"it is always the case that if %s holds, then %s eventually holds and is succeeded by %s, " + 
			"where %s does not hold between %s and %s."};
	
	public OrderPattern(){
		super();
	}

	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass(){
		return 2;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar(){
		
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
		
		if(this.type < 0 || this.type >= OrderPattern.map.length){
			return null;
		}
		
		String stringVerPat = null;
		switch (this.type) {
		case 0:
		case 3:
			if(this.patternVar1 != null && this.patternVar2 != null){
				stringVerPat = String.format(OrderPattern.map[type], this.patternVar1, this.patternVar2);
			}
			break;
			
		case 1:
		case 2:
		case 4:
			if(this.patternVar1 != null && this.patternVar2 != null && this.patternVar3 != null){
				stringVerPat = String.format(OrderPattern.map[type], this.patternVar1, this.patternVar2, this.patternVar3);
			}
			break;
			
		case 5:
			if(this.patternVar1 != null && this.patternVar2 != null && this.patternVar3 != null){
				stringVerPat = String.format(OrderPattern.map[type], this.patternVar1, this.patternVar2,
						this.patternVar3, this.patternVar2);
			}
			break;
			
		case 6:
			if(this.patternVar1 != null && this.patternVar2 != null && this.patternVar3 != null){
				stringVerPat = String.format(OrderPattern.map[type], this.patternVar1, this.patternVar2,
						this.patternVar3, this.patternVar4, this.patternVar2, this.patternVar3);
			}
			break;

		default:
			break;
		}
		
		if(stringVerPat != null && this.scope.asString() != null){
			return this.scope.asString() + stringVerPat;
		}
		
		return null; 
		
	}

}
