package org.roy

import gremlin.scala._
import org.apache.tinkerpop.gremlin.process.traversal.Path
import org.roy.air.route.GremlinTraversal
import org.roy.tinkergraph.TinkerGraphManager

object TinkerGraphMain {

  def main(args: Array[String]): Unit = {
    val scalaGraph: ScalaGraph = TinkerGraphManager.initTinkerGraph

    TinkerGraphManager.loadAirRouteToMemory(scalaGraph)
    val rsr: List[Path] = GremlinTraversal.repeatStep(scalaGraph)

    //rsr.foreach(elem => println(elem))
    //GremlinTraversal.saidpurToChittagong(scalaGraph).foreach(elem => println(elem))

    //GremlinTraversal.getDhakaToOneLevel(scalaGraph).foreach(println _)

    //println("dfdf" + GremlinTraversal.getDist(scalaGraph))
    GremlinTraversal.getPathBetweenTwoAirports(scalaGraph, "AUS", "AGR").foreach(println _)
    scalaGraph.close()
  }
}
