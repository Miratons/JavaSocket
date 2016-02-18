package fr.imie.formations.exercices;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InetAdressGetByNameMain {
    private static final Scanner scanner;

    static {
        scanner = new Scanner( System.in );
    }

    public static void main( String[] args ) {
        System.out.println( "Veuillez saisir une adresse IP ou URL : " );
        String saisie = scanner.next();
        try {
            InetAddress adresse = InetAddress.getByName( saisie );
            System.out.println( "Adresse hôte : " + adresse.getHostAddress() );
            System.out.println( "Nom d'hôte : " + adresse.getHostName() );
            System.out.println( "Nom canonique de l'hôte : " + adresse.getCanonicalHostName() );
            System.out.println( "Adresse : " + adresse.getAddress() );
        } catch ( UnknownHostException e ) {
            e.printStackTrace();
        }
    }
}
