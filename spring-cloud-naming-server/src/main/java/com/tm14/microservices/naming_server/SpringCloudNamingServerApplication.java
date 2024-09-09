package com.tm14.microservices.naming_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudNamingServerApplication.class, args);
	}
}
