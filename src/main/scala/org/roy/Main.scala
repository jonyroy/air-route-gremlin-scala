package org.roy

import gremlin.scala._
import org.janusgraph.core.JanusGraph
import org.roy.air.route.Query._
import org.roy.janusgraph.JanusConnManager._
import org.roy.utils.Utils._
import org.apache.tinkerpop.gremlin.structure.io
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.roy.air.route.{GremlinTraversal, Query}
import org.roy.janusgraph.SchemaGenerator
import org.roy.tinkergraph.TinkerGraphManager
import org.roy.utils.IsNotNull

import scala.collection.JavaConverters._

object Main {


  def main(arg: Array[String]): Unit = {


    //SchemaGenerator.schemaManager

    val graph: JanusGraph = getJanusGraphInstance()

//
    val scalaGraph: ScalaGraph = getScalaGraphInstance(graph)

    //TinkerGraphManager.loadAirRouteToMemory(scalaGraph)

    //commitScalaGraphTx(scalaGraph)

    println("Commit Ok")

    val r = GremlinTraversal.repeatStep(scalaGraph)

    println("Path" + r)

    //val pk = graph.getPropertyKey("id")

    //if(IsNotNull(pk)) println(pk)

    //println(Query.getNameOfPerson(scalaGraph, 100))

    //println(Query.getNumberOfVertices(scalaGraph))

    //Query.vertexCreationManager(scalaGraph)

   // val scalaGraph: ScalaGraph = TinkerGraphManager.initTinkerGraph

//    TinkerGraphManager.loadAirRouteToMemory(scalaGraph)
//
//    val result = Query.getCountryGroupBy(scalaGraph)
//
//    result.foreach(elem => elem.asScala.foreach(elem0 => {
//      val v: Vertex = elem0._1
//
//    }))

    //println(Query.getCountryGroupBy(scalaGraph))


    //val r = getCountryNameByCode(scalaGraph, "AUS")
    //val r = firstQuery(scalaGraph)

    //r.foreach(i => println(getDataType(i)))
    //println(r)
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
    killJanusInstance(graph)
  }
}
