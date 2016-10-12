package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MessageBean implements Serializable {

    private HashMap<String, ArrayList<String>> messages = null;
    
    public MessageBean() {
        this.messages = new HashMap<>();
    }

    public void info(String msg) {
        addMessage("info", msg);
    }

    public void danger(String msg) {
        addMessage("danger", msg);
    }

    public void success(String msg) {
        addMessage("success", msg);
    }
    
    public void warning(String msg) {
        addMessage("warning", msg);
    }
    
    private void addMessage(String type, String message){
        if (!messages.containsKey(type)) {
            messages.put(type, new ArrayList());
        }
        messages.get(type).add(message);
    }
    
    private List getMessages(String type){
        List msgs = new ArrayList();
        if(!messages.containsKey(type)) {
            return msgs;
        }
        msgs.addAll(messages.get(type));
        messages.get(type).clear();
        return msgs;
    }

    public HashMap<String, ArrayList<String>> getMessages() {
        HashMap msgs = new HashMap(messages);
        messages.clear();
        return msgs;
    }

    public Collection getInfos() {
        return getMessages("info");
    }

    public Collection getDangers() {
        return getMessages("danger");
    }

    public Collection getSuccesses() {
        return getMessages("success");
    }
}
