package lab5;
import java.io.*;
import java.util.*;

public class Catalog implements java.io.Serializable{
	private Map<String, Document> documents = new HashMap();
	private String name = null;
	private String saveLocation = null;

	public Catalog(){

	}

	public Catalog(String initialName, String initialSaveLocation){
		name = initialName;
		saveLocation = initialSaveLocation;
	}

	public Catalog(String initialName, String initialSaveLocation, Map<String, Document> initialDocuments){
		name = initialName;
		saveLocation = initialSaveLocation;
		documents = initialDocuments;
	}

	public String getName(){
		return name;
	}

	public String getSaveLocation(){
		return saveLocation;
	}

	public void setName(String newName){
		name = newName;
	}

	public void setSaveLocation(String newSaveLocation){
		saveLocation = newSaveLocation;
	}

	public boolean save(){
		try{
			FileOutputStream outputFileStream = new FileOutputStream(saveLocation);
			ObjectOutputStream objectWriter = new ObjectOutputStream(outputFileStream);
			objectWriter.writeObject(this);
			objectWriter.close();
			outputFileStream.close();
			return true;
		}
		catch(Exception e) 
        { 
            return false;
        } 
	}

	public static Catalog load(String loadLocation){
		try{
			FileInputStream inputFileStream = new FileInputStream(loadLocation);
			ObjectInputStream objectReader = new ObjectInputStream(inputFileStream);
			Catalog tmpObject = (Catalog)objectReader.readObject();
			objectReader.close();
			inputFileStream.close();
			return tmpObject;
		}
		catch(Exception e){
			return null;
		}
	}

	public Document getDocById(String docId){
		if(documents.containsKey(docId)){
			return documents.get(docId);
		}else{
			return null;
		}
	}

	public boolean addDocument(Document newDoc){
		if(!documents.containsKey(newDoc.getId())){
			documents.put(newDoc.getId(), newDoc);
			return true;
		}
		return false;
	}
}