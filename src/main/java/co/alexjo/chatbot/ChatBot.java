package co.alexjo.chatbot;

import co.alexjo.chatbot.irc.*;
import co.alexjo.chatbot.tasks.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ChatBot {
    
    public Collection<BaseTask> plugins = new ArrayList();
    public String nick;
    public String owner;
    
    private String server;
    private String channel;
    
    private BaseConnection connection;
    
    String na = "NA";

    public ChatBot(String server, String channel, String nick, String owner) {     
        this.server = server;
        this.channel = channel;
        this.nick = nick;
        this.owner = owner;
        loadTasks();
        connect();
    }

    public static void main(String[] args) {
        Settings.load("config.properties");
        ChatBot cb = new ChatBot(Settings.ircServer, Settings.ircChannel, Settings.name, Settings.owner);
    }

    public void connect() {        
        IRCChatRoom room = new IRCChatRoom(server, channel);
        connection = new IRCConnection(room, this);
        connection.connect();
    }

    public void loadTasks() {
        plugins.add(new YouTube());
        plugins.add(new Define());
        plugins.add(new TriggerWord());
        plugins.add(new Help());
    }

    public String updatePlugins(String line, PrintWriter out) {
        String message = null;
        boolean sent = false;
        for (BaseTask plugin : plugins) {
                String response = plugin.parseMessage(line, this.na);
                if(!response.equals(this.na) && !sent){
                    message = response;
                    sent = true;
                }                
        }
        return message;
    }
}

