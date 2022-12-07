package com.niceurekaserver.NicEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//@LoadBalancerClients
public class NicEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicEurekaServerApplication.class, args);
	}

}
