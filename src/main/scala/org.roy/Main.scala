package org.roy



object Main {
    override def main(arg: Seq[String]): Unit = {
        val graph: JanusGraph = JanusGraphFactory.build
          .set("storage.backend","cql")
          .set()
    }
}
