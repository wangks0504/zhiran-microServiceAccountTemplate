package com.zhiran.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhiran.common.entity.SessionContext;
import com.zhiran.common.helper.RedisSessionHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate getRedisTemplate (RedisConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
    @Bean(name = "redisSessionTemplate")
    public RedisTemplate<String, SessionContext> getStringSessionContextRedisTemplate (RedisConnectionFactory factory){
        RedisTemplate<String,SessionContext> stringSessionContextRedisTemplate = new RedisTemplate<String,SessionContext>();
        stringSessionContextRedisTemplate.setConnectionFactory(factory);
        //key的序列化
        stringSessionContextRedisTemplate.setKeySerializer(new StringRedisSerializer());
        //值的序列化SessionContext是自定义的所以需要jackson配置一下
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        stringSessionContextRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return stringSessionContextRedisTemplate;
    }

    @Bean
    public RedisSessionHelper getRedisSessionHelper (){
        return new RedisSessionHelper();
    }

}
