package fr.imie.formations.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Emission implements Runnable {
    private PrintWriter    out;
    // private Scanner scanner;
    private BufferedReader in;

    public Emission( final PrintWriter pOut, final BufferedReader pIn ) {
        out = pOut;
        in = pIn;
        // scanner = new Scanner( System.in );
    }

    @Override
    public void run() {
        boolean flag = true;
        String msgClient;
        while ( flag ) {
            try {
                msgClient = in.readLine();
                System.out.println( msgClient );
                out.println( "$" );
                out.flush();
            } catch ( IOException e ) {
                e.printStackTrace();
                System.out.println( "Déconnexion du client" );
                flag = false;
            }
        }
    }
}
