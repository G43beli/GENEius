# GeneWebApp

The Gene Web Application is part of the software system in the
Medical Informatics Master program. The web application will consume
data from the Gene Rest Service and will display the data in a simple
Java web application.

## Build the Application
Maven is used for the build process. The following command will build the
application:

    mvn clean package

If successful, a WAR (Web Archive) file will be created in the target
folder.This WAR file needs to be deployed on an external Web Server.
In the lectures, Apache Tomcat will be used. But of course, there
are other servers available (e.g. JBoss).

## Deployment
Start Apache Tomcat on your machine. Connect to the Apache Tomcat using your
browser:

    http://localhost:8080

Maybe a different port is used in your case. The port could be configured in
the conf/server.xml file of Apache Tomcat.

On the upper right hand, there is a button **Manager App**. Press that
button and enter username and password. The username and password is stored
in the conf/tomcat-users.xml file. If you have not yet added the user,
please add the following lines:

    <role rolename="manager-gui" />
    <role rolename="manager-script" />
    <user username="admin" password="tomcat" roles="manager-gui,manager-script" />

Use the WAR file to deploy section to deploy the created WAR file.
After successful deployment, the web application should be running.

Please make sure, that also the REST service as well as the database is
running in the background.
