# imixs-spring

The Imixs-Spring project is a place providing integration examples. Imixs-Workflow is based on Jakarta EE but 
Imixs-Workflow Spring Examples


Imixs is based on Jakarta EE and the Microprofile standard. Thanks to its powerful interfaces, Imixs-Workflow  can easily be integrated with other platforms. In this project we show integration examples for the Spring Framework.

**Note**: If you want to run Imixs-Workflow as a microservice take a look into the [Imixs-Microservice](https://github.com/imixs/imixs-microservice) project. If you want to call the Imixs-Workflow Rest-API you just need the [Imixs-Melman library](https://github.com/imixs/imixs-melman) which provides a convenient rest client based on Jax-RS API. 
 

If you have any questions about Imixs-Worklfow and Spring feel free to ask your questions [here](https://github.com/imixs/imixs-spring/issues).



## Spring Project Dependencies

To get started with the examples, you need to include Spring MVC in your Maven pom.xml only. See the following example:

	...
	<properties>
		<org.imixs.workflow.version>5.1.9</org.imixs.workflow.version>
		<spring.version>5.2.5.RELEASE</spring.version>
	</properties>

	<dependencies>
	
		<!-- Imixs Workflow core dependencies -->
		<dependency>
			<groupId>org.imixs.workflow</groupId>
			<artifactId>imixs-workflow-core</artifactId>
			<version>${org.imixs.workflow.version}</version>
		</dependency>
		
		<!-- Spring dependencies -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
	</dependencies>
	...

		
## JAXB Integration


Since Spring 3, one of the feature of “mvc:annotation-driven“, is the support to convert object to/from XML, if JAXB is in the project classpath. The Imixs-Workflow-Core library already supports XML Root classes for the core data objects. So it is very easy to apply these object classes in a Spring rest service. 

See the following example code:


```java
  @PostMapping("data")
  public ResponseEntity<?> getData(@RequestBody XMLDocument requestXML) {
    // consume xml request
    ItemCollection requestData = XMLDocumentAdapter.putDocument(requestXML);
    // consume data....
    @SuppressWarnings("unused")
    String param1 = requestData.getItemValue("param1", String.class);


    // create response data object
    ItemCollection resultData = new ItemCollection();
    resultData.setItemValue("_subject", "some data...");

    // convert resultData into xml
    return ResponseEntity.ok().body(XMLDocumentAdapter.getDocument(resultData));

  }
```

The XMLDocument provided by the Imixs-Workflow XML objects can be adapted into the [ItemCollection](https://www.imixs.org/doc/core/itemcollection.html). class providing a large list of methods to build complex data objects within a business process. 



