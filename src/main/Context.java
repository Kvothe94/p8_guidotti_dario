/*
 * 
 */
package main;
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
 * Classe che contiene le "condizioni al contorno" per la traduzione
 * dei requisiti in linguaggio naturale in FSPattern.
 * In particolare contiene l'ontologia e i metodi per gestirla e
 * accedervi. 
 * Inoltre contiene una mappatura tra i nomi dei segnali
 * che possono comparire nei nostri requisiti e il loro tipo.
 * La classe si appoggia all'{@link API JENA} per quanto riguarda la gestione
 * e l'interrogazione dell'ontologia.
 * 
 * @author Guidotti Dario
 * @version 1.0
 */
public class Context {
	
	/** URL dell'ontologia. */
	private String ontPath;
	
	/** Namespace dell'ontologia */
	private String ontNS;
	
	private OntModel ontModel;
	private HashMap<String, String> signalMap; 
	
	/**
	 * 
	 * @param ontPath URL dell'ontologia
	 * @param ontNS Namespace dell'ontologia
	 * @param signalFilePath Path relativo del file csv in cui sono
	 *        indicati i tipi dei segnali.
	 * 
	 */
	public Context(String ontPath, String ontNS, String signalFilePath)
			throws FileNotFoundException {
		
		this.ontPath = ontPath;
		this.ontNS = ontNS;
		this.ontModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM );
		
		this.ontModel.read(this.ontPath);
		this.signalMap = new HashMap<String, String>();
		
		File signalFile = new File(signalFilePath);
		Scanner inputStream = null;
		
		inputStream = new Scanner(signalFile);
		
		inputStream.nextLine();
		while (inputStream.hasNextLine()) {
			
		    String line = inputStream.nextLine();
		    String[] fields = line.split(",");
		    signalMap.put(fields[0], fields[2]);
		    
		}
		
		inputStream.close();
		
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

	/**
	 * Metodo che verifica che la stringa passata come parametro
	 * corrisponda al nome di una risorsa all'interno dell'ontologia.
	 * 
	 * @param word la stringa di cui vogliamo verificare la presenza in
	 *        forma di risorsa all'interno dell'ontologia.
	 * @return true se la risorsa esiste, false altrimenti.
	 */
	public boolean isResource(String word) {
		
		boolean ver = false;
		OntResource res = ontModel.getOntResource(ontNS + word);
		if(res != null) {
			ver = true;
		}
		return ver;
		
	}
	
	/**
	 * Metodo che verifica che la stringa passata come parametro
	 * corrisponda al nome di un entità all'interno dell'ontologia.
	 * 
	 * @param word la stringa di cui vogliamo verificare la presenza in
	 *        forma di entità all'interno dell'ontologia.
	 * @return true se l'entità esiste, false altrimenti.
	 */
	public boolean isEntity(String word) {
		
		boolean ver = false;
		OntResource res = ontModel.getOntClass(ontNS + word);
		if(res != null) {
			ver = true;
		}
		return ver;
		
	}
	
	/**
	 * Metodo che verifica che la stringa passata come parametro
	 * corrisponda al nome di una proprietà all'interno dell'ontologia.
	 * 
	 * @param word la stringa di cui vogliamo verificare la presenza in
	 *        forma di proprietà all'interno dell'ontologia.
	 * @return true se la proprietà esiste, false altrimenti.
	 */
	public boolean isProperty(String word) {
		
		boolean ver = false;
		OntResource res = ontModel.getOntProperty(ontNS + word);
		if(res != null) {
			ver = true;
		}
		return ver;
		
	}
	
	/**
	 * Metodo che verifica che la stringa passata come parametro
	 * corrisponda al nome di un dominio all'interno dell'ontologia.
	 * 
	 * @param word la stringa di cui vogliamo verificare la presenza in
	 *        forma di dominio all'interno dell'ontologia.
	 * @return true se il dominio esiste, false altrimenti.
	 */
	public boolean isDomain(String word) {
		
		boolean ver = false;
		
		if(isResource(word)){
			OntResource res = ontModel.getOntResource(ontNS + word);
			ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
			while(iter.hasNext()) {
			
				if(iter.next().hasDomain(res)) {
					ver = true;
				}
			
			}
		
		}
		
		return ver;
		
	}
	
