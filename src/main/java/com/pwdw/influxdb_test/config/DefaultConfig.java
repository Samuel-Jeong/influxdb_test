package com.pwdw.influxdb_test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "default")
@Data
public class DefaultConfig {

    private String id = null;
    private String url = null;
    private char[] token = null;
    private String organization = null;
    private String bucket = null;


}
