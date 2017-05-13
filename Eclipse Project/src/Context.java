import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * 
 */

/**
 * @author Guidotti Dario
 *
 */
public class Context {
	
	private String ontPath;
	private String ontNS;
	private OntModel ontModel;
	
	public Context(String ontPath, String ontNS){
		
		this.ontPath = ontPath;
		this.ontNS = ontNS;
		this.ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		this.ontModel.read(this.ontPath);
		
	}
	
	/**
	 * @return the ontPath
	 */
	public String getOntPath() {
		return ontPath;
	}
	
	/**
	 * @param ontPath the ontPath to set
	 */
	public void setOntPath(String ontPath) {
		this.ontPath = ontPath;
	}
	
	/**
	 * @return the ontModel
	 */
	public OntModel getOntModel() {
		return ontModel;
	}
	
	/**
	 * @param ontModel the ontModel to set
	 */
	public void setOntModel(OntModel ontModel) {
		this.ontModel = ontModel;
	}
	
	public boolean isResource(String word){
		
		OntResource res = ontModel.getOntResource(ontNS + word);
		if(res != null){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isEntity(String word){
		
		OntResource res = ontModel.getOntClass(ontNS + word);
		if(res != null){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isProperty(String word){
		
		OntResource res = ontModel.getOntProperty(ontNS + word);
		if(res != null){
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isDomain(String word){
		
		if(!isResource(word)){
			return false;
		} else {
			OntResource res = ontModel.getOntResource(ontNS + word);
			ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
			while(iter.hasNext()){
				if(iter.next().hasDomain(res)){
					return true;
				}
			}
			return false;
		}
		
	}
	
	public boolean isRange(String word){
		
		if(!isResource(word)){
			return false;
		} else {
			OntResource res = ontModel.getOntResource(ontNS + word);
			ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
			while(iter.hasNext()){
				if(iter.next().hasRange(res)){
					return true;
				}
			}
			return false;
		}
		
	}
	
	public List<OntProperty> getProperties(String domain, String range){
		
		if(!isDomain(domain) || !isRange(range)){
			return null;
		}
		
		ArrayList<OntProperty> propList = new ArrayList<OntProperty>();
		OntResource domRes = ontModel.getOntResource(ontNS + domain);
		OntResource ranRes = ontModel.getOntResource(ontNS + range);
		ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
		while(iter.hasNext()){
			OntProperty tempProp = iter.next();
			if(tempProp.hasDomain(domRes) && tempProp.hasRange(ranRes)){
				propList.add(tempProp);
			}
		}
		
		if(propList.isEmpty()){
			return null;
		} else {
			return propList;
		}
		
	}
	
	public OntProperty getProperty(String word){
		return ontModel.getOntProperty(ontNS + word);
	}
	
	public OntResource getResource(String word){
		return ontModel.getOntResource(ontNS + word);
	}
	
	public OntClass getEntity(String word){
		return ontModel.getOntClass(ontNS + word);
	}
	
}
