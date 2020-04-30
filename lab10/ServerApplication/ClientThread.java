package ServerApplication;

import java.net.*;
import java.io.*;

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
					server.stop();
				}else if(command.equals("exit")){
					socket.close();
					running = false;
				}else{
					socket_output.println("Server received the request "+command);
					socket_output.flush();
				}
			}catch(IOException e){
				System.out.println("Eroare la comunicarea cu serverul: "+e);
			}
		}
		System.out.println("[*] Connection closed from "+socket.getInetAddress().getHostAddress());
	}
}