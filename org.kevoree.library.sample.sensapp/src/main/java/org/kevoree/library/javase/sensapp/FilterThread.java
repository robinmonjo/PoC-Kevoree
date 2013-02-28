package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class FilterThread extends Thread {
    private boolean stopped = false;
    private ArrayList<FilterListener> listeners = new ArrayList<FilterListener>();
    private JSONObject json;

    public FilterThread() {}

    public FilterThread(JSONObject json) {
        this.json = json;
    }

    public void addProductionListener(FilterListener lst) {
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
        for(final FilterListener listener : listeners) {
            new Thread(new Runnable(){
                public void run()
                {listener.redirectData(toSend);}
            }).start();
        }
        this.halt();
    }
}
