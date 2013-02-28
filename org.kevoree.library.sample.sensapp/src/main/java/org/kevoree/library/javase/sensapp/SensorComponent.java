package org.kevoree.library.javase.sensapp;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Requires({
        @RequiredPort(name = "sensor", type = PortType.MESSAGE, optional = true)
})
@DictionaryType({
        @DictionaryAttribute(name = "productionDelay", defaultValue = "2000", optional = true),
        @DictionaryAttribute(name = "nbProductions", defaultValue = "10", optional = true)
})
@ComponentType
public class SensorComponent extends AbstractComponentType implements SensorListener {

    private SensorThread producer;

    @Start
    public void startComponent() {
        if (producer == null || producer.isStopped()) {
            producer = new SensorThread(Long.valueOf((String) getDictionary().get("productionDelay")),
                    Integer.valueOf((String) getDictionary().get("nbProductions")));
            producer.addProductionListener(this);
            producer.start();
        }
    }

    @Stop
    public void stopComponent() {
        if (producer != null) {
            producer.halt();
        }
    }

    @Update
    public void updateComponent() {
        stopComponent();
        startComponent();
    }

    public void dataProduced(String data) {
        MessagePort prodPort = getPortByName("sensor",MessagePort.class);
        if(prodPort != null) {
            prodPort.process(data);
        }
    }
}
