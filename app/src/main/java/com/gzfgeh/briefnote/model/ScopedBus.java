package com.gzfgeh.briefnote.model;

import java.util.HashSet;
import java.util.Set;

import de.greenrobot.event.EventBus;

/**
 * Created by guzhenf on 7/8/2015.
 */
public class ScopedBus {

    private final EventBus bus = EventBus.getDefault();
    private final Set<Object> objects = new HashSet<Object>();
    private boolean active;

    public void register(Object obj) {
        objects.add(obj);
        if (active) {
            bus.registerSticky(obj);
        }
    }

    public void unregister(Object obj) {
        objects.remove(obj);
        if (active) {
            bus.unregister(obj);
        }
    }

    public void post(Object event) {
        bus.postSticky(event);
    }

    public void paused() {
        active = false;
        for (Object obj : objects) {
            bus.unregister(obj);
        }
    }

    public void resumed() {
        active = true;
        for (Object obj : objects) {
            bus.registerSticky(obj);
        }
    }

}
