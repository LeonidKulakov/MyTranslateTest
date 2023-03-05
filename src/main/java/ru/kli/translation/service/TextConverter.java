package ru.kli.translation.service;

import java.util.List;

public class TextConverter {
    public static final String MANY_SPACES = "\\s+";
    public static final String REGEX = "\\pP";
    private TextConverter() {
    }

    private static String deletePunctuationMarks(String  text) {
        return text.replaceAll(REGEX, " ").replaceAll(MANY_SPACES, " ");
    }
    public static List<String> convertorStringToWordList(String  text) {
        String s = deletePunctuationMarks(text);
        List<String> wordList = List.of(s.split(" "));
        return wordList;
    }
}
