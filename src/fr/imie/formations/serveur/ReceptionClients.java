package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceptionClients implements Runnable {
    private ServerSocket   socketServer;
    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;

    /**
     * Constructeur
     * 
     * @param pSocketServer
     */
    public ReceptionClients( final ServerSocket pSocketServer ) {
        socketServer = pSocketServer;
    }

    @Override
    public void run() {
        String client;
        try {
            while ( true ) {
                // Connexion d'un client
                socket = socketServer.accept();
                System.out.println( "Connexion d'un client !" );

                out = new PrintWriter( socket.getOutputStream() );
                in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

                client = in.readLine();

                System.out.println( "Bonjour à " + client );

                out.println( "[Serveur] Bonjour, je suis à votre écoute..." );
                out.flush();
                // socket.close();
                Thread t = new Thread( new DiscutionAvecClient( socket, client ) );
                t.start();
            }
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
    }
}
