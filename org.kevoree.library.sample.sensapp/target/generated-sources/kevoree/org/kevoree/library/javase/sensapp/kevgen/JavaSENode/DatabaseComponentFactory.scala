package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class DatabaseComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=DatabaseComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=DatabaseComponentFactory.remove(instanceName)
def createInstanceActivator = DatabaseComponentFactory.createInstanceActivator
}

object DatabaseComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new DatabaseComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createDatabaseComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DatabaseComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DatabaseComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.DatabaseComponent].updateComponent()}
}}
def createDatabaseComponent() : org.kevoree.library.javase.sensapp.DatabaseComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.DatabaseComponent();
newcomponent.getHostedPorts().put("database",createDatabaseComponentPORTdatabase(newcomponent))
newcomponent}
def createDatabaseComponentPORTdatabase(component : DatabaseComponent) : DatabaseComponentPORTdatabase ={ new DatabaseComponentPORTdatabase(component)}
}
