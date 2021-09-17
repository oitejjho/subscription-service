<h1 align="center"> Subscription Service API </h1> <br>

<p align="center">
  This service will communicate with internal microservices and publish successful subscription
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Test](#test)
- [Improvements](#improvements)




## Introduction

This service is responsible for saving subscription data in persistence datastore, publish subscription event to rabbitmq to notify email service.

## Features
* Microservice architecture
* Event driven architecture
* * Queues are treated as streams to get faster and reliable delivery and consumption 
* Smart Endpoints and Dumb Pipes
* Validate request from public service or other upstream services
* Save subscription is database
* Return response to public service or other upstream services
* publish event in event bus in reactive way

## Requirements
The application can be run locally. Containerization is in progress

### Local
* [Java 16 SDK](https://www.oracle.com/java/technologies/downloads/#java16)
* [Maven](https://downloads.apache.org/maven/maven-3/3.8.1/binaries/)
* [RabbitMQ](https://hub.docker.com/r/bitnami/rabbitmq/)

## Quick Start
Make sure your maven is pointing to JAVA_HOME and JAVA_HOME is set to Java16 JDK.

Make sure your RabbiMQ docker is up and running to see successful subscription event.

The RabbitMQ host value in the __application.yml__ file is set to `localhost`.

### Run Local
If your JAVA_HOME is set to Java16 JDK
```bash
$ mvn clean install
$ java -jar target/subscription-service-0.0.1-SNAPSHOT.jar
```

For multiple JDK issue
```bash
$ JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home
$ export JAVA_HOME
$ mvn clean install
$ java -jar target/subscription-service-0.0.1-SNAPSHOT.jar
```

Application will run by default on port `9090`

Configure the port by changing `server.port` in __application.yml__

## Test
100% UnitTest code coverage for business and controller layers using Junit Jupiter is used.


## Improvements
* API documentation using swagger-ui and open-api docs
* Adoption of Reactive way of coding in whole project
* Containerization
* Integration with CICD i.e. jenkins / rancher
* Metrics Expose
* Integration with jaeger / slueth / opentelemetry for better visibility
* Integration with metrics collector i.e. prometheus
* Integration with ELK stack
* Integration with grafana for better visibility, observability and alerts
* Integration test
* Automation testing or behavioral testing i.e. RobotFramework, Selenium 