package com.example.sessiondemo;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SpringBootApplication
//@EnableRedisHttpSession
@EnableWebSecurity
@RestController
public class SessionDemoApplication {

	@RequestMapping(value = "/", produces = "application/json")
	public Map<String, String> helloUser(Principal principal) {
		HashMap<String,String> ret = new HashMap<>();
		ret.put("username", principal.getName());
		return ret;
	}
	@RequestMapping(value = "/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Bean
	public HttpSessionIdResolver httpSessionIdResolver(){
		return HeaderHttpSessionIdResolver.xAuthToken();
	}

	@Bean
	public ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}



	public static void main(String[] args) {
		SpringApplication.run(SessionDemoApplication.class, args);
	}

}
