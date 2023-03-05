package ru.kli.translation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.kli.translation.controller.SentenceController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonsService {
    private static final Logger LOGGER = Logger.getLogger(SentenceController.class);
    private JsonsService() {
    }

    /**
     * @param fromLang язык текста для перевода
     * @param toLang   язык переведенного текста
     * @param text     текст для перевода
     * @return подготовленный к отправке на перевод json
     */
    public static String jsonRequestGeneration(String fromLang, String toLang, String text) {
        return new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"")
                .append(fromLang)
                .append("\",")
                .append("\"toLang\":\"")
                .append(toLang)
                .append("\",")
                .append("\"text\":\"")
                .append(text)
                .append("\"")
                .append("}")
                .toString();
    }

        public static Map<String, String> jsonToMap(String json){
            try {
                Map<String, String> jMap = new ObjectMapper().readValue(json, LinkedHashMap.class);
                return jMap;
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                throw new RuntimeException(e);
            }
        }
    public static String jsonResponseGeneration(String ip, String fromLang, String toLang, List<String> words) {
        return new StringBuilder()
                .append("{")
                .append("\n")
                .append("\"ip\":\"")
                .append(ip)
                .append("\",")
                .append("\n")
                .append("\"fromLang\":\"")
                .append(fromLang)
                .append("\",")
                .append("\n")
                .append("\"toLang\":\"")
                .append(toLang)
                .append("\",")
                .append("\n")
                .append("\"text\":\"")
                .append(words.toString())
                .append("\"")
                .append("\n")
                .append("}")
                .toString();
    }
}
