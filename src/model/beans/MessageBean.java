package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MessageBean implements Serializable {

    private HashMap<String, ArrayList<String>> messages = null;
    private HashMap<String, ArrayList<String>> recup = null;

    public MessageBean() {
        this.messages = new HashMap<>();
    }

    public void infos(String msg) {

        if (!messages.containsKey("info")) {
            messages.put("info", new ArrayList());
        }
        messages.get("info").add(msg);
    }

    public void errors(String msg) {

        if (!messages.containsKey("error")) {
            messages.put("error", new ArrayList());
        }
        messages.get("error").add(msg);
    }

    public void successes(String msg) {

        if (!messages.containsKey("success")) {
            messages.put("success", new ArrayList());
        }
        messages.get("success").add(msg);
    }

    public HashMap<String, ArrayList<String>> getMessages() {
        recup = messages;
        messages.clear();
        return recup;
    }

    public Collection getInfos() {
        return messages.get("info");
    }

    public Collection getErrors() {
        return messages.get("error");
    }

    public Collection getSuccesses() {
        return messages.get("success");
    }
}
