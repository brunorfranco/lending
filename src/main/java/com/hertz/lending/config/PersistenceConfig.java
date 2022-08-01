package com.hertz.lending.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Enable JPA repositories. Will scan the repository folder for Spring Data
 * repositories.
 *
 * @author Bruno Franco
 *
 */
@EnableJpaRepositories(basePackages = "com.hertz.lending.repository")
public class PersistenceConfig {

}
