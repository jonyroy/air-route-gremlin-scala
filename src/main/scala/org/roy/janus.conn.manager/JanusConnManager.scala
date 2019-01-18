package org.roy.janus.conn.manager

import gremlin.scala._
import org.janusgraph.core.{JanusGraphFactory, JanusGraph}
import org.roy.utils.CheckArgs

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource

object JanusConnManager {

    def getJanusGraphInstance(): JanusGraph = {

        try {
             val graph: JanusGraph = JanusGraphFactory.build
                .set("storage.backend","cql")
                .set("storage.hostname", "127.0.0.1")
                .set("storage.port", "9042")
                .set("storage.cql.keyspace", "janusgraph")
                .open()
             
             graph
        } catch {
            case e: Exception => null
        }
         
    }

    def getScalaGraphInstance(graph: JanusGraph): ScalaGraph = {
        val scalaGraph: ScalaGraph = graph.asScala
        scalaGraph
    }


    def isGraphOpen(graph: JanusGraph): Boolean = {
       if(CheckArgs(graph) && graph.isOpen()) true else false
    }


    def killJanusInstance(graph: JanusGraph): Boolean = {
        
        try {
            if(CheckArgs(graph) && graph.isOpen()){
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
            if(CheckArgs(graph) && graph.isOpen()){
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



    def getGraphTraversal(graph: JanusGraph): GraphTraversalSource = {
        
        try {
            if(CheckArgs(graph) && graph.isOpen()){
                graph.traversal()
            } else {
                null
            }
        } catch {
            case e: Exception => null
        }
    }
}