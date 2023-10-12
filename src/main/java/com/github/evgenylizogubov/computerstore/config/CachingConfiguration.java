package com.github.evgenylizogubov.computerstore.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfiguration {
    public final static String CACHE_RESOLVER_NAME = "simpleCacheResolver";
    
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(500, TimeUnit.SECONDS)
                .initialCapacity(200);
    }
    
    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
    
    @Bean(CACHE_RESOLVER_NAME)
    public CacheResolver cacheResolver(CacheManager cacheManager) {
        return new RuntimeCacheResolver(cacheManager);
    }
}
