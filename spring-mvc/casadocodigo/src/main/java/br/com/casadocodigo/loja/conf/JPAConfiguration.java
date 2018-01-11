package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		//abstracao do arquivo persistence.xml
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		// implementacao do JPA
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		em.setJpaVendorAdapter(vendorAdapter);
		
		em.setDataSource(dataSource());
		
		em.setPackagesToScan(new String[] {"br.com.casadocodigo.loja.models"});
		
		
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
	}
}
