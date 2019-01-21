package org.roy.janusgraph

import org.apache.tinkerpop.gremlin.structure.Vertex
import org.janusgraph.core._
import org.janusgraph.core.schema.JanusGraphManagement
import org.roy.utils.IsNotNull
import org.roy.utils.Utils._

object SchemaGenerator {

  def genSchema = {

    val graph: JanusGraph = JanusConnManager.getJanusGraphInstance()

    println(s"Schema Gen Start.")

    val mgt: JanusGraphManagement = graph.openManagement()

    val vertexLabel = createVertexLabel(mgt, "person")

    val idPropertyKeyOption: Option[PropertyKey] = createPropertyKey(mgt, "id", PropertyKeyTypes.INTEGER)
    val firstNamePropertyKeyOption = createPropertyKey(mgt, "firstName", PropertyKeyTypes.STRING)
    val lastNamePropertyKeyOption = createPropertyKey(mgt, "lastName", PropertyKeyTypes.STRING)
    val addressPropertyKeyOption = createPropertyKey(mgt, "address", PropertyKeyTypes.STRING, PropertyCardinality.SET)
    val mobilePropertyKeyOption = createPropertyKey(mgt, "mobile", PropertyKeyTypes.STRING, PropertyCardinality.SET)
    val emailPropertyKeyOption = createPropertyKey(mgt, "email", PropertyKeyTypes.STRING, PropertyCardinality.SET)

    idPropertyKeyOption match {
      case Some(idPropertyKey) => mgt.buildIndex("id", classOf[Vertex]).addKey(idPropertyKey).buildCompositeIndex()
      case None =>
    }

    JanusConnManager.commitMgtTx(mgt)

    JanusConnManager.commitJanusGraphTx(graph)

    JanusConnManager.killJanusInstance(graph)

    println(s"Schema Gen Ends")

  }

  private def createVertexLabel(mgt: JanusGraphManagement, newVertexLabel: String): Option[VertexLabel] = {

    if (IsNotNull(mgt) && isStringNonEmpty(newVertexLabel)) {

      if (!isVertexLabelExist(mgt, newVertexLabel)) {
        Option(mgt.makeVertexLabel(newVertexLabel).make())
      } else {
        None
      }
    } else {
      None
    }
  }

  private def isVertexLabelExist(mgt: JanusGraphManagement, vertexLabel: String): Boolean = {

    if (IsNotNull(mgt) && isStringNonEmpty(vertexLabel)) {

      val label: VertexLabel = mgt.getVertexLabel(vertexLabel)

      if (IsNotNull(label)) {
        true
      } else {
        false
      }
    } else {
      false
    }

  }

  private def createPropertyKey(mgt: JanusGraphManagement, propertyKey: String, propertyType: String, cardinality: String = "single"): Option[PropertyKey] = {

    if (IsNotNull(mgt) && isStringNonEmpty(propertyKey, propertyType) && (!isPropertyExist(mgt, propertyKey))) {

      val pMake = propertyType match {
        case "string" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[String]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[String]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[String]).cardinality(Cardinality.LIST).make()
          }

        case "character" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[Character]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[Character]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[Character]).cardinality(Cardinality.LIST).make()
          }
        case "boolean" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Boolean]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Boolean]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Boolean]).cardinality(Cardinality.LIST).make()
          }
        case "byte" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Byte]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Byte]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Byte]).cardinality(Cardinality.LIST).make()
          }
        case "short" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Short]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Short]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Short]).cardinality(Cardinality.LIST).make()
          }
        case "integer" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Integer]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Integer]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Integer]).cardinality(Cardinality.LIST).make()
          }
        case "long" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Long]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Long]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Long]).cardinality(Cardinality.LIST).make()
          }
        case "float" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Float]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Float]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Float]).cardinality(Cardinality.LIST).make()
          }
        case "double" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Double]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Double]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.lang.Double]).cardinality(Cardinality.LIST).make()
          }
        case "date" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.Date]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.Date]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.Date]).cardinality(Cardinality.LIST).make()
          }
        //        case "geoshape" =>
        //          cardinality match {
        //            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[]).cardinality(Cardinality.SINGLE).make()
        //            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[]).cardinality(Cardinality.SET).make()
        //            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[]).cardinality(Cardinality.LIST).make()
        //          }
        case "uuid" =>
          cardinality match {
            case "single" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.UUID]).cardinality(Cardinality.SINGLE).make()
            case "set" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.UUID]).cardinality(Cardinality.SET).make()
            case "list" => mgt.makePropertyKey(propertyKey).dataType(classOf[java.util.UUID]).cardinality(Cardinality.LIST).make()
          }

        case _ => null
      }

      Option(pMake)

    } else {
      None
    }
  }

  private def isPropertyExist(mgt: JanusGraphManagement, propertyName: String): Boolean = {

    if (IsNotNull(mgt) && isStringNonEmpty(propertyName)) {
      val propertyKey: PropertyKey = mgt.getPropertyKey(propertyName)
      if (IsNotNull(propertyKey)) true else false
    } else {
      false
    }
  }

  private def getJanusManagement(graph: JanusGraph): Option[JanusGraphManagement] = {
    if (IsNotNull(graph)) {
      Option(graph.openManagement())
    } else {
      None
    }
  }

  private def createEdgeLabel(mgt: JanusGraphManagement, newEdgeLabel: String): Option[EdgeLabel] = {
    if (IsNotNull(mgt) && isStringNonEmpty(newEdgeLabel)) {
      if (!isEdgeLabelExist(mgt, newEdgeLabel)) {
        Option(mgt.makeEdgeLabel(newEdgeLabel).make())
      } else {
        None
      }
    } else {
      None
    }
  }

  private def isEdgeLabelExist(mgt: JanusGraphManagement, edgeLabel: String): Boolean = {

    if (IsNotNull(mgt) && isStringNonEmpty(edgeLabel)) {

      val eLabel: EdgeLabel = mgt.getEdgeLabel(edgeLabel)

      if (IsNotNull(eLabel)) {
        true
      } else {
        false
      }
    } else {
      false
    }

  }

  object PropertyKeyTypes {
    lazy val STRING = "string"
    lazy val CHARACTER = "character"
    lazy val BOOLEAN = "boolean"
    lazy val BYTE = "byte"
    lazy val SHORT = "short"
    lazy val INTEGER = "integer"
    lazy val LONG = "long"
    lazy val FLOAT = "float"
    lazy val DOUBLE = "double"
    lazy val DATE = "date"
    lazy val GEOSHAPE = "geoshape"
    lazy val UUID = "uuid"
  }

  object PropertyCardinality {
    lazy val SINGLE = "single"
    lazy val SET = "set"
    lazy val LIST = "list"
  }

}
