package org.roy

import gremlin.scala._
import org.janusgraph.core.JanusGraph
import org.roy.air.route.Query._
import org.roy.janusgraph.JanusConnManager._
import org.roy.utils.Utils._
import org.apache.tinkerpop.gremlin.structure.io
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.roy.janusgraph.SchemaGenerator
import org.roy.tinkergraph.TinkerGraphManager

object Main {


  def main(arg: Array[String]): Unit = {

//    val graph: JanusGraph = getJanusGraphInstance()
//
//    if (isGraphOpen(graph)) {
//      println("Hoooree JanusGraph Instance Creation Successful.")
//    } else {
//      println("Sorry Can't create JanusGraph Instance. Try another time")
//    }
//
//    val scalaGraph: ScalaGraph = getScalaGraphInstance(graph)

    println(SchemaGenerator.PropertyKeyTypes.STRING)

    val scalaGraph: ScalaGraph = TinkerGraphManager.initTinkerGraph

    TinkerGraphManager.loadAirRouteToMemory(scalaGraph)



    val r = getCountryNameByCode(scalaGraph, "AUS")
    //val r = firstQuery(scalaGraph)

    //r.foreach(i => println(getDataType(i)))
    println(r)
    //println(s"Type of Result: ${getDataType(r)}")


    //val x = scalaGraph.V().l().size
    //val y = scalaGraph.E().l().size

    //println(s"Total Vertex: $x")
    //println(s"Total Edge: $y")
    //println(getDataType(scalaGraph))
    //println(getDataType(graph))


//    scalaGraph.tx().commit()
//    scalaGraph.close()
//    if (isGraphOpen(graph)) println("Open") else println("close")
//    killJanusInstance(graph)
  }
}
