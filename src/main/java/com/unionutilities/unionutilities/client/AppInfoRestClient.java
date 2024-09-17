package com.unionutilities.unionutilities.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionutilities.unionutilities.model.AppInfoModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AppInfoRestClient {

    private OkHttpClient client;
    private ObjectMapper mapper;
    private AuthConfig authConfig;
    private FileService fileService;

    private final String LOG_ENDPOINT = "/api/v1/info/logs";

    public AppInfoModel getAppInfo(String url) {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Basic " + makeAuthHeaders())
                .url(url)
                .build();
        return makeApiCall(request);
    }

    private AppInfoModel makeApiCall(Request request) {
        try (Response response = client.newCall(request).execute()){
            log.info("Attempting call to {}", request.url());
            return mapper.readValue(Objects.requireNonNull(response.body()).byteStream(), AppInfoModel.class);
        } catch (IOException | NullPointerException e){
            log.error(e.getMessage());
            return AppInfoModel.error();
        }
    }

    private String makeAuthHeaders() {
        String plainCreds = authConfig.getUsername() + ":" + authConfig.getPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
        }

}
