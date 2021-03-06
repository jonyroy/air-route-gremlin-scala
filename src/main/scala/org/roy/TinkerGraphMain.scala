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
    //GremlinTraversal.getPathBetweenTwoAirports(scalaGraph, "AUS", "AGR").foreach(println _)

    //GremlinTraversal.getMaxTwoStepsAirports(scalaGraph, "SPD").foreach(println _)

    //GremlinTraversal.countByGroup(scalaGraph, "country").foreach(println _)

    import ImplicitPrint._

    //GremlinTraversal.getOneStopDstFromSrc(scalaGraph, "AUS").toImpPrint
    GremlinTraversal.getNthStopDstFromSrc(scalaGraph, "SPD", 5).printSize

    scalaGraph.close()
  }
}

object ImplicitPrint {

  implicit class PrintImplicit[A](ls: Traversable[A])  {
    def toImpPrint: Unit = ls.foreach(println _)
  }

  implicit class PrintImplicitSize[A](ls: Traversable[A]) {
    def printSize: Unit = println(s"Collection Size: ${ls.size}")
  }
}
