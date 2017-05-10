package Patterns;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class Scope {
	
	private int type;
	private static String[] map = {"Globally, ", "Before %s, ", "After %s, ", "Between %s and %s, ", "After %s until %s, "};
	
	private String scopeVar1;
	private String scopeVar2;
	
	public Scope(){
		
		this.type = -1;
		this.scopeVar1 = null;
		this.scopeVar2 = null;
	
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getScopeVar1() {
		return scopeVar1;
	}
	
	public void setScopeVar1(String scopeVar1) {
		this.scopeVar1 = scopeVar1;
	}
	
	public String getScopeVar2() {
		return scopeVar2;
	}
	
	public void setScopeVar2(String scopeVar2) {
		this.scopeVar2 = scopeVar2;
	}
	
	public String asString(){
		
		//Type not valid: it cannot be converted
		if(this.type < 0 || this.type >= Scope.map.length){
			return null;
		}
		
		String stringVer = null;
		
		switch (this.type) {
		case 0:
			stringVer = String.format(Scope.map[0], scopeVar1, scopeVar2);
			break;
			
		case 1:
			if(scopeVar1 != null){
				stringVer = String.format(Scope.map[1], scopeVar1, scopeVar2);
			}
			break;
			
		case 2:
			if(scopeVar1 != null){
				stringVer = String.format(Scope.map[2], scopeVar1, scopeVar2);
			}
			break;
			
		case 3:
			if(scopeVar1 != null && scopeVar2 != null){
				stringVer = String.format(Scope.map[3], scopeVar1, scopeVar2);
			}
			break;
			
		case 4:
			if(scopeVar1 != null && scopeVar2 != null){
				stringVer = String.format(Scope.map[4], scopeVar1, scopeVar2);
			}
			break;

		default:
			break;
		}
		
		return stringVer;
		
	}
	
}
