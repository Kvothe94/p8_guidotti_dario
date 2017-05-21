import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
	private HashMap<String, String> signalMap; 
	
	public Context(String ontPath, String ontNS, String signalFilePath){
		
		this.ontPath = ontPath;
		this.ontNS = ontNS;
		this.ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		this.ontModel.read(this.ontPath);
		this.signalMap = new HashMap<String, String>();
		
		File signalFile = new File(signalFilePath);
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(signalFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputStream.nextLine();
		while (inputStream.hasNextLine())
		{
		    String line = inputStream.nextLine();
		    String[] fields = line.split(",");
		    signalMap.put(fields[0], fields[2]);
		    
		}
		
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
	
	/**
	 * @return the ontNS
	 */
	public String getOntNS() {
		return ontNS;
	}

	/**
	 * @param ontNS the ontNS to set
	 */
	public void setOntNS(String ontNS) {
		this.ontNS = ontNS;
	}

	/**
	 * @return the signalMap
	 */
	public HashMap<String, String> getSignalMap() {
		return signalMap;
	}

	/**
	 * @param signalMap the signalMap to set
	 */
	public void setSignalMap(HashMap<String, String> signalMap) {
		this.signalMap = signalMap;
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
