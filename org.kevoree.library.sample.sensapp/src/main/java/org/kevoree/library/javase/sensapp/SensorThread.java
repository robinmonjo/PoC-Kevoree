package org.kevoree.library.javase.sensapp;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.*;

public class SensorThread extends Thread {


    private boolean stopped = false;
    private int nbSensors = 10;
    private long delay = 2000;
    private ArrayList<SensorListener> listeners = new ArrayList<SensorListener>();

    public SensorThread() {}

    public SensorThread(long delay, int nbSensors) {
        this.delay = delay;
        this.nbSensors = nbSensors;
    }

    public void addProductionListener(SensorListener lst) {
        listeners.add(lst);
    }

    public void halt() {
        stopped = true;
    }

    public boolean isStopped(){return stopped;}

    public void run() {
        int iteration = 0;
        Random r = new Random();
        while(!stopped && iteration < this.nbSensors) {
            JSONObject json = new JSONObject();
            json.put("value",r.nextInt(100));
            produceData(json.toJSONString());
            iteration++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produceData(final String toSend) {
        for(final SensorListener listener : listeners) {
            new Thread(new Runnable(){
                public void run()
                    {listener.dataProduced(toSend);}
            }).start();
        }
    }

}
