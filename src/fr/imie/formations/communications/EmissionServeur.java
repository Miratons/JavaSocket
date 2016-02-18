package fr.imie.formations.communications;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Service d'émission de message à un serveur, pour un client.
 * 
 * @author takiguchi
 *
 */
public class EmissionServeur extends Emission {
    /** Le scanner pour saisir des messages. */
    private Scanner          scanner;
    /** Le service de réception des messages du serveur. */
    private ReceptionServeur reception;

    /**
     * Constructeur
     * 
     * @param pOut
     *            Flux d'envoi de message vers le serveur.
     */
    public EmissionServeur( final PrintWriter pOut, final ReceptionServeur pReception ) {
        super( pOut );
        scanner = new Scanner( System.in );
        reception = pReception;
    }

    @Override
    public void run() {
        // String temp;
        while ( isRunning() ) {
            System.out.print( "Vous : " );
            // temp = scanner.next();
            setMessage( scanner.next() );
            if ( "exit".equals( getMessage().toLowerCase() ) ) {
                setMessage( "over" );
                stop();
            }
            getOut().println( getMessage() );
            getOut().flush();
        }

        System.out.println( "Arrêt de la communication avec le serveur..." );
    }
}
