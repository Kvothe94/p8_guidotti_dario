import java.util.List;
import Patterns.*;
/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public abstract class AssociationStrategy {
	
	protected Context context;
	protected List<FSPattern> patternsModel;
	
	/**
	 * @param context
	 * @param patternsModel
	 */
	public AssociationStrategy(Context context, List<FSPattern> patternsModel){
		this.context = context;
		this.patternsModel = patternsModel;
	}
	
	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the patternsModel
	 */
	public List<FSPattern> getPatternsModel() {
		return patternsModel;
	}

	/**
	 * @param patternsModel the patternsModel to set
	 */
	public void setPatternsModel(List<FSPattern> patternsModel) {
		this.patternsModel = patternsModel;
	}

	public abstract FSPattern associatePattern(Requirement requirement);
	
}
