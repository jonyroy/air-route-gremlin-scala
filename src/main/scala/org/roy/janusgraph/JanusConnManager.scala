package org.roy.janusgraph

import gremlin.scala._
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.janusgraph.core.schema.JanusGraphManagement
import org.janusgraph.core.{JanusGraph, JanusGraphFactory}
import org.roy.utils.IsNotNull

object JanusConnManager {


  def getJanusGraphInstance(): JanusGraph = {

    try {
      val graph: JanusGraph = JanusGraphFactory.build
        .set("storage.backend", "cql")
        .set("storage.hostname", "127.0.0.1,192.168.0.104")
        .set("storage.port", "9042")
        .set("storage.cql.keyspace", "janusgraph")
        .open()

      graph
    } catch {
      case e: Exception => null
    }

  }

  def getTinkerGraphInstance: ScalaGraph = {
    TinkerGraph.open().asScala()
  }

  def getScalaGraphInstance(graph: JanusGraph): ScalaGraph = {
    try {
      val scalaGraph: ScalaGraph = graph.asScala
      scalaGraph
    } catch {
      case e: Exception => null
    }

  }


  def isGraphOpen(graph: JanusGraph): Boolean = {
    if (IsNotNull(graph) && graph.isOpen) true else false
  }


  def killJanusInstance(graph: JanusGraph): Boolean = {

    try {
      if (IsNotNull(graph) && graph.isOpen) {
        graph.close()
        true
      } else {
        false
      }
    } catch {
      case e: Exception => false
    }
  }


  def commitJanusGraphTx(graph: JanusGraph): Boolean = {
    try {
      if (IsNotNull(graph) && graph.isOpen) {
        graph.tx().commit()
        true
      } else {
        false
      }
    } catch {
      case e: Exception =>
        graph.tx().rollback()
        false
    }
  }


  def commitMgtTx(mgt: JanusGraphManagement): Boolean = {
    try{
      if(IsNotNull(mgt) && mgt.isOpen){
        mgt.commit()
        true
      } else {
        false
      }
    }catch {
      case e: Exception =>
        mgt.rollback()
        false
    }
  }


  def commitScalaGraphTx(sGraph: ScalaGraph): Boolean = {

    try {
      if (IsNotNull(sGraph) && sGraph.tx().isOpen)
        sGraph.tx().commit()
      true
    } catch {
      case e: Exception =>
        sGraph.tx().rollback()
        false
    }
  }


  def getGraphTraversal(graph: JanusGraph): GraphTraversalSource = {

    try {
      if (IsNotNull(graph) && graph.isOpen) {
        graph.traversal()
      } else {
        null
      }
    } catch {
      case e: Exception => null
    }
  }
}