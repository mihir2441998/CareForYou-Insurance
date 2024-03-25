package com.careforyou.claimsservice.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("com.careforyou.claimsservice.claims.repository")
//@EnableJpaRepositories("com.careforyou.claimsservice.claims.repository")
//@EntityScan(basePackages = "com.careforyou.claimsservice.claims.entities")
@SpringBootApplication
public class ClaimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimsApplication.class, args);
	}

}
