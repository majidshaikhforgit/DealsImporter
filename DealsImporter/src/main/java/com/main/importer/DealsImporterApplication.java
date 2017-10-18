package com.main.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.main.*")
@EnableJpaRepositories("com.main.dao.jpa")
@EntityScan("com.main.dao.entity")
public class DealsImporterApplication {

  public static void main(String[] args) {
    SpringApplication.run(DealsImporterApplication.class, args);
  }
}
