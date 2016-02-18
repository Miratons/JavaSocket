package fr.imie.formations.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DiscutionAvecServeur implements Runnable {
    private Socket         socket;
    private PrintWriter    out;
    private BufferedReader in;
    private Client         client;
    private Thread         emission;
    private Thread         reception;

    /**
     * Constructeur
     * 
     * @param pSocket
     */
    public DiscutionAvecServeur( final Socket pSocket, final Client pClient ) {
        socket = pSocket;
        client = pClient;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter( socket.getOutputStream() );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            emission = new Thread( new Emission( client, out ) );
            emission.start();

            reception = new Thread( new Reception( in, emission ) );
            reception.start();
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
    }
}
