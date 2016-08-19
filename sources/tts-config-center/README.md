# TTS Configuration Center Application

A small application to manage and speed up the process to setup server

# Structure

* model - Service interface and model classes shared between persistence, service and webui
* persistence - DAO implementation using JPA and hibernate
* service - Service implementation 
* rest - REST Service implementation
* webui - WebUI

# Build

mvn clean install

# Installation

Download and start Karaf 4.0.5

Start karaf and execute the commands below

```Shell
cat https://raw.githubusercontent.com/cschneider/Karaf-Tutorial/master/tasklist-blueprint-cdi/org.ops4j.datasource-tasklist.cfg | tac -f etc/org.ops4j.datasource-tasklist.cfg
feature:repo-add mvn:com.tts.app.configcenter/features/1.0.0-SNAPSHOT/xml
feature:install example-tasklist-cdi-persistence example-tasklist-cdi-ui example-tasklist-cdi-service
```

# Test

Open the UI in your browser <http://localhost:8181/tasklist> and work with the tasks.

Alternatively use the REST endpoint <http://localhost:8181/cxf/tasklistRest>

Create Task2 using the rest service

	curl -i -X POST -H "Content-Type: application/json" -d '{task:{"id":2,"title":"Task2"}}'  http://localhost:8181/cxf/tasklistRest

Retrieve Task2

	curl -i http://localhost:8181/cxf/rest/tasklistRest/2

# Import the source in eclipse

	Import... 
	-> Existing maven projects 
	-> Browse to <source> folder 
	-> Select all projects 
	Change option Advanced -> Name template to [groupId].[artifactId] This will make sure we can also import other examples.
	-> Finish
 
