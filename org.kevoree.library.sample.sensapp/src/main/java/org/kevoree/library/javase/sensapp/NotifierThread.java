package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class NotifierThread extends Thread {
    private boolean stopped = false;
    private ArrayList<NotifierListener> listeners = new ArrayList<NotifierListener>();
    private JSONObject json;

    public NotifierThread() {}

    public NotifierThread(JSONObject json) {
        this.json = json;
    }

    public void addProductionListener(NotifierListener lst) {
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
        for(final NotifierListener listener : listeners) {
            new Thread(new Runnable(){
                public void run()
                {listener.redirectData(toSend);}
            }).start();
        }
        this.halt();
    }
}
