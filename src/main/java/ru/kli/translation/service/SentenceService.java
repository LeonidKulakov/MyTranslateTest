package ru.kli.translation.service;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kli.translation.entity.Sentence;
import ru.kli.translation.entity.Word;
import ru.kli.translation.repo.SentenceRepo;
import ru.kli.translation.repo.WordRepo;


import java.util.*;


@Service
public class SentenceService {

    @Autowired
    private SentenceRepo sentenceRepo;
    @Autowired
    private WordRepo wordRepo;

    public String getTranslate(HttpServletRequest request) {
        Map<String, String> jMap = JsonsService.requestToMap(request);
        TextConverter converter = new TextConverter(jMap);
        List<String> words = converter.getTranslateSentence();
        String output = String.join(" ", words);
        Sentence sentence = saveSentence(jMap, String.join(" ", words));
        saveWord(words, sentence);
        return JsonsService.parserMapToJson(new LinkedHashMap<>(Map.of(
                "ip", jMap.get("ip"),
                "output", output
        )));
    }

    private Sentence saveSentence(Map<String, String> jMap, String output) {
        Date date = Calendar.getInstance().getTime();
        Sentence sentence = new Sentence();
        sentence.setDate(date);
        sentence.setIp(jMap.get("ip"));
        sentence.setIp(jMap.get("lang"));
        sentence.setInput(jMap.get(jMap.get("input")));
        sentence.setOutput(output);
        sentenceRepo.save(sentence);
        return sentence;
    }

    private void saveWord(List<String> words, Sentence sentence) {
        Word wordEntity = new Word();
        for (String word : words) {
            wordEntity.setWord(word);
            wordEntity.setSentence(sentence);
            wordRepo.save(wordEntity);
        }

    }
}

