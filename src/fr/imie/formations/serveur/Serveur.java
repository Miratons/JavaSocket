package fr.imie.formations.serveur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket hostServer;
		try {
			
			
			hostServer = new ServerSocket(8080);
			System.out.println("serveur disponnible sur le port 8080");
			// attente de la connexion d’un client
			Socket socket = hostServer.accept();
			System.out.print("connexion d'un client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
