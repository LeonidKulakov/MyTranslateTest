package ru.kli.translation.type;

public enum ConnectionData {
    CLIENT_ID("FREE_TRIAL_ACCOUNT"),
    CLIENT_SECRET("PUBLIC_SECRET"),
    URL("http://api.whatsmate.net/v1/translation/translate");
    private String info;

    ConnectionData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
