package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
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
        Serveur serveur = new Serveur( 2016 );
        Thread th = new Thread( serveur );
        th.start();
        // serveur.testCon();
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
    private BufferedReader      in;
    private PrintWriter         out;

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
            System.out.println( "Serveur démarré sur le port " + port );
        } catch ( final IOException e ) {
            e.printStackTrace();
            new RuntimeException( "Erreur lors de la création du serveur." );
        }
    }

    // public void testCon() {
    // try {
    // socketServeur = new ServerSocket( 2016 );
    // System.out.println( "Serveur démarré, écoutant sur le port 2016" );
    // socket = socketServeur.accept();
    // System.out.println( "Un client vient de se connecter !" );
    // out = new PrintWriter( socket.getOutputStream() );
    // out.println( "Vous venez de vous connecter ! :)" );
    // out.flush();
    // socket.close();
    // socketServeur.close();
    // } catch ( IOException e ) {
    // e.printStackTrace();
    // }
    // }

    /**
     * Fonctionnement du serveur.
     */
    @Override
    public void run() {
        System.out.println( "Réception des nouveaux clients..." );
        Thread thread = new Thread( new ReceptionClients( socketServeur ) );
        thread.start();
    }
}
