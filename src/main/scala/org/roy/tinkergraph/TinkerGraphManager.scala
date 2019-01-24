package org.roy.tinkergraph

import gremlin.scala.{ScalaGraph, _}
import org.apache.commons.configuration.BaseConfiguration
import org.apache.tinkerpop.gremlin.structure.io.IoCore
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

object TinkerGraphManager {

  def loadAirRouteToMemory(graph: ScalaGraph): Boolean = {

    try {
      println(s"Graph Loading Starts......")
      graph.graph.io(IoCore.graphml()).readGraph("src/main/resources/air-routes-latest.graphml")
      println(s"Graph Loading Ends.")
      true
    } catch {
      case e: Exception => false
    }

  }

  def initTinkerGraph: ScalaGraph = {

    val conf = new BaseConfiguration()
    conf.setProperty("gremlin.tinkergraph.vertexIdManager", "LONG")
    conf.setProperty("gremlin.tinkergraph.edgeIdManager", "LONG")
    conf.setProperty("gremlin.tinkergraph.vertexPropertyIdManager", "LONG")
    val graph = TinkerGraph.open(conf)
    graph.asScala

  }

}
