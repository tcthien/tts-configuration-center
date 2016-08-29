# How to install
* Download & start karaf
* Execute following command in karaf
    - feature:repo-add camel 2.16.2
    - feature:repo-add cxf 3.1.5
    - feature:install camel
    - feature:install camel-cxf
    - feature:install cxf-commands
    - bundle:install mvn:org.codehaus.jackson/jackson-jaxrs/1.9.13
* Install Hiberante:
	- feature:repo-add mvn:org.hibernate/hibernate-osgi/5.0.0.Final/xml/karaf
	- feature:install hibernate-orm
* Copy datasource-h2.xml to karaf deploy dir
	
    

feature:repo-add mvn:org.ops4j.pax.jdbc/pax-jdbc-features/0.5.0/xml/features
feature:install transaction jndi pax-jdbc-h2 pax-jdbc-pool-dbcp2 pax-jdbc-config
service:list DataSourceFactory