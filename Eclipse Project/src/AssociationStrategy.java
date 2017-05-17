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
	
	private Context context;
	private List<Pattern> patternsModel;
	
	/**
	 * @param context
	 * @param patternsModel
	 */
	public AssociationStrategy(Context context, List<Pattern> patternsModel){
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
	public List<Pattern> getPatternsModel() {
		return patternsModel;
	}

	/**
	 * @param patternsModel the patternsModel to set
	 */
	public void setPatternsModel(List<Pattern> patternsModel) {
		this.patternsModel = patternsModel;
	}

	public abstract Pattern associatePattern(Requirement requirement);
	
}
