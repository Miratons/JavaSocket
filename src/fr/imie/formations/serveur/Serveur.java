package fr.imie.formations.serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	private static Socket socket;
	
	private final static String MESSAGE = "SERVEUR : J'ai bien reçus votre message.";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket hostServer;
		try {
			hostServer = new ServerSocket(8080);
			System.out.println("serveur disponnible sur le port 8080");
			// attente de la connexion d’un client
			socket = hostServer.accept();
			System.out.print("connexion d'un client");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			if (in.ready()) {
				String s = in.readLine();
				// le code suivant sera exécuté une fois que le message est reçu
				reply();
			}
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
}
