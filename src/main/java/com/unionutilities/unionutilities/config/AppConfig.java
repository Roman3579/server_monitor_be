package com.unionutilities.unionutilities.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionutilities.unionutilities.service.files.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
@AllArgsConstructor
@Slf4j
public class AppConfig implements ApplicationRunner {

    private final List<FileService> fileServices;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
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
        setupFilesAndDirectories();
    }

    private void setupFilesAndDirectories() throws IOException {
        for(FileService fileService : fileServices){
            fileService.setupBaseFileOrDirectory();
        }
    }

}
