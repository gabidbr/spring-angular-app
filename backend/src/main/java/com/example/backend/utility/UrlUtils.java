package com.example.backend.utility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.backend.constant.FileConstant.DEFAULT_USER_IMAGE_PATH;

public class UrlUtils {
    public static String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }
}
