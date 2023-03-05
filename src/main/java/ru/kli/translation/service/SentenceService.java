package ru.kli.translation.service;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kli.translation.controller.SentenceController;
import ru.kli.translation.repo.SentenceRepo;


import java.io.*;
import java.net.HttpURLConnection;
import java.util.*;

@Service
public class SentenceService {
    private static final Logger LOGGER = Logger.getLogger(SentenceController.class);
    @Autowired
    private SentenceRepo sentenceRepo;

    public List<String> getTranslateSentence(String fromLang, String toLang, String text) {
        List<String> words = new ArrayList<>();
        List<String> translateWords = new ArrayList<>();
        words = TextConverter.convertorStringToWordList(text);
        for (String word : words) {
            translateWords.add(translate(fromLang, toLang, word));
        }
        return translateWords;
    }


    public String translate(String fromLang, String toLang, String text) {
//        System.out.println("Контрольный вывод "+fromLang +" "+ toLang +" "+ text);
        String jsonPayload = JsonsService.jsonRequestGeneration(fromLang, toLang, text);
        HttpURLConnection connection = ConnectionService.connectWithTranslator();

        OutputStream os = null;
        try {
            os = connection.getOutputStream();
            os.write(jsonPayload.getBytes());
            os.flush();

            BufferedReader rb = checkResponseCode(connection.getResponseCode(), connection);
            // TODO добавить логер
            StringBuilder builder = new StringBuilder();
            String sub;
            while (((sub = rb.readLine()) != null)) {
                builder.append(sub);
            }
            return builder.toString();
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //TODO обложить логами
    private String parserResponse(String response) {
        // TODO поменять тело метода на сбор json
        JSONParser jp = new JSONParser();
        JSONObject jsonObject;
        try {
            String result = "";

            jsonObject = (JSONObject) jp.parse(response);
            JSONArray text = (JSONArray) jsonObject.get("text");
            //TODO стрингбафер
            for (int i = 0; i < text.size(); i++) {
                String subRes = (String) text.get(i);
                result += subRes;
            }
            return result;
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private BufferedReader checkResponseCode(int responseCode, HttpURLConnection connection) throws IOException {
        return new BufferedReader(new InputStreamReader(
                (responseCode == 200) ? connection.getInputStream() : connection.getErrorStream()
        ));

    }

}

