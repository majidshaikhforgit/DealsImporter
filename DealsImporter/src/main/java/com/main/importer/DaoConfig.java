//package com.main.importer;
//import com.main.dao.entity.Deal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
//@ComponentScan("com.main.*")
//@EnableJpaRepositories("com.main.dao.jpa")
//@EntityScan("com.main.dao.entity")
//public class DaoConfig {
//
//  @Autowired
//  private Environment env;
//
//  @Bean
//  public DataSource dataSource() {
//    BasicDataSource dataSource = new BasicDataSource();
//    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//    dataSource.setUrl(env.getProperty("spring.datasource.url"));
//    dataSource.setUsername(env.getProperty("spring.datasource.username"));
//    dataSource.setPassword(env.getProperty("spring.datasource.password"));
//    return dataSource;
//  }
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setDatabase(Database.MYSQL);
//    vendorAdapter.setGenerateDdl(false);
//
//    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//    factory.setJpaVendorAdapter(vendorAdapter);
//    factory.setPackagesToScan(getClass().getPackage().getName());
//    factory.setDataSource(dataSource());
//
//    return factory;
//  }
//
//  @Bean
//  public Properties hibernateProperties() {
//    Properties properties = new Properties();
//    properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
//    properties.setProperty("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
//    properties.setProperty("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//    return properties;
//  }
//
//  @Bean
//  public LocalSessionFactoryBean sessionFactory() {
//    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//    sessionFactory.setDataSource(dataSource());
//    sessionFactory.setPackagesToScan("com.main.dao.entity");
//    sessionFactory.setHibernateProperties(hibernateProperties());
//    return sessionFactory;
//  }
//}
