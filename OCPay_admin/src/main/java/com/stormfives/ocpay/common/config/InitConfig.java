package com.stormfives.ocpay.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by y on 2018/3/22.
 */
@Configuration
public class InitConfig {

    @Value("${aes.deskey}")
    public String deskey;
}
