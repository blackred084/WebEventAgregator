package pl.sda.eventsagregator.configurers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Value("${spring.datasource.username}")
    String datasourceUsername;

    @Value("${spring.datasource.password}")
    String datasourcePassword;

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    String datasourceDriver;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String automaticSchemaCreation;

    @Value("${spring.jpa.generate-ddl}")
    String automaticDdlCreation;

    @Value("${spring.datasource.initialization-mode}")
    String automaticDatasourceInitialization;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("pl.sda.eventsagregator.entities");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(datasourceDriver);
        dataSourceBuilder.url(datasourceUrl);
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);

        DataSource dataSource = dataSourceBuilder.build();

        Resource initSchema = new ClassPathResource("/scripts/schema-mysql.sql");
//        Resource initData = new ClassPathResource("/scripts/data-mysql.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", automaticSchemaCreation); // drop table on start
        properties.setProperty("spring.jpa.generate-ddl", automaticDdlCreation); // drop table on start
        properties.setProperty("spring.datasource.initialization-mode", automaticDatasourceInitialization); // drop table on start
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }


}