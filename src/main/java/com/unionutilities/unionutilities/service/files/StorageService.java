package com.unionutilities.unionutilities.service.files;

import com.unionutilities.unionutilities.config.storage.StorageConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class StorageService {

    private final StorageConfig storageConfig;

    public Boolean createStorageFolder() {
        return new File(storageConfig.getFolderPath()).mkdirs();
    }

    public File createTempLogFile() throws IOException {
        String fileName = String.valueOf(System.currentTimeMillis());
        String filePath = storageConfig.getFolderPath() + File.separator + fileName + ".txt";
        File logFile = new File(filePath);
        logFile.createNewFile();
        return logFile;
    }

    public Boolean createFileInStorage(String fileName) throws IOException {
        return new File(storageConfig.getFolderPath() + File.separator + fileName).createNewFile();
    }

    public boolean deleteFileFromStorage(String fileName){
        return new File(storageConfig.getFolderPath() + File.separator + fileName).delete();
    }

    public File getFileFromStorage(String fileName) {
        return new File(storageConfig.getFolderPath() + File.separator + fileName);
    }

}
