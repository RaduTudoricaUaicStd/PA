package lab7;

public class Token{
	private Integer value = null;

	Token(Integer initValue){
		value = initValue;
	}

	public Integer getValue(){
		return value;
	}

	public String toString(){
		if(value != null){
			return value.toString();
		}
		return "blank";
	}
}