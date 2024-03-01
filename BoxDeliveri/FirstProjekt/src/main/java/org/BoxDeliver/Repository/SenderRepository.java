package org.BoxDeliver.Repository;
import org.BoxDeliver.Sender.Sender;
import org.BoxDeliver.common.ExceptionMessages;
import java.util.LinkedHashMap;
import java.util.Map;

public class SenderRepository implements RepositorySender {
    private Map<String,Sender> senderMap;
    public SenderRepository() {
        this.senderMap = new LinkedHashMap<>();
    }
    @Override
    public void add(Sender sender) {
        String name = sender.getName();
        if (senderMap.containsKey(name)){
                throw new IllegalArgumentException(ExceptionMessages.SENDER_IS_ADDED);
        }
        senderMap.put(name,sender);
    }

    @Override
    public Sender findFirst(String name) {
        if (!this.senderMap.containsKey(name)) {
            throw new IllegalArgumentException(ExceptionMessages.SENDER_WITH_NAME_NOT_EXIST);
        }
        return this.senderMap.get(name);
    }

    @Override
    public boolean isHere(String name) {
        return this.senderMap.containsKey(name);
    }

    @Override
    public int size() {
        return this.senderMap.size();
    }


}
