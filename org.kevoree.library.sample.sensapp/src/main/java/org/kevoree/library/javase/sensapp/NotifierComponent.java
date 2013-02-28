package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

@Provides({
        @ProvidedPort(name = "notifier", type = PortType.MESSAGE)
})
@ComponentType
public class NotifierComponent extends AbstractComponentType {

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

    @Port(name = "notifier")
    public void consumeData(Object o) {
        System.out.println("Notifier:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Notifier received data : " + json.toString());
            } catch (Exception e) {
                System.out.println("Notifier received exception : " + e.getMessage());
            }
        }
    }
}
