package project.simplechat;

import java.security.Principal;

public class ChatMessage  {

    private String name;
    private String username;
    private String text;
    private String time;

    public ChatMessage(String name, String username, String text, String time) {
        this.name = name;
        this.text = text;
        this.username = username;
       this.time = StringUtils.getCurrentTimeStamp();
    }

     public ChatMessage(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getText(){
        return text;
    }

    public String getTime(){
        return time;
    }

    //@Override
     public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
