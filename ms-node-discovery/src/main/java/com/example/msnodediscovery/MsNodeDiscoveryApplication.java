package com.example.msnodediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsNodeDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNodeDiscoveryApplication.class, args);
	}

}
