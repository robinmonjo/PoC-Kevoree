package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class WidgetComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=WidgetComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=WidgetComponentFactory.remove(instanceName)
def createInstanceActivator = WidgetComponentFactory.createInstanceActivator
}

object WidgetComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new WidgetComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createWidgetComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.WidgetComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.WidgetComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.WidgetComponent].updateComponent()}
}}
def createWidgetComponent() : org.kevoree.library.javase.sensapp.WidgetComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.WidgetComponent();
newcomponent.getHostedPorts().put("widget",createWidgetComponentPORTwidget(newcomponent))
newcomponent}
def createWidgetComponentPORTwidget(component : WidgetComponent) : WidgetComponentPORTwidget ={ new WidgetComponentPORTwidget(component)}
}
