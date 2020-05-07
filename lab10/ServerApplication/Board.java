package ServerApplication;

class Board{
	private BoardTokens [][]board = null;
	
	Board(){
		board = new BoardTokens[15][15];
		for(int i = 0; i<15; i++){
			for(int j = 0 ;j<15; j++){
				board[i][j] = null;
			}
		}
	}

	BoardTokens winState(){
		for(int i = 0; i<15; i++){
			for(int j = 0; j<15; j++){
				if(board[i][j] == null){
					continue;
				}
				BoardTokens result = checkAscRow(i, j);
				if(result != null){
					return result;
				}
				result = checkDescRow(i, j);
				if(result != null){
					return result;
				}
				result = checkAscColumn(i, j);
				if(result != null){
					return result;
				}
				result = checkDescColumn(i, j);
				if(result != null){
					return result;
				}
				result = checkAscDiag(i, j);
				if(result != null){
					return result;
				}
				result = checkDescDiag(i, j);
				if(result != null){
					return result;
				}
			}
		}
		return null;
	}

	int mod(int x, int t){
		while(x < 0){
			x = t + x;
		}
		if(x >= t){
			return x%t;
		}
		return x;
	}

	private BoardTokens checkAscRow(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i < 5; i++){
			if(board[x][mod(y+i, 15)] != value){
				return null;
			}
		}
		return value;
	}

	private BoardTokens checkDescRow(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i < 5; i++){
			if(board[x][mod(y-i, 15)] != value){
				return null;
			}
		}
		return value;
	}

	private BoardTokens checkAscColumn(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i < 5; i++){
			if(board[mod(x+i, 15)][y] != value){
				return null;
			}
		}
		return value;
	}

	private BoardTokens checkDescColumn(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i < 5; i++){
			if(board[mod(x-i, 15)][y] != value){
				return null;
			}
		}
		return value;
	}

	private BoardTokens checkAscDiag(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i<5; i++){
			if(board[mod(x+i, 15)][mod(y+i, 15)] != value){
				return null;
			}
		}
		return value;
	}

	private BoardTokens checkDescDiag(int x, int y){
		BoardTokens value = board[x][y];
		for(int i = 0; i<5; i++){
			if(board[mod(x-i, 15)][mod(y-i, 15)] != value){
				return null;
			}
		}
		return value;
	}

	boolean setPosition(int i, int j, BoardTokens token){
		if( i < 0 || i > 14 || j < 0 || j > 14){
			return false;
		}
		if(board[i][j] != null || token == null){
			return false;
		}
		board[i][j] = token;
		return true;
	}

	BoardTokens[][] getBoard(){
		return board;
	}

	int[][] serializeBoard(){
		int[][] serializedBoard;
		serializedBoard = new int[15][15];
		for(int i = 0; i<15; i++){
			for(int j = 0; j<15; j++){
				if(board[i][j] == null){
					serializedBoard[i][j] = 0;
				}
				if(board[i][j] == BoardTokens.WHITE){
					serializedBoard[i][j] = 1;
				}
				if(board[i][j] == BoardTokens.BLACK){
					serializedBoard[i][j] = 2;
				}
			}
		}
		return serializedBoard;
	}

	String serializeBoardString(){
		String serializedBoard = "";
		for(int i = 0; i<15; i++){
			for(int j = 0; j<15; j++){
				if(board[i][j] == null){
					serializedBoard += '0';
				}
				if(board[i][j] == BoardTokens.WHITE){
					serializedBoard += '1';
				}
				if(board[i][j] == BoardTokens.BLACK){
					serializedBoard += '2';
				}
			}
			serializedBoard += '\n';
		}
		return serializedBoard;
	}

}