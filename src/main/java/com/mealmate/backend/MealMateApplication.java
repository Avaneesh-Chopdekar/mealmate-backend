package com.mealmate.backend;

import com.mealmate.backend.service.ImageUploadService;
import com.mealmate.backend.utils.HelperSingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MealMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealMateApplication.class, args);
	}

	@Autowired
	public void configureService(ImageUploadService imageUploadService) {
		HelperSingletonService.setImageUploadService(imageUploadService);
	}
}
