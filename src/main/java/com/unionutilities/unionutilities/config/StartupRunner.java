package com.unionutilities.unionutilities.config;

import com.unionutilities.unionutilities.service.files.FileService;
import com.unionutilities.unionutilities.service.files.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final StorageService storageService;
    private final List<FileService> fileServices;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setupApp();
    }

    private void setupApp() throws IOException {
        setupFilesAndDirectories();
    }

    private void setupFilesAndDirectories() throws IOException {
        storageService.createStorageFolder();
        for(FileService fileService : fileServices){
            fileService.setupBaseFileOrDirectory();
        }
    }

}
