package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class DispatcherThread extends Thread {

    private boolean stopped = false;
    private ArrayList<DispatcherListener> listeners = new ArrayList<DispatcherListener>();
    private JSONObject json;

    public DispatcherThread() {}

    public DispatcherThread(JSONObject json) {
        this.json = json;
    }

    public void addProductionListener(DispatcherListener lst) {
        listeners.add(lst);
    }

    public void halt() {
        stopped = true;
    }

    public boolean isStopped(){return stopped;}

    public void run() {
        redirectDataToDb(json.toJSONString());
    }

    private void redirectDataToDb(final String toSend) {
        for(final DispatcherListener listener : listeners) {
            new Thread(new Runnable(){
                public void run()
                {listener.redirectData(toSend);}
            }).start();
        }
        this.halt();
    }
}
