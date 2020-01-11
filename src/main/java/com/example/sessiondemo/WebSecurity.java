package com.example.sessiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(jdbcUserDetailsManager()).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        jdbcUserDetailsManager.setUserExistsSql("select username from users where username = ?");
        jdbcUserDetailsManager.setCreateUserSql("insert into users (username, password, role) values (?,?,?)");
        jdbcUserDetailsManager.setCreateAuthoritySql("insert into users (username, role) values (?,?)");
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    private static final String[] PUBLIC_PAGES = new String[]{
            "/",
            "signup",
            "/error",
            "/contact",
            "/login/**",
            "account_confirmation",
            "password/**",
            "/public/**",
            "css/**",
            "js/**",
            "vendor/**"
    };

//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/user/signup", "/print").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username, password, true"
                + " from users where username = ?")
        .authoritiesByUsernameQuery("select username, password, role"
                + " from users where username = ?");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(currentUserDetailService).passwordEncoder(passwordEncoder);
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(User.withUsername("user")
//                        .password(passwordEncoder().encode("pass"))
//                        .roles("USER"));
//    }
}
