package com.zhiran.gateway.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhiran.common.entity.SessionContext;
import com.zhiran.common.helper.RedisSessionHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {
    @Bean(name = "redisSessionTemplate")
    public RedisTemplate<String, SessionContext> getRedisTemplate(RedisConnectionFactory factory){
        //SessionContext保存这一些和用户和其使用设备的相关信息
        RedisTemplate<String,SessionContext> redisTemplate = new RedisTemplate<String,SessionContext>();
        //创建连接
        redisTemplate.setConnectionFactory(factory);
        //对redis进行key的String序列化操作
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        /**
         * 对值进行序列化
         */
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //设置能处理的值类型
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //设置默认备注 对反序列化起到关键作用
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
    @Bean
    public RedisSessionHelper redisSessionHelper (){
        return  new RedisSessionHelper();
    }


}
