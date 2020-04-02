package lab7;

import java.util.*;

class Player implements Runnable{
	private String name = null;
	private List<Token> tokens = null;
	private final Board board;

	public Player(String initName, Board initBoard){
		name = initName;
		board = initBoard;
		tokens = new ArrayList();
	}

	public String getName(){
		return name;
	}

	public void setName(String newName){
		name = newName;
	}

	@Override
	public String toString(){
		if(tokens != null){
			return getName()+": "+tokens.toString();
		}else{
			return getName()+": null";
		}
	}

	@Override
	public void run(){
		Token taken = board.getToken();
		while(taken != null){
			tokens.add(taken);
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(e);
				return;
			}
			taken = board.getToken();
		}
	}
}