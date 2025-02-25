package com.mealmate.backend.dto;

public record ImageInfo (
        String publicId,
        String secureUrl,
        String format
) {}
