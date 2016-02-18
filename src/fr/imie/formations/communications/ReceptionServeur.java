package fr.imie.formations.communications;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Service de réception de message d'un serveur, pour un client.
 * 
 * @author takiguchi
 *
 */
public class ReceptionServeur extends Reception {
    /**
     * Constructeur
     * 
     * @param pIn
     *            Flux de réception de messages
     */
    public ReceptionServeur( final BufferedReader pIn ) {
        super( pIn );
    }

    @Override
    public void run() {
        // String temp;
        while ( isRunning() ) {
            try {
                // temp = getIn().readLine();
                // setMessageRecu( temp );
                setMessageRecu( getIn().readLine() );
                if ( "over".equals( getMessageRecu() ) ) {
                    stop();
                } else {
                    System.out.println( "[Serveur] : " + getMessageRecu() );
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }

        System.out.println( "Communication avec le serveur terminée." );
    }
}
