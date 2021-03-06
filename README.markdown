# semanticExperiments

This repository contains some code of my experiments with semantic web technologies.

## Overview

The following architecture is used at the moment.

 ![ Architecture overview ]( https://github.com/flosse/semanticExperiments/raw/master/overview.png )

### Origins

These are the sources where the raw data comes from. This could be an OPCServer
an XML file or a microcontroller.

### Lifter

The lifters know their data source and transform the raw data into semantic data.
Each lifter can act as an agent or as a standalone program.

### Backend

The backend provides a service layer that can be used by the lifters to push
semantic data into the global model. It also provides a SPARQL interface for the
webClient. As a basic framework Jena is used at the moment but e.g. sesame could
do the same job. Like the lifters the backend can be started in a standalone mode
or as an agent.

### WebClient

The webClient privides an search interface for the user. The search can be stated
more precisely by using filters.

## Software & Tools

The following software and tools are used:

- [W3C RDF Validator](http://www.w3.org/RDF/Validator/)
- [rdf:about RDF Validator](http://www.rdfabout.com/demo/validator/)
- [Arduino IDE](http://arduino.cc/en/Main/Software) - IDE for programming arduino µControllers
- [Sesame](http://www.openrdf.org/) - Semanitc Web Framwork for Java
- [Jena](http://jena.sourceforge.net/) - Semanitc Web Framwork for Java
- [rdfQuery](https://github.com/alohaeditor/rdfQuery) - Javascript library for RDF-related processing
- [JADE](http://jade.tilab.com/) - Agent platform
- [AgentOWL](http://agentowl.sourceforge.net/) - library for RDF/OWL support in [JADE](http://jade.tilab.com/)
- [Scala](http://www.scala-lang.org/) - Programming language for the JVM
- [sbt](https://github.com/sbt/sbt) - a build tool for Scala
- [CoffeeScript](http://www.coffeescript.org) - a little language that compiles into JavaScript


## Interesting JavaScript libraries

- [rdfstore-js](https://github.com/antoniogarrote/rdfstore-js) - a great RDF store with SPARQL support, 2013
- [rdf.js](https://github.com/webr3/rdf.js) - RDF Tooling, 2011
- [js3](https://github.com/webr3/js3) - generates RDF out of JavaScript values and objects, 2010
- [rdfQuery](https://github.com/alohaeditor/rdfQuery) - Javascript library for RDF-related processing, 2011
- [Jstle](https://github.com/dnewcome/jstle) - RDF serialization language, 2010
- [jOWL](https://code.google.com/p/jowl-plugin/) - a jQuery plugin for processing OWL, MIT, 2009
- [RDFAuthor](https://code.google.com/p/rdfauthor/), GPLv3, 2011
- [rdf-parser](http://www.jibbering.com/rdf-parser/) - a simple RDF parser, 2006
- [hercules](http://hercules.arielworks.net/) - framework for semantic web applications, 2008
- [sparql.js](http://www.thefigtrees.net/lee/sw/sparql.js) - JS library for processing SPARQL queries, 2007
- [vie.js ](http://bergie.github.com/VIE/) - Library for making RDFa -annotated content on a web pages editable, 2013
- [jquery-sparql](https://github.com/jgeldart/jquery-sparql) - a SPARQL jQuery plugin, 2010
- [node-neo4j](https://github.com/thingdom/node-neo4j) - Neo4j graph database driver (REST API client) for Node.js, 2013
- [LevelGraph](https://github.com/mcollina/node-levelgraph) - A graph database built on top of LevelUp, 2013

### Graph visualization

- [d3](http://mbostock.github.com/d3/)
- [moowheel](http://labs.unwieldy.net/moowheel/)
- [Dracula](http://www.graphdracula.net/)
- [InfoVis Toolkit](http://thejit.org/)
- [Protovis](http://vis.stanford.edu/protovis/)
- [arbor.js](http://arborjs.org/)

## Weblinks

- [Protégé](http://protege.stanford.edu/) - OWL-Editor
- [RDF-JSON](http://docs.api.talis.com/platform-api/output-types/rdf-json)
- [JQbus](http://svn.foaf-project.org/foaftown/jqbus/intro.html) - XMPP query services
- [SPARQL By Example - A Tutorial](http://www.cambridgesemantics.com/2008/09/sparql-by-example/)
- [Semantic web basics](http://www.linkeddatatools.com/semantic-web-basics)
- [SPARQL implementations](http://www.w3.org/wiki/SparqlImplementations)
- [Jena JavaDoc](http://jena.sourceforge.net/javadoc/index.html)
- [Jena ARQ JavaDoc](http://jena.sourceforge.net/ARQ/javadoc/)
- [Joseki](http://www.joseki.org/) - SPARQL Endpoint for Jena
- [Scala Style Guide](https://github.com/davetron5000/scala-style/)
- [Strategies for Building Semantic Web Applications](http://notes.3kbo.com/)
- http://semanticweb.com/
- [SemanticOverflow](http://answers.semanticweb.com/)
- [Where can I learn about the semantic web](http://answers.semanticweb.com/questions/1/where-can-i-learn-about-the-semantic-web)
- [Linked Data Patterns](http://patterns.dataincubator.org/book/)

## Build

For compiling JAR files [sbt](https://github.com/sbt/sbt)
([Setup](http://www.scala-sbt.org/release/docs/Getting-Started/Setup))
 is used.

To build a project change to the according directory and type `sbt` to reach the
sbt promt. To compile the source just type

    > compile

Or if you want compile on every change use

    > ~compile

With `run` you start the compiled program and with `assembly` you can create a
JAR file with all dependencies included.

#### IntelliJ IDEA

To use scala with IntelliJ install the sbt-idea-plugin as processor and execute
`idea` to create IDEA project files:

    > *sbtIdeaRepo at http://mpeltonen.github.com/maven/
    > *idea is com.github.mpeltonen sbt-idea-processor 0.1-SNAPSHOT

    > update

    > idea

Install the Scala plugin in IntelliJ and have fun!

#### Eclipse

Run `sbt` and generate the eclipse project and classpath file with

    > eclipse

For more information have a look at http://www.scala-ide.org/ and https://github.com/musk/SbtEclipsify

### WebClient

1. Install [Node.js](http://www.nodejs.org) and
   [CoffeeScript](http://www.coffeescript.org).
2. Compile `.coffee` files with `coffee -c *.coffee`

### API documentation

**sbt** supports **scaladoc**, so just type `doc` to create the documentation.

## Licence

The source code is licenced under the GPLv3.
