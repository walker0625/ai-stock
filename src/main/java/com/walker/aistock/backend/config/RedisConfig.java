package com.walker.aistock.backend.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableCaching
public class RedisConfig {

    // TODO yml 설정 적용 안되는 이슈 해결되면 yml로 통일(yml로는 ssl 설정이 적용되지 못함)
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("j2025-redis-ejj1pd.serverless.apn2.cache.amazonaws.com");
        config.setPort(6379);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
            .useSsl()  // 중요: SSL 활성화(엘라스틱 캐시는 tsl/ssl 연결만 가능!)
            .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // LocalDate 등 직렬화에 필요(+ 의존성 추가)
        objectMapper.activateDefaultTyping( // 직렬화 할때 타입 정보를 json에 포함(역직렬화에 필요) : 위치나 이름이 바뀌면 Cache 갱신 필요
            objectMapper.getPolymorphicTypeValidator(),
            ObjectMapper.DefaultTyping.EVERYTHING,
            JsonTypeInfo.As.WRAPPER_OBJECT
        );
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                                                                         .entryTtl(Duration.ofDays(1))
                                                                         .disableCachingNullValues()
                                                                         .serializeValuesWith(
                                                                             RedisSerializationContext.SerializationPair.fromSerializer(
                                                                                 jackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                                .cacheDefaults(defaultCacheConfig)
                                .withCacheConfiguration("investingPrinciple", defaultCacheConfig.entryTtl(Duration.ZERO))
                                .build();
    }

}