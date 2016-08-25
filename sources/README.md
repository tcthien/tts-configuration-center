# How to install
* Download & start karaf
* Execute following command in karaf
    - feature:repo-add camel 2.16.2
    - feature:repo-add cxf 3.1.5
    - feature:install camel
    - feature:install camel-cxf
    - feature:install cxf-commands
* Build source code
* Execute following command in karaf
    - feature:repo-add mvn:com.tts.app.configcenter/features/1.0.0-SNAPSHOT/xml
    - feature:install tss-configcenter