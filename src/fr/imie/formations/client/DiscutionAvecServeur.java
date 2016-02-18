package fr.imie.formations.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.imie.formations.communications.EmissionServeur;
import fr.imie.formations.communications.ReceptionServeur;

/**
 * Service de discution du client avec un serveur.
 * 
 * @author takiguchi
 *
 */
public class DiscutionAvecServeur implements Runnable {
    /** Socket entre le client et son serveur. */
    private Socket           socket;
    /** Flux de réception des messages du serveur. */
    private PrintWriter      out;
    /** Flux d'envoi de messages vers le serveur. */
    private BufferedReader   in;
    /** Service d'émission de messages vers le serveur. */
    private ReceptionServeur reception;
    /** Thread de réception des messages du serveur. */
    private Thread           threadReception;
    /** Thread d'émission de messages vers le serveur. */
    private Thread           threadEmission;

    /**
     * Constructeur
     * 
     * @param pSocket
     *            Socket entre le client et le serveur.
     */
    public DiscutionAvecServeur( final Socket pSocket ) {
        socket = pSocket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter( socket.getOutputStream() );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            reception = new ReceptionServeur( in );
            threadReception = new Thread( reception );
            threadReception.start();

            threadEmission = new Thread( new EmissionServeur( out, reception ) );
            threadEmission.start();
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
    }
}
