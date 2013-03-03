package co.alexjo.chatbot.tasks;

import co.alexjo.chatbot.BaseTask;
import co.alexjo.chatbot.ChatBot;
import co.alexjo.chatbot.TaskType;

public class Help extends BaseTask{

    @Override
    public String getName() {
        return "brain";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TRIGGER;
    }

    @Override
    public String parseMessage(String line, String noText) {
        String message = noText;
        
        if(line.startsWith("name"+" help")){
            if(line.equals("name"+" help")){
                message = "cmds: ?, !, settings";
            }else{
                String query = line.split("name" +" help")[1];
            }
        }
        
        return message;
    }
    
}
