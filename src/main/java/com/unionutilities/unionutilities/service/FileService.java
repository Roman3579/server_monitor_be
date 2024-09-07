package com.unionutilities.unionutilities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unionutilities.unionutilities.config.StorageConfig;
import com.unionutilities.unionutilities.model.ApiCallResult;
import com.unionutilities.unionutilities.model.ApiCallResultContainer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

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

    public void writeResultsToFile(List<ApiCallResult> apiCallResults) throws IOException {
        ApiCallResultContainer container = new ApiCallResultContainer(apiCallResults);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(container);
        Files.write(getResultsFile().toPath(), Collections.singleton(json), StandardCharsets.UTF_8);
    }

    public ApiCallResultContainer getStoredResults() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getResultsFile(), ApiCallResultContainer.class);
    }

    private File getResultsFile() {
        return new File(storageConfig.getFolderPath() + File.separator + storageConfig.getResultFilePath());
    }

}
