package fr.imie.formations.client;

import java.io.BufferedReader;
import java.io.IOException;

public class Reception implements Runnable {
    private BufferedReader in;
    private boolean        flag;
    private Thread         emission;

    public void setFlag( boolean flag ) {
        this.flag = flag;
    }

    /**
     * Constructeur
     * 
     * @param pIn
     */
    public Reception( final BufferedReader pIn, final Thread pEmission ) {
        flag = true;
        in = pIn;
        emission = pEmission;
    }

    @Override
    public void run() {
        String msgServeur;
        while ( flag ) {
            try {
                msgServeur = in.readLine();
                System.out.println( "[Serveur] : " + msgServeur );

                if ( !emission.isAlive() ) {
                    flag = false;
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }

        try {
            in.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
