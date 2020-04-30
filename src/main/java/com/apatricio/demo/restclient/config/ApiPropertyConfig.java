package com.apatricio.demo.restclient.config;

import com.apatricio.demo.utils.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(factory = YamlPropertySourceFactory.class ,value="classpath:api-config.yml" )
public class ApiPropertyConfig {
}
