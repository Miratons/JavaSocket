package fr.imie.formations.communications;

import java.io.PrintWriter;

/**
 * Service d'émission de message à un client, pour un serveur.
 * 
 * @author takiguchi
 *
 */
public class EmissionClient extends Emission {
    /**
     * Constructeur
     * 
     * @param pOut
     */
    public EmissionClient( PrintWriter pOut ) {
        super( pOut );
        setMessage( "Commande reçue" );
    }

    @Override
    public void run() {
        getOut().println( getMessage() );
        getOut().flush();
    }
}
