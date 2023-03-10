package ru.kli.translation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonsService {
    private static final Logger LOGGER = Logger.getLogger(JsonsService.class);

    private JsonsService() {
    }

    public static Map<String, String> requestToMap(HttpServletRequest request) {
        try {
            String json = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            Map<String, String> jMap = new ObjectMapper().readValue(json, HashMap.class);
            return jMap;
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parserMapToJson(Map<String, String> jMap) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(jMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
