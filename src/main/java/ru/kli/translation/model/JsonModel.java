package ru.kli.translation.model;

public class JsonModel {
    private String fromLang;
    private String toLang;
    private String text;

    public String getJsonFormat() {
        return "{" +
                "\"fromLang\":\"" +
                fromLang +
                "\"," +
                "\"toLang\":\"" +
                toLang +
                "\"," +
                "\"text\":\"" +
                text +
                "\"" +
                "}";
    }

    public String getFromLang() {
        return fromLang;
    }

    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }

    public String getToLang() {
        return toLang;
    }

    public void setToLang(String toLang) {
        this.toLang = toLang;
    }

    public String getWord() {
        return text;
    }

    public void setWord(String text) {
        this.text = text;
    }
}
