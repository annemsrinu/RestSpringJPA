/**
 * 
 */

package com.learner.controller;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DELL
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.learner" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class MainApp {
		
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("EmployeeREST API Documentation", "Employee API Documentation", "1.0", "urn:tos",
				new springfox.documentation.service.Contact("Srini", "http://learnerBlog.com", "sriniannem@gmail.com"),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.learner")).paths(PathSelectors.any()).build();
	}
	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {
		/*
		 * LocalEntityManagerFactoryBean factoryBean= (LocalEntityManagerFactoryBean)
		 * Persistence.createEntityManagerFactory("capg");
		 */
		
		LocalEntityManagerFactoryBean factoryBean=
				new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("learner");
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager getTransactionManager() {
		JpaTransactionManager transactionManager=
				new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
		return transactionManager;
	}
	

}
