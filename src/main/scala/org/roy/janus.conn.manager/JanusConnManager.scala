package org.roy.janus.conn.manager


import org.janusgraph.core.{JanusGraphFactory, JanusGraph}
import org.roy.utils.CheckArgs

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
}