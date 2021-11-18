package com.axbg.demo.services;

import org.springframework.web.multipart.MultipartFile;

public interface RecognitionService {
    String recognizeIngredient(MultipartFile image);
}
