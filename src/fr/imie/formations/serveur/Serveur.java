package fr.imie.formations.serveur;

import java.io.IOException;
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

    /*
     * Attributs
     */
    private int          port;
    private ServerSocket socketServeur;
    private Socket       socket;
    private int          nbClients;
    private boolean      flagFonctionnement;

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

                socket.close();
            }
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
        System.out.println( "Serveur arrêté" );
    }
}
