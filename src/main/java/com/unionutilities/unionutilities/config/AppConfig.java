package com.unionutilities.unionutilities.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.unionutilities.unionutilities.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@AllArgsConstructor
@Slf4j
public class AppConfig implements ApplicationRunner {

    private final FileService fileService;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setupApp();
    }

    private void setupApp() throws IOException {
        setupStorage();
    }

    private void setupStorage() throws IOException {
        log.info("Setting up storage.");
        if(!fileService.createStorageFolder()){
            log.info("Storage directory not created, it probably already exists.");
        } else {
            log.info("Storage directory created.");
        }
        if(!fileService.createResultsFile()){
            log.info("Storage file not created, it probably already exists.");
        } else {
            log.info("Storage file created.");
        }
        log.info("Storage setup finished.");
    }

}
