package swe.lifter.opc

import java.io._
import java.net._
import java.text._
import java.util._

import org.apache.commons.logging._
import org.apache.log4j._

import org.opcfoundation.ua.builtintypes._
import org.opcfoundation.ua.common._
import org.opcfoundation.ua.core._
import org.opcfoundation.ua.transport.security._
import org.opcfoundation.ua.utils._

import com.prosysopc.ua._
import com.prosysopc.ua.PkiFileBasedCertificateValidator._
import com.prosysopc.ua.UaApplication._
import com.prosysopc.ua.client._
import com.prosysopc.ua.nodes._  

import scala.collection.JavaConversions._
import scala.actors._

object OPCLifter extends App{

  private val log:Log = LogFactory.getLog( this.getClass )

  private val serverUri = "opc.tcp://localhost:52520/OPCServer"
  private val clientName = "OPCLifter"
  private val url = "github.com/flosse/semanticExperiments"
  private val addressSpaceName = "SampleAddressSpace"
	private val client = new UaClient( serverUri )
	private val validator = new PkiFileBasedCertificateValidator
  private val appDescription = createAppDescription
	private val identity = createAppIdentity( appDescription )

	log.debug("Setup OPC client ..." )
  setupClient

	log.debug("Connecting to " + serverUri + " ..." )
  connectToOPCServer
	log.debug("... connected" )

  log.debug( "queryAddressSpace" )
  queryAddressSpace

  private def createAppDescription = {

    val appDescription = new ApplicationDescription
    appDescription.setApplicationName( new LocalizedText( clientName, Locale.ENGLISH ) )
    appDescription.setApplicationUri( "urn:localhost:UA:" + clientName )
    appDescription.setProductUri( "urn:" + url + ":UA:" + clientName )
    appDescription.setApplicationType( ApplicationType.Client )

    appDescription
  }
  
  private def createAppIdentity( appDescription:ApplicationDescription ) = 
	  ApplicationIdentity
      .loadOrCreateCertificate(
        appDescription,
        "Semantic Experiments",
        null,
        new File( validator.getBaseDir, "private" ),
        true 
      )

  def setupClient {

    client.setCertificateValidator( validator )
    client.setApplicationIdentity( identity )
    client.setLocale( Locale.ENGLISH )
    client.setSecurityMode( SecurityMode.NONE )
    client.setUserIdentity( new UserIdentity )
  }

  def queryAddressSpace {

    client.getAddressSpace.setMaxReferencesPerNode( 500 ) 
    client.getAddressSpace.setReferenceTypeId( Identifiers.HierarchicalReferences )

    var nt = client.getNamespaceTable

    printNameSpaces( nt )

    var ns = nt.getIndex("http://" + url + "/" + addressSpaceName )

    val mySwitchNodeId = new NodeId( ns, "MySwitch")
    val mySwitchData = client.readValue( mySwitchNodeId )
    log.debug("Status of MySwitch: " + mySwitchData.getValue )

    val myNumberNodeId = new NodeId( ns, "MyNumber")
    val myNumberData = client.readValue( myNumberNodeId )
    log.debug("Status of MyNumber: " + myNumberData.getValue )

  }

  private def printNameSpaces( nt: NamespaceTable ) {
    log.debug( "Namespaces:" )
    for( i <- 0 to 5 )
      log.debug( "Index= " + i + " Uri= " + nt.getUri( i ) )
  }

  private def connectToOPCServer{

		if( !client.isConnected ){
			try {
				client.connect
			} catch{
        case e:InvalidServerEndpointException => println( e.getMessage )
        case e:ServerConnectionException      => println( e.getMessage )
        case e:SessionActivationException     => println( e.getMessage )
        case e:ServiceException               => println( e.getMessage )
        case _                                =>
			}
    }
	}
}


