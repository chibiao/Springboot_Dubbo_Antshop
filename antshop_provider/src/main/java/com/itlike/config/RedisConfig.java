package com.itlike.config;

import com.itlike.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Auther: 梦学谷
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, User> jsonRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer(User.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
