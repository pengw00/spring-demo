package com.example.sessiondemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public LettuceConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("ec2-54-164-134-74.compute-1.amazonaws.com", 13909);
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("localhost", 6379);
//        redisConfig.setPassword("p532e83d7ea3dfab049eede085fe1d12932ac2c5a20a8299005c8d67f9bade67f");
        redisConfig.setPassword("88888888");

        return new LettuceConnectionFactory(redisConfig);
    }

}