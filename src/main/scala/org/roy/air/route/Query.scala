package org.roy.air.route


import java.util.Date

import gremlin.scala._
import org.roy.janusgraph.JanusConnManager
import org.roy.utils.{IsNotNull, Utils}

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

  def getNumberOfVertices(scalaGraph: ScalaGraph): Long = {
    val numOfVertices = scalaGraph.V().count().headOption()
    numOfVertices match {
      case Some(value) => value
      case None => 0
    }
  }


  def getNameOfPerson(scalaGraph: ScalaGraph, id: Int) = {
    scalaGraph.V().hasLabel("person").has(Key[Int]("id"), id).value(Key[String]("firstName")).l()
  }



  def vertexCreationManager(scalaGraph: ScalaGraph) = {

    val startTime = Utils.getCurrentTimeMS

    var tracer = startTime

    var commitCoumter = 1

    var counter = 0

    val id = Key[Int]("id")
    val firstName = Key[String]("firstName")
    val lastName = Key[String]("lastName")
    val address = Key[String]("address")
    val mobile = Key[String]("mobile")
    val email = Key[String]("email")

    (0 to 5000).foreach(vertexId => {
      val newVertexOption = createVertex(scalaGraph, "person")
      addPropertyToVertex(newVertexOption, id, vertexId)
      addPropertyToVertex(newVertexOption, firstName, "Jony")
      addPropertyToVertex(newVertexOption, lastName, "Roy")
      addPropertyToVertex(newVertexOption, address, "Tha Block, Road: 2, House No: 277, Mirpur-12, Dhaka")
      addPropertyToVertex(newVertexOption, mobile, "+08801741346826")
      addPropertyToVertex(newVertexOption, email, "jonyroyice@gmail.com")

      counter += 1

      if(counter >= 2000) {
        val t = Utils.getCurrentTimeMS
        JanusConnManager.commitScalaGraphTx(scalaGraph)
        println(s"Commit $commitCoumter : ${t - tracer}ms")

        commitCoumter += 1
        tracer = t
        counter = 0
      }
    })

    if(counter >= 1) {
      JanusConnManager.commitScalaGraphTx(scalaGraph)
      counter = 0
    }

    println(s"Total Time: ${Utils.getCurrentTimeMS - startTime} ms")

  }

  def createVertex(scalaGraph: ScalaGraph, vertexLabel: String):Option[Vertex] = {

    if (IsNotNull(scalaGraph.graph)) {
      try {
        Option(scalaGraph.addVertex(vertexLabel))
      } catch {
        case e: Exception => None
      }
    } else {
      None
    }
  }

  def addPropertyToVertex[A](vertexOption: Option[Vertex], propertyKey: Key[A], value: A ) = {
    vertexOption match {
      case Some(vertex) => vertex.setProperty(propertyKey, value)
      case None =>
    }
  }

}
