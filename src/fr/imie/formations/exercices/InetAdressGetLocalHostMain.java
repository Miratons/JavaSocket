package fr.imie.formations.exercices;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAdressGetLocalHostMain {
    public static void main( String[] args ) throws UnknownHostException {
        InetAddress localIP = InetAddress.getLocalHost();
        System.out.println( localIP.getHostAddress() );
        System.out.println( localIP.getHostName() );
        System.out.println( localIP.getCanonicalHostName() );
        System.out.println( localIP.getAddress() );
        /*
         * Ce programme affiche différentes informations à propos de
         * l'ordinateur : Adresse ip hôte, Nom d'hôte, Nom canonique de l'hôte
         * et l'adresse de l'hôte.
         */
    }
}
