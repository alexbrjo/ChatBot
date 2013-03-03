package co.alexjo.chatbot.tasks;

import co.alexjo.chatbot.BaseTask;
import co.alexjo.chatbot.TaskType;

public class YouTube extends BaseTask{

    @Override
    public String getName() {
       return "youtube";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TRIGGER;
    }

    @Override
    public String parseMessage(String line, String noText) {
        String message = noText;
        return message;
    }
    
}
