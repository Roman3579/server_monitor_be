package com.unionutilities.unionutilities.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionutilities.unionutilities.config.AuthConfig;
import com.unionutilities.unionutilities.model.AppInfoModel;
import com.unionutilities.unionutilities.service.files.StorageService;
import com.unionutilities.unionutilities.throwable.ConnectionFailedException;
import com.unionutilities.unionutilities.throwable.NotFoundException;
import com.unionutilities.unionutilities.throwable.UnknownApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AppInfoRestClient {

    private OkHttpClient client;
    private ObjectMapper mapper;
    private AuthConfig authConfig;
    private StorageService storageService;

    private final String LOG_ENDPOINT = "/api/v1/info/logs";
    private final String INFO_UPDATE_ENDPOINT = "/api/v1/info";

    public AppInfoModel getAppInfo(String url) throws ConnectionFailedException, UnknownApiException, NotFoundException {
        Request request = buildBasicRequest(url).build();
        return makeApiCall(request);
    }

    public Resource getAppLogs(String url) {
        try {
            File tempLogFile = storageService.createTempLogFile();
            downloadLogsToFile(url, tempLogFile);
            return new FileSystemResource(tempLogFile);
        } catch (MalformedURLException e) {
            log.info("Failed to parse URL.");
            return null;
        } catch (IOException e) {
            log.info("Failed to write to file.");
            return null;
        }
    }

    public Boolean updateAppInfo(String targetUrl, AppInfoModel appInfoModel) throws JsonProcessingException {
        Request request = buildBasicRequest(targetUrl + INFO_UPDATE_ENDPOINT)
                .put(buildRequestBody(appInfoModel))
                .build();
        try(Response response = client.newCall(request).execute()){
            log.info("Info at {} updated successfully.", targetUrl);
            return true;
        } catch (IOException e){
            log.info("Failed to update app info: {}", e.getMessage());
            return false;
        }
    }

    private RequestBody buildRequestBody(AppInfoModel model) throws JsonProcessingException {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String appInfoJson = mapper.writeValueAsString(model);
        return RequestBody.create(appInfoJson, json);
    }

    private void downloadLogsToFile(String url, File targetFile) throws IOException {
        URL completeUrl = new URL(url + LOG_ENDPOINT);
        URLConnection urlConnection = completeUrl.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + makeAuthHeaders());
        IOUtils.copy(urlConnection.getInputStream(), Files.newOutputStream(targetFile.toPath()));
    }

    private AppInfoModel makeApiCall(Request request) throws ConnectionFailedException, UnknownApiException, NotFoundException {
        log.info("Attempting call to {}", request.url());
        try (Response response = client.newCall(request).execute()){
            return processResponse(response);
        } catch (ConnectException e) {
            log.info("Failed to connect to {}", request.url());
            throw new ConnectionFailedException();
        } catch (IOException | NullPointerException e){
            log.error("Unknown error when retrieving app info from {} : {}", request.url(), e.getMessage());
            throw new UnknownApiException();
        }
    }

    private AppInfoModel processResponse(Response response) throws IOException, NotFoundException, UnknownApiException {
        int responseCode = response.code();
        AppInfoModel result;
        switch (responseCode){
            case 200:
                result = mapper.readValue(Objects.requireNonNull(response.body()).byteStream(), AppInfoModel.class);
                break;
            case 404:
                throw new NotFoundException();
            default:
                throw new UnknownApiException();
        }
        return result;
    }

    private String makeAuthHeaders() {
        String plainCreds = authConfig.getUsername() + ":" + authConfig.getPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }

    private Request.Builder buildBasicRequest(String url) {
        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Basic " + makeAuthHeaders());
    }

}
