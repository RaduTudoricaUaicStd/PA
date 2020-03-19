package lab4;
import java.util.*;

class Hospital implements Comparable<Hospital>{
	private String name;

	public String getName(){
		return name;
	}

	public void setName(String newName){
		name = newName;
	}

	public Hospital(String initName){
		name = initName;
	}

	@Override
	public String toString(){
		return getName();
	}

	public int compareTo(Hospital h){
		return name.compareTo(h.getName());
	}
}