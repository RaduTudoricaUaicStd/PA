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
		while(running){
			try{
				System.out.print("comanda> ");
				String cmd = System.console().readLine();
				socket_output.println(cmd);
				socket_output.flush();
				if(cmd.equals("exit")){
					socket.close();
					running = false;
				}else{
					String response = socket_input.readLine();
					if(response == null){
						break;
					}
					System.out.println("raspuns> "+response);
				}
			}catch(IOException e){
				System.out.println("Eroare la comunicarea cu serverul: "+e);
			}
		}
	}
}