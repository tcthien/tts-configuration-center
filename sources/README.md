# TTS Configuration Center Application

This application is used to manage which software feature(docker, docker-compose) installed on a server. We can also use this software to install software to multiple server.

# Structure

* tts-configcenter-angular-ui: Source for WEB UI built with Angular & Boothstrap
* tts-configcenter-model: Define some generic model, dao... for application
* tts-configcenter-persistence: Define DAO Implement 
* tts-configcenter-service: Define REST Service

# Build

mvn clean install

# Installation

Download and start Karaf 4.0.5

Start karaf and execute the commands below

H2 Memory DB:
	Copy <root>/org.ops4j.datasource-configcenter.cfg to <karaf>/etc
MySQL DB:
	install -s mvn:org.apache.servicemix.specs/org.apache.servicemix.specs.stax-api-1.0/1.9.0
	install -s mvn:mysql/mysql-connector-java/5.1.18
	Copy datasource-mysql.xml to <karaf>/deploy

Copy jsch-0.1.53.jar JSCH from <home dir>/.m2/repository/com/jcraft/jsch/0.1.53/ to <karaf>/deploy 

```Shell
feature:repo-add mvn:com.tts.app.configcenter/tts-configcenter-features/1.0.0-SNAPSHOT/xml
feature:install tts-configcenter-persistence tts-configcenter-service tts-configcenter-ui
```

# Test

Open the UI in your browser <http://<your id address>:8181/tasklist> and work with the tasks.

Alternatively use the REST endpoint <http://<your id address>:8181/cxf/zone>


# Import the source in eclipse

	Import... 
	-> Existing maven projects 
	-> Browse to tasklist-ds folder 
	-> Select all projects 
	Change option Advanced -> Name template to [groupId].[artifactId]. This will make sure we can also import other examples.
	-> Finish
 
