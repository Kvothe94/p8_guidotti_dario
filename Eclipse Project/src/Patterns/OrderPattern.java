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
	
	private String patternVar2;
	private String patternVar3;
	private String patternVar4;
	
	public OrderPattern(){
		super();
		this.patternVar2 = null;
		this.patternVar3 = null;
	}
	
	public String getPatternVar2() {
		return patternVar2;
	}

	public void setPatternVar2(String patternVar2) {
		this.patternVar2 = patternVar2;
	}

	public String getPatternVar3() {
		return patternVar3;
	}

	public void setPatternVar3(String patternVar3) {
		this.patternVar3 = patternVar3;
	}
	
	public String getPatternVar4() {
		return patternVar3;
	}

	public void setPatternVar4(String patternVar4) {
		this.patternVar4 = patternVar4;
	}

	/* (non-Javadoc)
	 * @see Pattern#asString()
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
