package com.example.sessiondemo;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableWebSecurity
@EnableRedisHttpSession
@RestController
public class SessionDemoApplication {

	@GetMapping("/name")
	public String getName(Principal principal) {
		return principal.getName();
	}


	public static void main(String[] args) {
		SpringApplication.run(SessionDemoApplication.class, args);
	}

}
