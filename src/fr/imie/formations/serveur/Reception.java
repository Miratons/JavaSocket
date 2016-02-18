package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;

public class Reception implements Runnable {
    private BufferedReader in;
    private String         client;

    public Reception( final BufferedReader pIn, final String pClient ) {
        in = pIn;
        client = pClient;
    }

    @Override
    public void run() {
        String msgClient;

        while ( true ) {
            try {
                msgClient = in.readLine();
                System.out.println( "[" + client + "] : " + msgClient );
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
