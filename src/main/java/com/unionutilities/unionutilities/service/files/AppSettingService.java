package com.unionutilities.unionutilities.service.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionutilities.unionutilities.config.storage.FileConfig;
import com.unionutilities.unionutilities.model.settings.AppSettings;
import com.unionutilities.unionutilities.service.JsonHelper;
import com.unionutilities.unionutilities.web.requests.AddTargetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppSettingService implements FileService {

    private final FileConfig fileConfig;
    private final StorageService storageService;
    private final ObjectMapper objectMapper;
    private AppSettings appSettings = new AppSettings();

    @Override
    public Boolean setupBaseFileOrDirectory() throws IOException {
        createSettingsFile();
        if(getSettingsFile().length() != 0){
            return false;
        }
        AppSettings appSettings = new AppSettings();
        JsonHelper.writeObjectToFile(getSettingsFile().toPath(), appSettings);
        return true;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void loadAppConfig() throws IOException {
        log.info("Loading application config from file.");
        this.appSettings = objectMapper.readValue(getSettingsFile(), AppSettings.class);
    }

    public Set<String> getTargets() {
        return this.appSettings.getTargets();
    }

    public Boolean createSettingsFile() throws IOException {
        return storageService.createFileInStorage(fileConfig.getSettingsFilePath());
    }

    public File getSettingsFile() {
        return storageService.getFileFromStorage(fileConfig.getSettingsFilePath());
    }

    public void addTarget(AddTargetRequest addTargetRequest) throws IOException {
        this.appSettings.getTargets().add(addTargetRequest.getTarget());
        updateAppSettings();
    }

    private void updateAppSettings() throws IOException {
        JsonHelper.writeObjectToFile(getSettingsFile().toPath(), this.appSettings);
    }

}
