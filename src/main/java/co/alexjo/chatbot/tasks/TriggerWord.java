package co.alexjo.chatbot.tasks;

import co.alexjo.chatbot.BaseTask;
import co.alexjo.chatbot.TaskType;
import java.util.HashMap;

// To be renamed
public class TriggerWord extends BaseTask {

    protected HashMap<String, String> trigger = new HashMap<String, String>();

    @Override
    public String getName() {
        return "triggerword";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TRIGGER;
    }

    @Override
    public String parseMessage(String line, String noText) {
        String message = noText;
        line = line.toLowerCase();

        if (line.startsWith("!")) {
            String[] cmd = line.split(" ");

            if (cmd.length >= 3) {
                trigger.put(cmd[1], line.split(cmd[1])[1]);
                System.out.println(cmd[1]);
            }
        } else {
            String[] search = line.split(" ");
            for (String s : search) {
                if (trigger.containsKey(s)) {
                    message = s +" : "+ trigger.get(s);
                }
            }
        }

        return message;
    }
}
