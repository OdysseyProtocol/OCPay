package com.odwallet.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class InitConfig {

    @Value("${aes.deskey}")
    public String deskey;




}
