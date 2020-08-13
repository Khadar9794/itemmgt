package com.dxctraining.config;

import com.sun.org.slf4j.internal.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.dxctraining")
@EnableTransactionManagement
@PropertySource("classpath:orm.properties")
public class JavaConfig {
    private static com.sun.org.slf4j.internal.Logger Log = LoggerFactory.getLogger(JavaConfig.class);

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.dxctraining");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(ormProperties());
        return em;
    }


    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            Environment environment = applicationContext.getEnvironment();
            String driver = environment.getProperty("db.driver");
            dataSource.setDriverClass(driver);
            String url = environment.getProperty("db.url");
            dataSource.setJdbcUrl(url);
            String user = environment.getProperty("db.user");
            dataSource.setUser(user);
            String password = environment.getProperty("db.password");
            dataSource.setPassword(password);
            return dataSource;
        } catch (Throwable e) {
            Log.error("exception in dataSource()", e);
            return null;
        }
    }

    Properties ormProperties() {
        Properties properties = new Properties();
        Environment environment = applicationContext.getEnvironment();
        String dialect = environment.getProperty("hibernate.dialect");
        String ddlAuto = environment.getProperty("hibernate.hbm2ddl.auto");
        String showSql = environment.getProperty("hibernate.show_sql");
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.show_sql", showSql);
        //  properties.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


}