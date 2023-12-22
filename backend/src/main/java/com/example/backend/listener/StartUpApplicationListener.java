package com.example.backend.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.example.backend.constant.FileConstant.USER_FOLDER;

@Component
public class StartUpApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("Application started creating the user folder directories");
        new File(USER_FOLDER).mkdirs();
    }
}
