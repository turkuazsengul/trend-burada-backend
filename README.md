# TREND-BURADA-BACKEND

- Trend Burada web sitesinin mikro servis ekosistemi içerisinde yer alan kaynak kodlarını içerir.

## SETUP WITH DOCKER

#### NODE DISCOVERY(EUREKA)

*  `docker pull turkuazsengul/ms-node-discovery`

#### NODE CONFIGURATION 

*  `docker pull turkuazsengul/ms-node-configuration`

#### PRODUCT API

*  `docker pull turkuazsengul/ms-api-product`
 
#### USER API

*  `docker pull turkuazsengul/ms-api-user`

#### MAIL API

*  `docker pull turkuazsengul/ms-api-mail`

#### QA AUTOMATION API

*  `docker pull turkuazsengul/ms-api-qa-automation`

##DOCKER RUN ON SAME NETWORK

* `docker container run --publish 8888:8888 --detach --name configuration --network ms-network turkuazsengul/ms-node-configuration:latest`
* `docker container run --publish 5555:5555 --detach --name eureka --network ms-network turkuazsengul/ms-node-discovery:latest`
* `docker container run --publish 8080:8080 --detach --name apigateway --network ms-network turkuazsengul/ms-api-gateway:latest`
* `docker container run --publish 20000:20000 --detach --name api-product --network ms-network turkuazsengul/ms-api-product:latest`
* `docker container run --publish 10000:10000 --detach --name api-order --network ms-network turkuazsengul/ms-api-order:latest`
*  `docker container run --publish 40000:40000 --detach --name api-user --network ms-network turkuazsengul/ms-api-user:latest`
*  `docker container run --publish 50000:50000 --detach --name api-mail --network ms-network turkuazsengul/ms-api-mail:latest`
*  `docker container run --publish 8282:8282 --detach --name ms-api-qa-automation --network ms-network turkuazsengul/ms-api-qa-automation:latest`
