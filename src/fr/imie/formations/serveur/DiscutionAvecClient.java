package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DiscutionAvecClient implements Runnable {
    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;
    private String         client;
    private Thread         emission;
    private Thread         reception;

    public DiscutionAvecClient( final Socket pSocket, final String pClient ) {
        socket = pSocket;
        client = pClient;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            out = new PrintWriter( socket.getOutputStream() );

            emission = new Thread( new Emission( out, in ) );
            emission.start();

            // reception = new Thread( new Reception( in, client ) );
            // reception.start();
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
    }
}
