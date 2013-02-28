package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.sensapp._
class FilterComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=FilterComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=FilterComponentFactory.remove(instanceName)
def createInstanceActivator = FilterComponentFactory.createInstanceActivator
}

object FilterComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new FilterComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createFilterComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.FilterComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.FilterComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.sensapp.FilterComponent].updateComponent()}
}}
def createFilterComponent() : org.kevoree.library.javase.sensapp.FilterComponent ={
val newcomponent = new org.kevoree.library.javase.sensapp.FilterComponent();
newcomponent.getHostedPorts().put("providedFilter",createFilterComponentPORTprovidedFilter(newcomponent))
newcomponent.getNeededPorts().put("requiredFilter",createFilterComponentPORTrequiredFilter(newcomponent))
newcomponent}
def createFilterComponentPORTprovidedFilter(component : FilterComponent) : FilterComponentPORTprovidedFilter ={ new FilterComponentPORTprovidedFilter(component)}
def createFilterComponentPORTrequiredFilter(component : FilterComponent) : FilterComponentPORTrequiredFilter ={ return new FilterComponentPORTrequiredFilter(component);}
}
