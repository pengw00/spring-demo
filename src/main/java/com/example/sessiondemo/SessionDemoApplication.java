package com.example.sessiondemo;

import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.example.sessiondemo.entity.User;
import com.example.sessiondemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@SpringBootApplication
//@EnableRedisHttpSession
@EnableWebSecurity
@RestController
public class SessionDemoApplication {

	@Autowired
	UserService userService;

	@Autowired
	DataSource dataSource;



	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@RequestMapping(value = "/", produces = "application/json")
	public Map<String,String> helloUser(Principal principal) {
		HashMap<String,String> ret = new HashMap<>();
		ret.put("username", principal.getName());
//		User user = userService.getUserByName("david");
		return ret;
	}

	@RequestMapping(value = "/print", produces = "application/json")
	public String getPrincipal(Principal principal){
		return principal.getName();
	}

	@RequestMapping(value = "/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session) {
		session.invalidate();
	}

//	@RequestMapping(value = "/create")
//	public User create(User user){
//		userService.create(user);
//		return userService.getUserByName(user.getUsername());
//	}
//		@GetMapping(value = "/user/{username}")
//		public String checkIfUserExists(@PathVariable("username") String username)
//		{
//		boolean flag = jdbcUserDetailsManager.userExists(username);
//		if (flag)
//			return "\"" + username + "\" exist in Database";
//		else
//			return "\"" + username + "\" does not exist in Database";
//		}

	@RequestMapping(value="/user/signup", produces = "application/json")
	public String createUser() throws SQLException {
//		User user = new User();

//		userService.create(user);
//		jdbcUserDetailsManager.createUser((UserDetails) user);
//				User (username).password(passwordEncoder.encode(password)).roles("USER").build());
		jdbcUserDetailsManager.setDataSource(dataSource);
//		dataSource.getConnection("postgres", "88888888");
		jdbcUserDetailsManager.setUserExistsSql("select username from users where username = ?");
		jdbcUserDetailsManager.setCreateUserSql("insert into users values('data', 'dfdsf', 'ddf')");
		jdbcUserDetailsManager.loadUserByUsername("danno");
		Boolean ret = jdbcUserDetailsManager.userExists("danno");
		return ret == true?"danno exsit":"user not exist";
//		return user.getEmail();
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
