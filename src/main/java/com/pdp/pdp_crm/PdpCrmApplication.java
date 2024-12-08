package com.pdp.pdp_crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PdpCrmApplication {
    /**
     * Main
     */
    public static void main(String[] args) {
        SpringApplication.run(PdpCrmApplication.class, args);
    }
}