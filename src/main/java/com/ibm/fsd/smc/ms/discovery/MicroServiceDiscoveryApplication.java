package com.ibm.fsd.smc.ms.discovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceDiscoveryApplication.class, args);
	}

}