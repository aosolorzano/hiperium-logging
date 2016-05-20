Hiperium Project: Platform for the Internet of Things.
========================
Author: Andres Solorzano
Level: Advanced  
Technologies: CDI, RESTful, JPA, EJB, JTA, NoSQL
Summary: This project is the audit microservice that stores user activity in a smart home.  
Target Product: Wildfly 10
Source: <https://bitbucket.org/aosolorzano/hiperium-audit>  

What is it?
-----------

The Hiperium Audit is a microservice of the hiperium Project for the Internet of Things. This audit project stores any activity that users perform in their smart homes with the goal to make analytics for that actions.

System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Wildfly 10 or later.

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Maven 3.0 or later.


Start the JBoss Wildfly Server
-------------------------

1. Open a command prompt and navigate to the root of the JBoss Wildfly directory.
2. The following shows the command line to start the server:

        For Linux:   JBOSS_HOME/bin/standalone.sh --server-config=standalone.xml
        For Windows: JBOSS_HOME\bin\standalone.bat --server-config=standalone.xml


Access the application 
---------------------

The application will service many Restful functionalities that are running at the following URL: <http://localhost:8080/hiperium-audit/api/audit/>. You need the client that are developed using IonicFramework that use AngularJS to access the services.
