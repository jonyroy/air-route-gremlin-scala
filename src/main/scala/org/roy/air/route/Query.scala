package org.roy.air.route

import gremlin.scala._

object Query {

  def firstQuery(graph: ScalaGraph) = {
    val code = Key[String]("code")
    graph.V().has(code, "AUS").valueMap("country").l()
  }

  def getCountryNameByCode(graph: ScalaGraph, countryCode: String): List[String] = {
    val code: Key[String] = Key[String]("code")
    val country: Key[String] = Key[String]("country")

    graph.V().has(code, countryCode).dedup().value(country).l()
  }

  def getCountryGroupBy(scalaGraph: ScalaGraph) = {

    scalaGraph.V().hasLabel("airport").groupCount().by("country").l()
  }

}
