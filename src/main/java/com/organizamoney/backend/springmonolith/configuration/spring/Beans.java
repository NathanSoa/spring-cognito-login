package com.organizamoney.backend.springmonolith.configuration.spring;

import com.organizamoney.backend.springmonolith.domain.account.enterprise.policies.SignUpPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public SignUpPolicy signUpPolicy() {
        return new SignUpPolicy();
    }
}
