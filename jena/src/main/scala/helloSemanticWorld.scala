package swe

import com.hp.hpl.jena.rdf.model._
import com.hp.hpl.jena.util._
import com.hp.hpl.jena.query._

object helloSemanticWorld{

  def main( args:Array[String] ){

    val model = ModelFactory.createDefaultModel
    println("repository initialized")

    println("adding file content...")
    val inputFileName = "../ontologies/simpleOntology.n3"
    val in = FileManager.get().open( inputFileName )

    in match {
      case null		=> println( "File: " + inputFileName + " not found" )
      case _		=> read
    }  

    def read {
      try{
	model.read( in, null, "N3" )
	println("content added")
	query
      }catch{
	case e:Exception	=> println("error: " + e )
	case _			=> println("something went wrong" )
      }
    }

    def query {

      println("sample quering...")

      var textSearchString = "sensor"
      val prefix = "PREFIX so: <http://github.com/flosse/semanticExperiments/ontologies/simpleOntology#> "
      val select = "SELECT DISTINCT ?s "
      val where = "WHERE { ?s ?p ?o . FILTER ( regex( str(?s) , \"(?i)" + textSearchString + "\" ) ) } " 
      val order = "ORDER BY ?s"
      val queryString = prefix + select + where + order

      println("SPARQL: " + queryString )

      val query = QueryFactory.create( queryString )
      val qexec = QueryExecutionFactory.create( query, model )

      try {
        println("Result:")
        val results = qexec.execSelect
        while( results.hasNext ){
          val soln = results.nextSolution
          val x = soln.get("s")
          println( x )
        }
      }catch{
        case e:Exception	=> println("error: "+ e )
        case _		=> println("something went wrong" )
      } finally { qexec.close }
    }
  }
}
