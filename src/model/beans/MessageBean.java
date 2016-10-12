package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MessageBean implements Serializable {

    private HashMap<String, ArrayList<String>> messages = null;

    public MessageBean() {

    }

    public void info(String msg) {

        if (!messages.containsKey("info")) {
            messages.put("info", new ArrayList());
        }
        messages.get("info").add(msg);
    }

    public void error(String msg) {

        if (!messages.containsKey("error")) {
            messages.put("error", new ArrayList());
        }
        messages.get("error").add(msg);
    }

    public void success(String msg) {

        if (!messages.containsKey("success")) {
            messages.put("success", new ArrayList());
        }
        messages.get("success").add(msg);
    }

    public HashMap<String, ArrayList<String>> getMessages() {
        return messages;
    }
    
    
    
}
