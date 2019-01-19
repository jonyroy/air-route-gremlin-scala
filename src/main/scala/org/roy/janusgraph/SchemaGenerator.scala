package org.roy.janusgraph

import org.janusgraph.core.JanusGraph

object SchemaGenerator {

  def genSchema = {
    val graph: JanusGraph = JanusConnManager.getJanusGraphInstance()
    val mgt = graph.openManagement()
  }

}
