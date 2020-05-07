package lab4;
import java.util.*;

class Resident{
	private String name;

	public String getName(){
		return name;
	}

	public void setName(String newName){
		name = newName;
	}

	public Resident(String initName){
		name = initName;
	}

	@Override
	public String toString(){
		return getName();
	}
}