	/**
	 * Metodo che verifica che la stringa passata come parametro
	 * corrisponda al nome di un range all'interno dell'ontologia.
	 * 
	 * @param word la stringa di cui vogliamo verificare la presenza in
	 *        forma di range all'interno dell'ontologia.
	 * @return true se il range esiste, false altrimenti.
	 */
	public boolean isRange(String word) {
		
		boolean ver = false;
		
		if(isResource(word)){
			OntResource res = ontModel.getOntResource(ontNS + word);
			ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
			while(iter.hasNext()) {
			
				if(iter.next().hasRange(res)) {
					ver = true;
				}
			
			}
		
		}
		
		return ver;
		
	}
	
	/**
	 * Metodo che restituisce la lista delle proprietà presenti all'
	 * interno dell'ontologia che presentano come dominio e range i valori
	 * passati come argomento.
	 * 
	 * @param domain il dominio in riferimento al quale vogliamo trovare
	 *        le proprietà.
	 * @param range il range in riferimento al quale vogliamo trovare
	 *        le proprietà.
	 * @return la lista delle proprietà trovate. In caso di errore
	 *         restituisce null.
	 */
	public List<OntProperty> getProperties(String domain, String range) {
		
		ArrayList<OntProperty> returnList = null;
		if((isDomain(domain)) && (isRange(range))) {
			
			ArrayList<OntProperty> auxList = new ArrayList<OntProperty>();
			OntResource domRes = ontModel.getOntResource(ontNS + domain);
			OntResource ranRes = ontModel.getOntResource(ontNS + range);
			ExtendedIterator<OntProperty> iter = ontModel.listAllOntProperties();
			
			while(iter.hasNext()){
				OntProperty tempProp = iter.next();
				if((tempProp.hasDomain(domRes)) 
						&& (tempProp.hasRange(ranRes))) {
					
					auxList.add(tempProp);
				}
			}
			
			if(!auxList.isEmpty()){
				returnList = auxList;
			}
	
		}
		
		return returnList;
		
	}
	
	/**
	 * Metodo che restituisce, se esiste, la proprietà corrispondente alla
	 * stringa passata come argomento.
	 * 
	 * @param word la stringa che identifica la proprietà che ricerchiamo
	 *        all'interno dell'ontologia.
	 * @return la proprietà trovata. Restituisce null in caso di errore.
	 * 
	 */
	public OntProperty getProperty(String word) {
		
		OntProperty retOntProperty = null;
		if(isProperty(word)) {
			retOntProperty = ontModel.getOntProperty(ontNS + word);
		} 

		return retOntProperty;
	}
	
	/**
	 * Metodo che restituisce, se esiste, la risorsa corrispondente alla
	 * stringa passata come argomento.
	 * 
	 * @param word la stringa che identifica la risorsa che ricerchiamo
	 *        all'interno dell'ontologia.
	 * @return la risorsa trovata. Restituisce null in caso di errore.
	 * 
	 */
	public OntResource getResource(String word) {
		
		OntResource retOntResource = null;
		if(isResource(word)) {
			retOntResource = ontModel.getOntResource(ontNS + word);
		} 

		return retOntResource;
		
	}
	
	/**
	 * Metodo che restituisce, se esiste, l'entità corrispondente alla
	 * stringa passata come argomento.
	 * 
	 * @param word la stringa che identifica l'entità che ricerchiamo
	 *        all'interno dell'ontologia.
	 * @return l'entità trovata. Restituisce null in caso di errore.
	 * 
	 */
	public OntClass getEntity(String word) {
		
		OntClass retOntEntity = null;
		if(isEntity(word)) {
			retOntEntity = ontModel.getOntClass(ontNS + word);
		} 

		return retOntEntity;
		
	}
	
}
