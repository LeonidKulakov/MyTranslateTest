package ru.kli.translation.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kli.translation.entity.Sentence;
import ru.kli.translation.repo.SentenceRepo;
import ru.kli.translation.service.JsonsService;
import ru.kli.translation.service.SentenceService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sentence")
public class SentenceController {
    private static final Logger LOGGER = Logger.getLogger(SentenceController.class);
    @Autowired
    private SentenceService service;
    @Autowired
    private SentenceRepo sentenceRepo;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RequestMapping("/")
    @ResponseBody
    public String getSentence(HttpServletRequest request) {
        String json = null;
        try {
            LOGGER.log(Level.INFO, "Запрос");
            json = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            Map<String, String> jMap = JsonsService.jsonToMap(json);
            List<String> translateWords = service.getTranslateSentence(
                    jMap.get("fromLang"),
                    jMap.get("toLang"),
                    jMap.get("text")
            );
            // TODO Достать из json (Переделать БД под задание)
            Sentence sentence = new Sentence();
//            sentence.setIp(jMap.get("ip"));
//            sentence.setInput();
//            sentence.setOutput();
            sentenceRepo.save(sentence);

            // ВАЖНО! Достает связку
//            sentence.getId();
            return JsonsService.jsonResponseGeneration(
                    jMap.get("ip"),
                    jMap.get("fromLang"),
                    jMap.get("toLang"),
                    translateWords
            );
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
