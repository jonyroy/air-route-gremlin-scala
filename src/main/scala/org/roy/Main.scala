package org.roy

import org.roy.janus.conn.manager.JanusConnManager._
import org.janusgraph.core.{JanusGraphFactory, JanusGraph}


object Main {
     def main(arg: Array[String]): Unit = {
       val graph: JanusGraph = getJanusGraphInstance()

       if(isGraphOpen(graph)) {
           println("Hoooree JanusGraph Instance Creation Successful.")
       } else {
           println("Sorry Can't create JanusGraph Instance. Try another time")
       }

       killJanusInstance(graph)
    }
}
