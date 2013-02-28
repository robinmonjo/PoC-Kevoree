package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class NotifierComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=NotifierComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=NotifierComponentFactory.remove(instanceName)
def createInstanceActivator = NotifierComponentFactory.createInstanceActivator
}

object NotifierComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new NotifierComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createNotifierComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.NotifierComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.NotifierComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.NotifierComponent].updateComponent()}
}}
def createNotifierComponent() : org.kevoree.library.javase.sensapp.NotifierComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.NotifierComponent();
newcomponent.getHostedPorts().put("providedNotifier",createNotifierComponentPORTprovidedNotifier(newcomponent))
newcomponent.getNeededPorts().put("requiredNotifier",createNotifierComponentPORTrequiredNotifier(newcomponent))
newcomponent}
def createNotifierComponentPORTprovidedNotifier(component : NotifierComponent) : NotifierComponentPORTprovidedNotifier ={ new NotifierComponentPORTprovidedNotifier(component)}
def createNotifierComponentPORTrequiredNotifier(component : NotifierComponent) : NotifierComponentPORTrequiredNotifier ={ return new NotifierComponentPORTrequiredNotifier(component);}
}
