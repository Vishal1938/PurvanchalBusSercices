package com.rbubus.vendorapi.vendor_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.rbubus.vendorapi.vendor_api.domain")
@EnableJpaRepositories("com.rbubus.vendorapi.vendor_api.repos")
@EnableTransactionManagement
public class DomainConfig {
}
