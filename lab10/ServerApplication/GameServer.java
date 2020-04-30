package ServerApplication;

import java.net.*;
import java.io.*;

class GameServer{
	private ServerSocket server = null;
	private boolean running = true;

	GameServer(int port, int timeout){
		try{
			server = new ServerSocket(port);
			server.setSoTimeout(timeout);
			server.setReuseAddress(true);
			System.out.println("[*] Listening for a new connection");
			while(running){
				try{
					new Thread(new ClientThread(this, server.accept())).start();
				}catch(SocketTimeoutException e){
					continue;
				}
			}
		}catch(IOException e){
			System.out.println("[!] The server encountered the error " + e);
			return;	
		}
		System.out.println("[*] The server is closing");
		try{
			server.close();
		}catch(Exception e){
			System.out.println("[!] Cannot close the server");
		}
	}

	void stop(){
		running = false;
	}
}