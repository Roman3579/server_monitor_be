package com.unionutilities.unionutilities.service.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionutilities.unionutilities.config.storage.FileConfig;
import com.unionutilities.unionutilities.model.ApiCallResult;
import com.unionutilities.unionutilities.model.ApiCallResultContainer;
import com.unionutilities.unionutilities.service.JsonHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultsService implements FileService {

    private final StorageService storageService;
    private final FileConfig fileConfig;

    @Override
    public Boolean setupBaseFileOrDirectory() throws IOException {
        return createResultsFile();
    }

    public Boolean createResultsFile() throws IOException {
        return storageService.createFileInStorage(fileConfig.getResultFilePath());
    }

    public File getResultsFile() {
        return storageService.getFileFromStorage(fileConfig.getResultFilePath());
    }

    public ApiCallResultContainer getStoredResults() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getResultsFile(), ApiCallResultContainer.class);
    }

    public void writeResultsToFile(List<ApiCallResult> apiCallResults) throws IOException {
        ApiCallResultContainer container = new ApiCallResultContainer(apiCallResults);
        JsonHelper.writeObjectToFile(getResultsFile().toPath(), container);
    }
}
