package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

@Provides({
        @ProvidedPort(name = "database", type = PortType.MESSAGE)
})
@ComponentType
public class DatabaseComponent extends AbstractComponentType {

    @Start
    public void startComponent() {
        System.out.println("Database:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Database:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Database:: Update");
    }

    @Port(name = "database")
    public void consumeData(Object o) {
        System.out.println("Database:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Database received data : " + json.toString());
            } catch (Exception e) {
                System.out.println("Database received exception : " + e.getMessage());
            }
        }
    }
}
