package org.kevoree.library.javase.sensapp.kevgen.JavaSENode
import org.kevoree.framework.port._
import org.kevoree.library.javase.sensapp._
import scala.{Unit=>void}
class DispatcherComponentPORTdispatcher(component : DispatcherComponent) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "dispatcher"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.consumeData(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
