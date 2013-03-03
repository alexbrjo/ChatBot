package co.alexjo.chatbot;

import java.io.PrintWriter;

public abstract class BaseConnection {
    public abstract ConnectionType getConnectionType();
    public abstract void message(String line, PrintWriter out);
    public abstract void connect();
}
