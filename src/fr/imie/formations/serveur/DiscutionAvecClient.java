package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.imie.formations.communications.EmissionClient;
import fr.imie.formations.communications.ReceptionClient;

/**
 * Service de discution d'un serveur avec son client.
 * 
 * @author takiguchi
 *
 */
public class DiscutionAvecClient implements Runnable {
    /** Socket entre le serveur et son client. */
    private Socket         socket;
    /** Flux de réception des messages du client. */
    private BufferedReader in;
    /** Flux d'envoi de messages vers le client. */
    private PrintWriter    out;
    /** Nom du client avec qui est établi la discution. */
    private String         client;
    /** Service d'émission de messages vers le client. */
    private EmissionClient emission;
    /** Thread d'émission de messages vers le client. */
    private Thread         threadEmission;
    /** Thread de réception des messages du client. */
    private Thread         threadReception;

    /**
     * Constructeur
     * 
     * @param pSocket
     *            Socket entre le serveur et son client.
     * @param pClient
     *            Le client avec qui le serveur est en relation.
     */
    public DiscutionAvecClient( final Socket pSocket, final String pClient ) {
        socket = pSocket;
        client = pClient;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            out = new PrintWriter( socket.getOutputStream() );

            emission = new EmissionClient( out );
            // threadEmission = new Thread( emission );
            // threadEmission.start();

            threadReception = new Thread( new ReceptionClient( in, emission, client ) );
            threadReception.start();
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
    }
}
