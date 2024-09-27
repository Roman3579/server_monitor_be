package com.unionutilities.unionutilities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class JsonHelper {

    public static void writeObjectToFile(Path filePath, Object object) throws IOException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(object);
        Files.write(filePath, Collections.singleton(json), StandardCharsets.UTF_8);
    }


}
