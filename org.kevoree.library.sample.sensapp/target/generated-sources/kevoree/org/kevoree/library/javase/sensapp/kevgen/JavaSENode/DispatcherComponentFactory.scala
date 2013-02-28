package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class DispatcherComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=DispatcherComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=DispatcherComponentFactory.remove(instanceName)
def createInstanceActivator = DispatcherComponentFactory.createInstanceActivator
}

object DispatcherComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new DispatcherComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createDispatcherComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DispatcherComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DispatcherComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DispatcherComponent].updateComponent()}
}}
def createDispatcherComponent() : org.kevoree.library.javase.sensapp.DispatcherComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.DispatcherComponent();
newcomponent.getHostedPorts().put("providedDispatcher",createDispatcherComponentPORTprovidedDispatcher(newcomponent))
newcomponent.getNeededPorts().put("requiredDispatcher",createDispatcherComponentPORTrequiredDispatcher(newcomponent))
newcomponent}
def createDispatcherComponentPORTprovidedDispatcher(component : DispatcherComponent) : DispatcherComponentPORTprovidedDispatcher ={ new DispatcherComponentPORTprovidedDispatcher(component)}
def createDispatcherComponentPORTrequiredDispatcher(component : DispatcherComponent) : DispatcherComponentPORTrequiredDispatcher ={ return new DispatcherComponentPORTrequiredDispatcher(component);}
}
