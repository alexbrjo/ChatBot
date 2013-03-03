package co.alexjo.chatbot.tasks;

import co.alexjo.chatbot.BaseTask;
import co.alexjo.chatbot.TaskType;
import java.util.HashMap;

public class Define extends BaseTask{

    protected HashMap<String, String> def = new HashMap<String, String>();
    
    @Override
    public String getName() {
        return "Define";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.CMD;
    }

    @Override
    public String parseMessage(String line, String noText) {
        String message = noText;
        line = line.toLowerCase();
        
        if(line.startsWith("define") || line.startsWith("def") || line.startsWith("?")){
            String[] cmd = line.split(" ");
            
            if(cmd.length >= 3){
                def.put(cmd[1], line.split(cmd[1])[1]);
            }else if(cmd.length == 2){
                if(def.get(cmd[1]) != null){
                    message = cmd[1]+": "+def.get(cmd[1]);
                }else{
                    message = "not defined";
                }
            }
        }
        
        return message;
    }
    
}
