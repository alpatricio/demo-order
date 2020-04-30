package com.apatricio.demo.restclient.config;

import com.apatricio.demo.utils.encryption.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiBeanConfig {

    @Autowired
    private Encryptor encryptor;

    @Autowired
    private DistanceApiConfig distanceApiConfig;

    public String getDistanceApiKey() {
        return encryptor.decrypt(distanceApiConfig.getKey());
    }
}
