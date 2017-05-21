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
	 * @see FSPattern#getPatternVar3()
	 */
	public String getPatternVar3(){
		return null;
	}

	/* (non-Javadoc)
	 * @see FSPattern#setPatternVar3()
	 */
	public void setPatternVar3(String patternVar1){}
	
	/* (non-Javadoc)
	 * @see FSPattern#getPatternVar4()
	 */
	public String getPatternVar4(){
		return null;
	}

	/* (non-Javadoc)
	 * @see FSPattern#setPatternVar4()
	 */
	public void setPatternVar4(String patternVar1){}

	/* (non-Javadoc)
	 * @see FSPattern#asString()
	 */
	@Override
	public int getPatternClass(){
		return 3;
	}
	
	/* (non-Javadoc)
	 * @see FSPattern#getNumVar()
	 */
	@Override
	public int getNumVar(){
		
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
