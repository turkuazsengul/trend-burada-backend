#!/bin/bash

removeJarFile(){
  rm -rf $1
}

createPackage(){
  echo "********** ms-node-configuration package process started **********"
  echo ""

  mvn -f $1 clean install -DskipTests

  echo ""
  echo ">>>>>>>>> Package Created"

  mv $2 $3

  echo ">>>>>>>>> Package Moved To Infra Directory"
  echo ""
  echo "********** ms-node-configuration package process completed **********"
}

buildFromDockerfile(){
  echo ""
  echo "********** ms-node-configuration docker build process started **********"
  echo ""

  docker build -f $1 . --tag $2

  echo ""
  echo "********** ms-node-configuration docker build process completed **********"
}

pushDockerImage(){
  echo ""
  echo "********** ms-node-configuration docker image push process started **********"
  echo ""

  docker push $1

  echo ""
  echo "********** ms-node-configuration docker image push process completed **********"
}


CONFIGURATION_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-node-configuration"
CONFIGURATION_JAR_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-node-configuration\target\ms-node-configuration-0.0.1-SNAPSHOT.jar"
CONFIGURATION_INFRA_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-node-configuration"
CONFIGURATION_DOCKERFILE_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-node-configuration\Dockerfile"

EUREKA_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-node-discovery"
EUREKA_JAR_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-node-discovery\target\ms-node-discovery-0.0.1-SNAPSHOT.jar"
EUREKA_INFRA_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-node-discovery"
EUREKA_DOCKERFILE_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-node-discovery\Dockerfile"

MS_USER_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-api-user"
MS_USER_JAR_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-api-user\target\ms-api-user-0.0.1-SNAPSHOT.jar"
MS_USER_INFRA_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-api-user"
MS_USER_DOCKERFILE_PATH="C:\Users\turku\Desktop\DEV\trend-burada-backend\ms-infrastructure\ms-api-user\Dockerfile"


#createPackage $CONFIGURATION_PATH $CONFIGURATION_JAR_PATH $CONFIGURATION_INFRA_PATH
#buildFromDockerfile $CONFIGURATION_DOCKERFILE_PATH turkuazsengul/ms-node-configuration:latest
#pushDockerImage turkuazsengul/ms-node-configuration:latest

#createPackage $EUREKA_PATH $EUREKA_JAR_PATH $EUREKA_INFRA_PATH
#buildFromDockerfile $EUREKA_DOCKERFILE_PATH turkuazsengul/ms-node-discovery:latest
#pushDockerImage turkuazsengul/ms-node-discovery:latest

createPackage $MS_USER_PATH $MS_USER_JAR_PATH $MS_USER_INFRA_PATH
buildFromDockerfile $MS_USER_DOCKERFILE_PATH turkuazsengul/ms-api-user:latest
pushDockerImage turkuazsengul/ms-api-user:latest
