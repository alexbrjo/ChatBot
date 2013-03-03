package co.alexjo.chatbot.irc;

public class IRCChatRoom {
    public String server;
    public String room;
    public String[] members;
    
    public IRCChatRoom(String server, String room){
        this.server = server;
        this.room = room;
    }
}
