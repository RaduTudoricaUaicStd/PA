package lab7;

import java.util.*;
import java.util.stream.*;

public class Board{
	private List<Token> tokenList = null;
	private Integer n;
	private Integer m;

	public Board(Integer initN, Integer initM) throws SetSizeException{
		n = initN;
		m = initM;
		if(n > m){
			throw new SetSizeException("Cannot construct an array of "+n.toString()+" distinct elements from a set of "+m.toString()+" elements");
		}
		List<Integer> tokenValues = IntStream.rangeClosed(1, m).boxed().collect(Collectors.toList());
		Collections.shuffle(tokenValues);
		tokenList = tokenValues.stream().limit(n).map(element -> new Token(element)).collect(Collectors.toList());
	}

	public List<Token> getTokens(){
		return tokenList;
	}

	public int size(){
		return tokenList.size();
	}

	public synchronized Token getToken(){
		if(tokenList.size() > 0){
			Token returned = tokenList.get(0);
			tokenList.remove(0);
			return returned;
		}else{
			return null;
		}
	}
}