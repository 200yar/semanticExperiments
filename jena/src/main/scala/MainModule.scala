package swe

import com.hp.hpl.jena.rdf.model._
import com.hp.hpl.jena.util._
import com.hp.hpl.jena.query._

import java.io.InputStream
import org.apache.commons.logging._

object MainModule {

  private val log:Log = LogFactory.getLog( this.getClass )
  private var queryModule : QueryModule = _
  private var model: Model = _
  
  def main( args:Array[String] ){

    model = loadRDFData( "../ontologies/simpleOntology.n3", createEmptyModel )

    queryModule = new QueryModule( model )
    
    var ws = new WebServer( model, queryModule, 8000 )

  }
  
  private def createEmptyModel = ModelFactory.createDefaultModel

  private def loadRDFData( fileName:String, model:Model ):Model = {

    log.debug("load rdf data")

    FileManager.get().open( fileName ) match {

      case null		        => log.error( "File: " + fileName + " not found" ); model
      case s:InputStream	=> loadFileIntoModel( s, model )
      case _              => log.error( "something went wrong" ); model

    }
  }

  private def loadFileIntoModel( in:InputStream, model:Model ):Model = {
    try{
      model.read( in, null, "N3" )
      log.debug("rdf data added successfully")
      model
    }catch{
      case e:Exception  => log.error( "error: " + e ); model
      case _            => log.error( "something went wrong" ); model
    } finally {
      model
    }
  }

}

class Service( val model:Model ){

  val rdfNS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  val rdfsNS = "http://www.w3.org/2000/01/rdf-schema#"

  def addResourceTriple( s:String, p:String, o:String ){

    model.add( ResourceFactory.createStatement(
        model.createResource( s ),
        model.createProperty( p ),
        model.createResource( o )
      )
    ) 
  }

}

