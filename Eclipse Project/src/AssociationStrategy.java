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
	
	public abstract Pattern associatePattern(Requirement requirement);
	
}
