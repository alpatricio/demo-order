package com.apatricio.demo.utils.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("encryptor")
public class EncryptorBeanConfig {

    @Autowired
    private EncryptorConfig encryptorConfig;

    @Bean
    public Encryptor encryptor() {
        return new Encryptor(encryptorConfig.getSecretKey());
    }

}
