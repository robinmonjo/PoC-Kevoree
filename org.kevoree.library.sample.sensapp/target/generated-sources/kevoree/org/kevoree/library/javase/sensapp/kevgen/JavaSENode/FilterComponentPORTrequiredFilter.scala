package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import org.kevoree.library.javase.sensapp._
class FilterComponentPORTrequiredFilter(component : FilterComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "requiredFilter"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
