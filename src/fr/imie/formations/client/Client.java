package fr.imie.formations.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private static Socket socket;
	
	private final static String MESSAGE = "SERVEUR : J'ai bien reçus votre message.";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			socket = new Socket("127.0.0.1", 8080);
			listen();
			reply();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reply() throws IOException{
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(MESSAGE);
		out.flush();
	}
	
	public static void listen() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		if (in.ready()) {
			String s = in.readLine();
			// le code suivant sera exécuté une fois que le message est reçu
			reply();
		}
	}

}
