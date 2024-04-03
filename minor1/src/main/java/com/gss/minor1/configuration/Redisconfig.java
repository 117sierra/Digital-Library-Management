package com.gss.minor1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class Redisconfig {
    @Bean
    public LettuceConnectionFactory getletconnection(){
        RedisStandaloneConfiguration redisStandaloneConfiguration= new RedisStandaloneConfiguration();   // default setting therefore dont need to pass any hostname or password or port in the argument
//        redisStandaloneConfiguration.setPassword(); for setting up password constructor doesnt have the password method
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate getredistemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setConnectionFactory(getletconnection());
        return template;
    }
}
