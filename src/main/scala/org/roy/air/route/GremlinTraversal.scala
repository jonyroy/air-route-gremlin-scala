package org.roy.air.route

import gremlin.scala._
import org.apache.tinkerpop.gremlin.process.traversal.Path

object GremlinTraversal {


  def repeatStep(scalaGraph: ScalaGraph): List[Path] = {
    scalaGraph.V().has(Key[String]("code"), "AUS").repeat(_.out).times(3).has(Key[String]("code"), "AGR").path(By(Key[String]("code"))).l()
  }
}
