package lab7;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Game{
	private int n;
	private int p;
	private int m;

	public Game(int initN, int initP, int initM) {
		n = initN;
		p = initP;
		m = initM;
	}

	public void play(){
		final Board b;
		try{
			b = new Board(n, m);
		}catch(SetSizeException e){
			System.out.println(e);
			return;
		}
		List<Player> players = IntStream.rangeClosed(1, p).boxed().map(num -> new Player("P"+num, b)).collect(Collectors.toList());
		players.stream().forEach(player -> new Thread(player).start());
		while(b.size() > 0){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(e);
				return;
			}
		}
		players.stream().forEach(player -> System.out.println(player));
	}
}