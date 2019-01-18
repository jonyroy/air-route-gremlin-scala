package org.roy

import org.roy.janus.conn.manager.JanusConnManager._
import org.janusgraph.core.{JanusGraphFactory, JanusGraph}
import gremlin.scala._
import org.roy.utils.Utils._
import org.roy.air.route.Query._

object Main {
     def main(arg: Array[String]): Unit = {

       val graph: JanusGraph = getJanusGraphInstance()

       if(isGraphOpen(graph)) {
           println("Hoooree JanusGraph Instance Creation Successful.")
       } else {
           println("Sorry Can't create JanusGraph Instance. Try another time")
       }

       val scalaGraph: ScalaGraph = getScalaGraphInstance(graph)
        
       val r = firstQuery(scalaGraph)

       println(r)
       println(s"Type of Result: ${getDataType(r)}")


       //val x = scalaGraph.V().l().size
       //val y = scalaGraph.E().l().size

       //println(s"Total Vertex: $x")
       //println(s"Total Edge: $y")
       //println(getDataType(scalaGraph))
       //println(getDataType(graph))
       scalaGraph.close()
       if(isGraphOpen(graph)) println("Open") else println("close")
       killJanusInstance(graph)
    }
}
