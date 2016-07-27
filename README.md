Hiperium Project: Platform for the Internet of Things.
========================
Author: Andres Solorzano
Level: Advanced  
Technologies: CDI, RESTful, JPA, EJB, JTA, NoSQL
Summary: This project is the audit microservice that stores user activity in a smart home.  
Target Product: Wildfly 10
Source: <https://bitbucket.org/aosolorzano/hiperium-logging>  

What is it?
-----------

The Hiperium Audit is a microservice of the hiperium Project for the Internet of Things. This audit project stores any activity that users perform in their smart homes with the goal to make analytics for that actions.

System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Wildfly 10 or later.

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Maven 3.0 or later, and Docker 1.10 or later.


Docker Image
-------------------

This repository contains the instructions needed to create a docker image based on the Hiperium Logging Service.


Dependencies
-------------------

Docker Engine


Deploying
-------------------

Execute the following commands to run the docker image in your host computer:

* docker pull hiperium/hiperium-logging
* docker run -it -d hiperium/hiperium-logging


Access the application 
---------------------

The application will service many Restful functionalities that are running at the following URL: <http://localhost:8080/hiperium-logging/api/logging/>. You need the client that are developed using IonicFramework that use AngularJS to access the services.
