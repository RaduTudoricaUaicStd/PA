package ServerApplication;

import java.net.*;
import java.io.*;
import java.util.*;

class ClientThread implements Runnable{
	private GameServer server = null;
	private Socket socket = null;
	private BufferedReader socket_input = null;
	private PrintWriter socket_output = null;
	private boolean running = true;
	ClientThread(GameServer server, Socket socket){
		this.server = server;
		this.socket = socket;
		try{
			this.socket_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.socket_output = new PrintWriter(socket.getOutputStream());
			System.out.println("[*] Got a new connection from "+socket.getInetAddress().getHostAddress());
		}catch(Exception e){
			this.socket_input = null;
			this.socket_output = null;
		}
	}

	public String read(){
		try{
			return socket_input.readLine();
		}catch(IOException e){
			return null;
		}
	}

	public void write(String inp){
		socket_output.println(inp);
		socket_output.flush();
	}

	public void run(){
		if((socket != null) && (server != null) && (socket_input != null) && (socket_output != null)){
			running = true;
		}
		while(running){
			try{
				String command = socket_input.readLine();
				if(command == null){
					break;
				}
				System.out.println("[*] Got command \""+command+"\"");
				if(command.equals("stop")){
					socket_output.print("Server stopped\n");
					socket_output.flush();
					socket_output.print("\0\n");
					socket_output.flush();
					server.stop();
				}else if(command.equals("exit")){
					socket.close();
					running = false;
				}else if(command.equals("list games")){
					for(int i = 0; i<server.gamesInWaiting.size(); i++){
						socket_output.println(Integer.toString(i)+" "+server.gamesInWaiting.get(i).getName());
						socket_output.flush();
					}
					socket_output.println("\0");
					socket_output.flush();
				}else if(command.equals("create")){
					socket_output.print("Enter your name\n");
					socket_output.flush();
					socket_output.print("\0\n");
					socket_output.flush();
					String name = socket_input.readLine();
					Game newGame = new Game(name, this);
					socket_output.print("Please wait for another player\n");
					socket_output.flush();
					server.gamesInWaiting.add(newGame);
					while(newGame.finished == false){
						try{
							Thread.sleep(100);
						}catch(Exception e){
							continue;
						}
					}
				}else if(command.equals("join")){
					socket_output.print("Enter the game number\n");
					socket_output.flush();
					socket_output.print("\0\n");
					socket_output.flush();
					int gameNumber = Integer.parseInt(socket_input.readLine());
					Game joinGame = server.gamesInWaiting.get(gameNumber);
					server.gamesInWaiting.remove(joinGame);
					socket_output.print("Enter your name\n");
					socket_output.flush();
					socket_output.print("\0\n");
					socket_output.flush();
					String name = socket_input.readLine();
					joinGame.playerJoin(name, this);
					while(joinGame.winner() == null){
						Player p = joinGame.getTurnPlayer();
						int x, y;
						p.client.write(joinGame.getBoard().serializeBoardString());
						do{
							p.client.write("select line");
							p.client.write("\0");
							x = Integer.parseInt(p.client.read());
							p.client.write("select row");
							p.client.write("\0");
							y = Integer.parseInt(p.client.read());
						}while(!joinGame.playRound(x, y));
					}
					joinGame.winner().client.write("You won!");
					joinGame.winner().client.write("\0");
					joinGame.loser().client.write("You lost!");
					joinGame.loser().client.write("\0");
				}else{
					socket_output.println("Server received the request "+command);
					socket_output.flush();
				}
			}catch(IOException e){
				System.out.println("Eroare la comunicarea cu clientul: "+e);
			}
		}
		System.out.println("[*] Connection closed from "+socket.getInetAddress().getHostAddress());
	}
}