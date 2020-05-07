package lab5;

public class InvalidFileLocation extends Exception{
	public InvalidFileLocation(){
		super("Fisierul nu exista!");
	}
	public InvalidFileLocation(String file){
		super("Fisierul "+file+" nu exista!");
	}
}