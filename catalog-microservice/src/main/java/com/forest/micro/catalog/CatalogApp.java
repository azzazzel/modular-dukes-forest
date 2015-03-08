package com.forest.micro.catalog;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableJSONDoc
public class CatalogApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CatalogApp.class, args);
	}

}
