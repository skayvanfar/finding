# Introduction
Welcome to Query project. This project demonstrates how to use a Query service to save and search queries.
it includes QueryService which is the main service.
# Software needed
1.	Apache Maven (http://maven.apache.org).
2.	Docker (http://docker.com). 
3.	Git Client (http://git-scm.com).

# How to get google links for queries.txt file from google
I use Spring Batch and define a job which can be called by the api:<br />
http://localhost:8080/invokejob

the defined step in this job is Multi-threaded Step (single process), it's the simplest way for parallel processing

the main technologies which are used:

Spring Boot <br />
Spring Batch <br />
Jsoup <br />
Spring Data <br />
Hibernate <br />
H2 database <br />
Maven <br />
Git <br />
Docker

#Improvements
It's better to use a document based db like mongodb for Horizontal scaling and better performance <br />
with 10000 record in query table, the speed is very good. But for more data and better performance in the future: using redis as cache in search api can improve read speed. <br />
the defined step in this job is Multi-threaded Step (single process), it's the simplest way for parallel processing
better parallel processing can be used (multi process) <br />
when the job which reads queries file and fetch result form Google search engine, sometimes google refuse access to the information because of google recaptcha
and I invoke again with different ip address(using vpn)

# Running application
You can run service by maven command spring-boot:run

# Building the Docker Images
To build the code for project as a docker image, open a command-line window

Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

   **mvn clean package docker:build**

If everything builds successfully you should see a message indicating that the build was successful.

# Running the Application by docker-compose

Now we are going to use docker-compose to start the actual image.  To start the docker image,
change to the directory containing  your project source code.  Issue the following docker-compose command:

   **docker-compose -f docker/common/docker-compose.yml up**

If everything starts correctly you should see a bunch of spring boot information fly by on standard out.  
At this point ContactService will be running.
