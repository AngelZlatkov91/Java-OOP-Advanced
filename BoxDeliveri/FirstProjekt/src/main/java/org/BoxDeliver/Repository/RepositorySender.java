package org.BoxDeliver.Repository;

import org.BoxDeliver.Sender.Sender;

public interface RepositorySender {
    void add(Sender sender);
    Sender findFirst(String name);
    boolean isHere(String name);
    int size();


}
