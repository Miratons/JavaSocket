package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements Runnable {
    /**
     * Point d'entrée.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        Serveur serveur = new Serveur( 8082 );
        Thread th = new Thread( serveur );
        th.start();
    }

    private final static String MESSAGE = "SERVEUR : J'ai bien re�us votre message.";

    /*
     * Attributs
     */
    private int                 port;
    private ServerSocket        socketServeur;
    private Socket              socket;
    private int                 nbClients;
    private boolean             flagFonctionnement;

    /**
     * Constructeur
     * 
     * @param pPort
     *            Le port d'écoute du serveur.
     */
    public Serveur( final int pPort ) {
        port = pPort;
        nbClients = 0;
        flagFonctionnement = true;

        try {
            socketServeur = new ServerSocket( port );
        } catch ( final IOException e ) {
            e.printStackTrace();
            new RuntimeException( "Erreur lors de la création du serveur." );
        }
    }

    /**
     * Fonctionnement du serveur.
     */
    @Override
    public void run() {
        try {
            System.out.println( "Serveur démarré, écoute sur le port " + port );
            while ( flagFonctionnement ) {
                socket = socketServeur.accept();
                System.out.println( "Nouveau client (" + nbClients + ")" );
                nbClients++;

                BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                if ( in.ready() ) {
                    String s = in.readLine();
                    // le code suivant sera ex�cut� une fois que le message est
                    // re�u
                    reply();
                }

                socket.close();
            }
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
        System.out.println( "Serveur arrêté" );
    }

    public void reply() throws IOException {
        PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
        out.println( MESSAGE );
        out.flush();
    }
}
