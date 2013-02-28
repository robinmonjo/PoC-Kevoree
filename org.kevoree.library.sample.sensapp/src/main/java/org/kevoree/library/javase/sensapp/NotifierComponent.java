package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Provides({
        @ProvidedPort(name = "providedNotifier", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "requiredNotifier", type = PortType.MESSAGE, optional = true)
})
@ComponentType
public class NotifierComponent extends AbstractComponentType implements NotifierListener{

    @Start
    public void startComponent() {
        System.out.println("Notifier:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Notifier:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Notifier:: Update");
    }

    @Port(name = "providedNotifier")
    public void consumeData(Object o) {
        System.out.println("Notifier:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Notifier received data : " + json.toString());
                System.out.println("Data redirected.");
                NotifierThread producer = new NotifierThread(json);
                producer.addProductionListener(this);
                producer.start();
            } catch (Exception e) {
                System.out.println("Notifier received exception : " + e.getMessage());
            }
        }
    }

    public void redirectData(String data) {
        MessagePort prodPort = getPortByName("requiredNotifier",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(data);
        }
    }
}
