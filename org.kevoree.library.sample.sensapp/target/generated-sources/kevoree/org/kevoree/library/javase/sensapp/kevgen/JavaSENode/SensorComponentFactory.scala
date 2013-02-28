package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class SensorComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=SensorComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=SensorComponentFactory.remove(instanceName)
def createInstanceActivator = SensorComponentFactory.createInstanceActivator
}

object SensorComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new SensorComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createSensorComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.SensorComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.SensorComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.SensorComponent].updateComponent()}
}}
def createSensorComponent() : org.kevoree.library.javase.sensapp.SensorComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.SensorComponent();
newcomponent.getNeededPorts().put("sensor",createSensorComponentPORTsensor(newcomponent))
newcomponent}
def createSensorComponentPORTsensor(component : SensorComponent) : SensorComponentPORTsensor ={ return new SensorComponentPORTsensor(component);}
}
