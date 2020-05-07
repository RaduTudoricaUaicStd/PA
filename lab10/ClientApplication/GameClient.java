package ClientApplication;

import java.net.*;
import java.io.*;

class GameClient{
	private Socket socket = null;
	private BufferedReader socket_input = null;
	private PrintWriter socket_output = null;
	private boolean running = true;
	
	GameClient(String ip, int port){
		try{
			socket = new Socket(ip, port);
			socket_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket_output = new PrintWriter(socket.getOutputStream());
		}catch(Exception e){
			socket = null;
			socket_input = null;
			socket_output = null;
		}
	}

	public void run(){
		if((socket != null) && (socket_input != null) && (socket_output != null)){
			running = true;
		}
		System.out.println("Commands:");
		System.out.println("exit       ---> exits the game");
		System.out.println("stop       ---> closes the server");
		System.out.println("list games ---> lists all the available games");
		System.out.println("create     ---> creates a new game (makes you wait in a lobby until another player connects)");
		System.out.println("join       ---> join an existing game and play");
		while(running){
			try{
				System.out.print("> ");
				String cmd = System.console().readLine();
				socket_output.println(cmd);
				socket_output.flush();
				if(cmd.equals("exit")){
					socket.close();
					running = false;
				}else{
					while(true){
						String response = socket_input.readLine();
						if(response == null){
							break;
						}
						if(response.equals("\0")){
							break;
						}
						System.out.println(response);
					}
				}
			}catch(IOException e){
				System.out.println("Eroare la comunicarea cu serverul: "+e);
			}
		}
	}
}