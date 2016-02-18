package fr.imie.formations.communications;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Service de réception de message d'un client, pour un serveur.
 * 
 * @author takiguchi
 *
 */
public class ReceptionClient extends Reception {
    /** Nom du client envoyant des messages. */
    private String         client;
    /** Service d'emission de message au client. */
    private EmissionClient emission;

    /**
     * Constructeur
     * 
     * @param pIn
     *            Flux de réception de messages.
     * @param pClient
     *            Nom du client envoyant des messages.
     */
    public ReceptionClient( final BufferedReader pIn, final EmissionClient pEmission, final String pClient ) {
        super( pIn );
        emission = pEmission;
        client = pClient;
    }

    @Override
    public void run() {
        while ( isRunning() ) {
            try {
                setMessageRecu( getIn().readLine() );
                if ( getMessageRecu() == null || "over".equals( getMessageRecu() ) ) {
                    emission.setMessage( "over" );
                    stop();
                } else {
                    System.out.println( "[" + client + "] : " + getMessageRecu() );
                }
                new Thread( emission ).start();
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
        }

        emission.stop();
        System.out.println( "Déconnexion du client [" + client + "]." );
    }
}
