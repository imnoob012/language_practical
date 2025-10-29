package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		// registry.addViewController("リクエストURL").setViewName("テンプレートファイル名");
		
		registry.addViewController("/").setViewName("index");
		
		registry.addViewController("/screen-b").setViewName("screen-b");
		
		registry.addViewController("/screen-c").setViewName("screen-c");
		
	}
}