package org.kevoree.library.javase.sensapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Provides({
        @ProvidedPort(name = "providedFilter", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "requiredFilter", type = PortType.MESSAGE, optional = true)
})
@DictionaryType({
        @DictionaryAttribute(name = "idSensorToFilter", defaultValue = "1", optional = false)
})
@ComponentType
public class FilterComponent extends AbstractComponentType implements FilterListener{
    @Start
    public void startComponent() {
        System.out.println("Filter:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Filter:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Filter:: Update");
    }

    @Port(name = "providedFilter")
    public void consumeData(Object o) {
        System.out.println("Filter:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(msg);
                JSONObject json = (JSONObject) obj;
                System.out.println("Filter received data : " + json.toString());
                int idToFilter = Integer.valueOf((String) getDictionary().get("idSensorToFilter"));
                if(((Long) json.get("id")).intValue() == idToFilter){
                    System.out.println("Data redirected.");
                    FilterThread producer = new FilterThread(json);
                    producer.addProductionListener(this);
                    producer.start();
                } else {
                    System.out.println("Data not redirected.");
                }
            } catch (Exception e) {
                System.out.println("Filter received exception : " + e.getMessage());
            }
        }
    }

    public void redirectData(String data) {
        MessagePort prodPort = getPortByName("requiredFilter",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(data);
        }
    }
}
