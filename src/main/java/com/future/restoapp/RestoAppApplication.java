package com.future.restoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoAppApplication.class, args);
	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//		// Register resource handler for images
//		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
//				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//	}

}
