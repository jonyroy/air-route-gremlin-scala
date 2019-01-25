package org.roy.air.route

import gremlin.scala._
import org.apache.tinkerpop.gremlin.process.traversal.Path
import org.apache.tinkerpop.gremlin.structure.T

object GremlinTraversal {



  def repeatStep(scalaGraph: ScalaGraph): List[Path] = {
    scalaGraph.V()
      .has(Key[String]("code"), "AUS")
      .repeat(_.out)
      .times(3)
      .has(Key[String]("code"), "AGR")
      .path(By(Key[String]("code")))
      .l()
  }

  def saidpurToChittagong(scalaGraph: ScalaGraph) = {

    scalaGraph.V()
      .has(Key[String]("code"), "SPD")
      .repeat(_.out())
      .times(2)
      //.until(_.has(Key[String]("code"), "AUS"))
      .path(By(Key[String]("city")))
      .l()
  }

  def getDhakaToOneLevel(scalaGraph: ScalaGraph) = {
    scalaGraph.V()
      .has(Key[String]("code"), "DAC").out().value(Key[String]("code")).l()
  }

  def getDist(scalaGraph: ScalaGraph) = {

    val a = StepLabel[Edge]()
    val b = StepLabel[Edge]()
    val c = StepLabel[Int]()
    val d = StepLabel[Int]()

    scalaGraph.V()
      .has(Key[String]("code"), "SPD")
      .outE("route")
      .as(a)
      .value(Key[Int]("dist"))
      .as(c)
      .select(a)
      .inV()
      .outE("route")
      .as(b)
      .value(Key[Int]("dist"))
      .as(d)
      .select(b)
      .inV()
      .has(Key[String]("code"), "CXB")
      .select()
      .headOption()
      .map(elem => elem._2 + elem._4)
  }

  def getPathBetweenTwoAirports(scalaGraph: ScalaGraph, sCode: String, dCode: String) = {

    scalaGraph.V()
      .has(Key[String]("code"), sCode)
      .repeat(_.out().simplePath())
      .until(_.has(Key[String]("code"), dCode))
      .path(By(Key[String]("city")))
      .limit(10)
      .l()

//    val a = StepLabel[java.util.Set[java.lang.Long]]()

  //  scalaGraph.V(5).sideEffect(_.out().count().store(a)).out().select(a).headOption()
  }



  /**
    *
    * @param scalaGraph
    * @param sCode
    */
  def getMaxTwoStepsAirports(scalaGraph: ScalaGraph, sCode: String) = {

    val a = StepLabel[Vertex]()
    val b = StepLabel[String]()
    val c = StepLabel[String]()

    scalaGraph.V()
      .has(Key[String]("code"), sCode)
      .out("route")
      .as(a)
      .value(Key[String]("city"))
      .as("x")
      .select(a)
      .out("route")
      .value(Key[String]("city"))
      .as("y")
      .select("x", "y")
      .l()

  }

  def countByGroup(scalaGraph: ScalaGraph, category: String) = {
    import scala.collection.JavaConversions._
    val x = new java.util.HashMap[java.lang.String, java.lang.Long]
    scalaGraph.V().hasLabel("airport")
      .groupCount(By(Key[String](category))).headOption().getOrElse(x).toMap
  }

}


