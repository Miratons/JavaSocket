package fr.imie.formations.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main( String[] args ) {
        Client client = new Client( "Toto" );
    }

    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;
    private String         nom;

    public Client( final String pNom ) {
        nom = pNom;
        try {
            socket = new Socket( "127.0.0.1", 2016 );
            out = new PrintWriter( socket.getOutputStream() );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Envoi du nom du client au serveur
            out.println( nom );
            out.flush();

            // Réception de l'accusé du serveur
            System.out.println( in.readLine() );

            Thread discution = new Thread( new DiscutionAvecServeur( socket, this ) );
            discution.start();
        } catch ( UnknownHostException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}
