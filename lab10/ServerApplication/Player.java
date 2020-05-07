package ServerApplication;

import java.net.*;
import java.io.*;

class Player{
	private String name = null;
	private BoardTokens token = null;
	public ClientThread client = null;

	Player(String name, BoardTokens token, ClientThread client){
		this.name = name;
		this.token = token;
		this.client = client;
	}

	String getName(){
		return name;
	}

	void setName(String name){
		this.name = name;
	}

	BoardTokens getToken(){
		return token;
	}

	void setToken(BoardTokens token){
		this.token = token;
	}
}	