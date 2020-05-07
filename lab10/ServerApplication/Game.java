package ServerApplication;

class Game{
	private Player whiteToken = null;
	private Player blackToken = null;
	private Board board = null;
	private BoardTokens turn = null;
	public boolean finished = false;

	Game(String blackTokenName, ClientThread client){
		blackToken = new Player(blackTokenName, BoardTokens.BLACK, client);
		board = new Board();
		turn = BoardTokens.BLACK;
	}

	Board getBoard(){
		return board;
	}

	void playerJoin(String whiteTokenName, ClientThread client){
		whiteToken = new Player(whiteTokenName, BoardTokens.WHITE, client);
	}

	boolean playRound(int i, int j){
		if(board.setPosition(i, j, turn)){
			if(turn == BoardTokens.WHITE){
				turn = BoardTokens.BLACK;
			}else{
				turn = BoardTokens.WHITE;
			}
			return true;
		}
		return false;
	}

	Player winner(){
		BoardTokens result = board.winState();
		if(result == BoardTokens.WHITE){
			return whiteToken;
		}
		if(result == BoardTokens.BLACK){
			return blackToken;
		}
		return null;
	}

	Player loser(){
		BoardTokens result = board.winState();
		if(result == BoardTokens.WHITE){
			return blackToken;
		}
		if(result == BoardTokens.BLACK){
			return whiteToken;
		}
		return null;
	}

	Player getTurnPlayer(){
		if(turn == BoardTokens.WHITE){
			return whiteToken;
		}
		return blackToken;
	}

	void gameFinished(){
		finished = true;
	}

	String getName(){
		return blackToken.getName();
	}
}