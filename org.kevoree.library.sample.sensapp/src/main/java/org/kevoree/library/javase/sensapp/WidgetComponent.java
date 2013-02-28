package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

@Provides({
        @ProvidedPort(name = "widget", type = PortType.MESSAGE)
})
@ComponentType
public class WidgetComponent extends AbstractComponentType {
    @Start
    public void startComponent() {
        System.out.println("Widget:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Widget:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Widget:: Update");
    }

    @Port(name = "widget")
    public void consumeData(Object o) {
        System.out.println("Widget:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Widget received data : " + json.toString());
            } catch (Exception e) {
                System.out.println("Widget received exception : " + e.getMessage());
            }
        }
    }
}
