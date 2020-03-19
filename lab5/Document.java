package lab5;

import java.io.*;
import java.awt.Desktop;
import java.util.*;
import java.net.*;

class Document implements java.io.Serializable{
	private String id = null;
	private String name = null;
	private String location = null;
	private Map<String, String> tags = new HashMap();

	public static boolean fileExists(String path){
		File testFile = new File(path);
		return (testFile.exists() && !testFile.isDirectory());
	}

	public Document(String docId, String docName, String docLocation) throws InvalidFileLocation{
		id = docId;
		name = docName;
		if(!docLocation.contains("://")){
			if(!fileExists(docLocation)){
				throw new InvalidFileLocation(docLocation);
			}
		}
		location = docLocation;
	}

	public Document(String docId, String docName, String docLocation, Map<String, String> docTags) throws InvalidFileLocation{
		id = docId;
		name = docName;
		tags = docTags;
		if(!docLocation.contains("://")){
			if(!fileExists(docLocation)){
				throw new InvalidFileLocation(docLocation);
			}
		}
		location = docLocation;
	}

	public String getName(){
		return name;
	}

	public String getLocation(){
		return location;
	}

	public String getId(){
		return id;
	}

	public void setName(String newName){
		name = newName;
	}

	public void setLocation(String newLocation){
		location = newLocation;
	}

	public void setId(String newId){
		id = newId;
	}

	public Map<String, String> getTags(){
		return tags;
	}

	public void addTag(String key, String value){
		tags.put(key, value);
	}

	public boolean removeTag(String key){
		if(tags.containsKey(key)){
			tags.remove(key);
			return true;
		}
		return false;
	}

	public boolean view() throws InvalidFileLocation{
		if(location == null){
			return false;
		}
		if(!location.contains("://")){
			if(!fileExists(location)){
				throw new InvalidFileLocation(location);
			}
			File f = new File(location);
			try{
				Desktop.getDesktop().open(f);
			}catch(IOException e){
				return false;
			}
			return true;
		}
		try{
			URI u = new URI(location);
			Desktop.getDesktop().browse(u);
		}catch(URISyntaxException e){
			return false;
		}catch(IOException e){
				return false;
		}
		return true;
	}
}