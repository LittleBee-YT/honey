package com.lbee.honey.auth.config.prop;

import com.lbee.honey.auth.config.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Configuration
@PropertySource(value = {"classpath:security-whitelist.yml"}, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "security")
public class SecurityWhitelistProp {
    private List<String> whiteList;
}
