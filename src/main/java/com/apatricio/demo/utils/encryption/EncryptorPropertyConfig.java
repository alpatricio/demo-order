package com.apatricio.demo.utils.encryption;

import com.apatricio.demo.utils.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(factory = YamlPropertySourceFactory.class ,value="classpath:encryptor.yml" )
public class EncryptorPropertyConfig {
}

