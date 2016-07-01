package com.altari.spring.ws.configuration;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.EternalExpiryPolicy;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

/**
 * 
 * @Régis LIMARE
 *
 */
@Component
public class CachingSetup implements JCacheManagerCustomizer {
    /**
     * cache eternel pour les objets de référence
     */
    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache("pays", new MutableConfiguration<>()
                .setExpiryPolicyFactory(EternalExpiryPolicy.factoryOf()));
        cacheManager.createCache("civilite", new MutableConfiguration<>()
                .setExpiryPolicyFactory(EternalExpiryPolicy.factoryOf()));
    }
}
