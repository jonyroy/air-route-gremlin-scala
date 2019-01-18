package org.roy.air.route

import gremlin.scala._

object Query {
    
    def firstQuery(graph: ScalaGraph) = {
        val code = Key[String]("code")
        graph.V().has(code, "AUS").valueMap.l()
    }
}
