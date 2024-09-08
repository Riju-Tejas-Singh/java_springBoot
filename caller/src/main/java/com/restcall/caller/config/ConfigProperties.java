package com.restcall.caller.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rest.caller")
public class ConfigProperties {
    private String host;
    private String port;
}
