package fr.imie.formations.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class Emission implements Runnable {
    private Client      client;
    private PrintWriter out;
    private Scanner     scanner;
    private String      saisie;

    /**
     * Constructeur
     * 
     * @param pClient
     * @param pOut
     */
    public Emission( final Client pClient, final PrintWriter pOut ) {
        client = pClient;
        out = pOut;
        scanner = new Scanner( System.in );
    }

    @Override
    public void run() {
        boolean flag = true;

        while ( flag ) {
            System.out.print( "Vous : " );
            saisie = scanner.next();

            // if ( "exit".equals( saisie ) ) {
            // flag = false;
            // reception.setFlag( false );
            // out.println( "Déconnexion de " + client.getNom() );
            // } else {
            // out.println( scanner.next() );
            // }
            out.println( saisie );
            out.flush();
        }

    }

}
