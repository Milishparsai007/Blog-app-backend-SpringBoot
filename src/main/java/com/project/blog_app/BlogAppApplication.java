package com.project.blog_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	//model mapper :- it is used to map object of one class/model to another model/class.
	//@Bean annotation creates an object of referred class without manually creating it.
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
