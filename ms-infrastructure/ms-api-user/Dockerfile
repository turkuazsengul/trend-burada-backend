FROM openjdk:17
ADD ./ms-api-user/ms-api-user-0.0.1-SNAPSHOT.jar ms-api-user-0.0.1-SNAPSHOT.jar
EXPOSE 40000
#ENTRYPOINT ["java","-jar","ms-api-user-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","ms-api-user-0.0.1-SNAPSHOT.jar"]