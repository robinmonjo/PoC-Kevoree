package org.kevoree.library.javase.sensapp;

import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.json.simple.*;
import org.kevoree.framework.MessagePort;

@Provides({
        @ProvidedPort(name = "providedDispatcher", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "requiredDispatcher", type = PortType.MESSAGE, optional = true)
})
@ComponentType
public class DispatcherComponent extends AbstractComponentType implements DispatcherListener {

    @Start
    public void startComponent() {
        System.out.println("Dispatcher:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Dispatcher:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Dispatcher:: Update");
    }

    @Port(name = "providedDispatcher")
    public void consumeData(Object o) {
        System.out.println("Dispatcher:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Dispatcher received data : " + json.toString());
                int value = ((Long) json.get("value")).intValue();
                if(value < 80) {
                    System.out.println("Data redirected.");
                    DispatcherThread producer = new DispatcherThread(json);
                    producer.addProductionListener(this);
                    producer.start();
                } else {
                    System.out.println("Data ignored (not redirected).");
                }
            } catch (Exception e) {
                System.out.println("Dispatcher received exception : " + e.getMessage());
            }
        }
    }

    public void redirectData(String data) {
        MessagePort prodPort = getPortByName("requiredDispatcher",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(data);
        }
    }
}
