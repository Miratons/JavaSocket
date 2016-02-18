package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.imie.formations.exceptions.InitServeurException;

public class Serveur implements Runnable {
    /**
     * Point d'entrée.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        Serveur serveur = null;
        try {
            serveur = new Serveur( 2016 );
        } catch ( InitServeurException e ) {
            System.err.println( e.getMessage() );
            System.exit( 1 );
        }
        Thread th = new Thread( serveur );
        th.start();
    }

    /*
     * Attributs
     */
    private int            port;
    private ServerSocket   socketServeur;
    private Socket         socket;
    private int            nbClients;
    private boolean        flagFonctionnement;
    private BufferedReader in;
    private PrintWriter    out;

    /**
     * Constructeur
     * 
     * @param pPort
     *            Le port d'écoute du serveur.
     * @throws InitServeurException
     */
    public Serveur( final int pPort ) throws InitServeurException {
        port = pPort;
        nbClients = 0;
        flagFonctionnement = true;

        try {
            socketServeur = new ServerSocket( port );
            System.out.println( "Serveur démarré sur le port " + port );
        } catch ( final BindException e ) {
            System.out.println( "Le port " + port
                    + " est déjà utilisé." );
            System.out.println( "Peut être qu'autre instance de serveur est fonctionnement ?" );
            throw new InitServeurException( "Erreur lors de la création du serveur." );
        } catch ( final IOException e ) {
            throw new InitServeurException( "(IOException) Erreur lors de la création du serveur." );
        }

    }

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
