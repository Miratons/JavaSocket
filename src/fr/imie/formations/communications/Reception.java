package fr.imie.formations.communications;

import java.io.BufferedReader;

// TODO: Auto-generated Javadoc
/**
 * Service de réception d'un serveur ou d'un client.
 * 
 * @author takiguchi
 *
 */
public abstract class Reception implements Runnable {
    /** Buffer pour recevoir les messages. */
    private BufferedReader in;
    /** Variable permettant d'arrêter le thread. */
    private boolean        running;
    /** Message reçu par l'expéditeur. */
    private String         messageRecu;

    /**
     * Constructeur.
     *
     * @param pIn
     *            Flux de réception de messages.
     */
    public Reception( final BufferedReader pIn ) {
        in = pIn;
        running = true;
    }

    /**
     * Checks if the thread is running.
     *
     * @return true, if it is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Stoppe le thread de réception de messages.
     */
    public void stop() {
        running = false;
    }

    /**
     * Gets the in.
     *
     * @return the in
     */
    public BufferedReader getIn() {
        return in;
    }

    /**
     * Gets the message recu.
     *
     * @return the message recu
     */
    public String getMessageRecu() {
        return messageRecu;
    }

    /**
     * Sets the message recu.
     *
     * @param messageRecu
     *            the new message recu
     */
    public void setMessageRecu( String messageRecu ) {
        this.messageRecu = messageRecu;
    }

}
