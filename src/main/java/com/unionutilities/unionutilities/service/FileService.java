package com.unionutilities.unionutilities.service;

import com.unionutilities.unionutilities.config.StorageConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class FileService {

    private final StorageConfig storageConfig;

    public void createStorageFolder() {
        new File(storageConfig.getFolderPath()).mkdirs();
    }

    public void createResultsFile() throws IOException {
        File resultsFile = new File(storageConfig.getFolderPath() +
                File.separator + storageConfig.getResultFilePath());
        resultsFile.createNewFile();
    }
}
