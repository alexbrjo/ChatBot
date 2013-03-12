package co.alexjo.chatbot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Settings {

    public static HashMap<String, String> h = new HashMap<String, String>();
    public static String name = "";
    public static String owner = "";
    public static String ircChannel = "";
    public static String ircServer = "";
    public static String jabberChannel = "";
    public static String jabberServer = "";
    public static String skypeUserName = "";
    public static String skypePass = "";

    public static void load(String path) {

        Properties prop = new Properties();
 
    	try {
    		prop.load(new FileInputStream("config.properties"));
                name = prop.getProperty("Name");
                owner = prop.getProperty("Owner");
                ircServer = prop.getProperty("IRCServer");
                ircChannel = prop.getProperty("IRCChannel");
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    }

    public static void setVars() {    
        name = h.get("name");
        owner = h.get("owner");
        
        ircChannel = h.get("ircchannel");
        ircServer = h.get("ircserver");
        
        jabberChannel = h.get("jabberchannel");
        jabberServer = h.get("jabberserver");
        
        skypeUserName = h.get("skypeusername");
        skypePass = h.get("skypepass");
    }
}
