package co.alexjo.chatbot.irc;

import co.alexjo.chatbot.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IRCConnection extends BaseConnection{
    
    private ChatBot bot;
    private IRCChatRoom room;
    
    public IRCConnection(IRCChatRoom room, ChatBot bot){
        this.room = room;
        this.bot = bot;
    }
    
    public void socketLoop() {    
        BufferedReader in = null;
        try {
            String line;
            Socket s = new Socket(room.server, 6667);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            out.print("USER " + bot.nick + " 0 * :" + bot.owner + "\r\n"
                    + "NICK " + bot.nick + "\r\n");
            out.flush();

            while (s.isConnected()) {
                line = in.readLine();
                System.out.println(line);
                ping(line, out);
                if (line.charAt(0) != ':')continue;
                if (line.split(" ")[1].equals("001")) {                    
                    out.print("MODE " + bot.nick + " +B\r\n" + "JOIN " + room.room + "\r\n");
                    out.flush();                   
                }else if(line.split(" ")[1].equals("PRIVMSG")){
                   if(line.split(":")[2].equals(bot.nick+" kys")){
                       quit(out);
                       s.close();
                       break;
                   }
                   
                   String print = bot.updatePlugins(line.split(":")[2], out);
                   if(print != null){
                       message(print, out);
                   }
                }
            }
            
            s.close();


        } catch (IOException ex) {
            Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ConnectionType getConnectionType() {
        return ConnectionType.IRC;
    }
    
    public void connect(){
        socketLoop();
    }
    
    /*
     * Respond to IRC Server
     */
    
    protected void ping(String line, PrintWriter out) {
        if (line.startsWith("PING ")) {
            out.print("PONG " + line.substring(5) + "\r\n");
            out.flush();
        }
    }
    
    /*
     * Send message in specific channel
     */
    
    public void message(String message, PrintWriter out){
        out.print("PRIVMSG " + room.room + " :" + message + "\r\n");
        out.flush();
    }
    
     public void quit(PrintWriter out){
        out.print("QUIT : porque no me importa de nadie \r\n");
        out.flush();
    }
}
