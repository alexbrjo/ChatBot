package co.alexjo.chatbot;

public abstract class BaseTask {
    
    protected String name = "";
    
    public abstract String getName();
    public abstract TaskType getTaskType();
    public abstract String parseMessage(String line, String noText);
}
