<h1 align="center">
  <br>
  <a><img src="https://github.com/kumarpawan29/timereporting/blob/master/src/main/resources/static/images/spring-framework.png" alt="spring boot"></a>
  <br>
  Time Reporting 
  <br>
</h1>

<h4 align="center">The main purpose of the application is to report worked hours through a web page and to display them on a simple web page.</h4>


<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-green.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-2.1.6.RELEASE-brightgreen.svg" />
    </a>
    <a alt="MySQL">
        <img src="https://img.shields.io/badge/MySQL-8.0.15-orange.svg" />
    </a>
    <a alt="Bootstrap">
        <img src="https://img.shields.io/badge/Bootstrap-v4.3.1-orange.svg"/>
    </a>
    <a alt="jQuery">
      <img src="https://img.shields.io/badge/jquery-3.4.1-blue.svg"/>
    </a>
    <a alt="opencsv">
      <img src="https://img.shields.io/badge/opencsv-4.1-yellow.svg"/>
  </a>
</p>

## Table of Contents ##
1. [Concept](#Concept)
2. [Spring Boot](#Spring-Boot)
3. [Steps to setup](#Steps-to-setup)

## Concept ##
A web application is to report worked hours through a web page and to display them on a simple web page.
It consists of mainly two functionalities. First one is to report worked hours and the second one is to display reported hours.

## Spring Boot ##

The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE (Enterprise Edition) platform.

## Steps to setup ##

Steps to deploy the **Time Reporting** can be divided into four steps:
- Step 1 : Install Java
- Step 2 : Install MySQL
- Step 3 : Install the Application Server (Tomcat)
- Step 4 : Deploy the application war or jar

For installing Java, refer to https://java.com/en/download/help/index_installing.xml

To install MySQL, refer to https://dev.mysql.com/downloads/installer/
After installing MySQL, create database(timereporting).
and configure the MySQL properties(username & password) in *application.properties* of the application.

Now about installing Application Server (Tomcat), by default Starter Web includes a dependency on starter tomcat. Starter Tomcat brings in all the dependencies need to run Tomcat as an embedded server.When we will run *TimereportingApplication.java* as a Java Application, you would see that the server would start up and start serving requests.

Spring Boot’s executable jars are ready-made for most cloud PaaS (Platform-as-a-Service) providers.
to run this **Time Reporting** built using *Spring Boot* we will need to first build it. To build and package into a single executable Jar file with a Maven, use the below command from the project folder which contains the pom.xml file :

1. mvn package
2. mvn install

**Time Reporting** application can be run in two ways: 
1. **java -jar command** : The commond *java -jar target/timereporting-0.0.1-SNAPSHOT.jar* can be used to run.
2. **Run Spring Boot app using Maven** :  To run the application with Maven plugin use *mvn spring-boot:run*
The application will be running at http://localhost:8080/login .

**Note :** Additional settings in *application.properties* file.
To generate reports, two folder paths need to be set.
1. spring.path.employeeMonthReport = {some valid folder path}
2. spring.path.monthReport = {some valid folder path}
And the reports will be generated every day at 6am.

The hours per week for every employee is configurable that can be configured as 
1. spring.users.hours-limit= {number}

**Improvement Areas:**
1. User experience can be improved.
2. A profile page can be added to show logged in user personal information.

**Known Issues:**
1. After selecting maximum hours from dropdown, minutes dropdown should be disabled.

