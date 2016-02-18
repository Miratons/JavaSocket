package fr.imie.formations.communications;

import java.io.PrintWriter;

public abstract class Emission implements Runnable {
    private PrintWriter out;
    private boolean     running;
    private String      message;

    public Emission( final PrintWriter pOut ) {
        out = pOut;
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public PrintWriter getOut() {
        return out;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( final String pMessage ) {
        message = pMessage;
    }
}
