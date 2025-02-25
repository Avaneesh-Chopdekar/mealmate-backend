package com.mealmate.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig {

    private final CustomProperties customProperties;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", customProperties.getCloudinaryCloudName(),
                        "api_key", customProperties.getCloudinaryApiKey(),
                        "api_secret", customProperties.getCloudinaryApiSecret())
        );
    }
}
