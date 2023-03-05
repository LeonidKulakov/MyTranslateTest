package ru.kli.translation.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import ru.kli.translation.type.ConnectionData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class);
    private static final String X_WM_CLIENT_ID = "X-WM-CLIENT-ID";
    private static final String X_WM_CLIENT_SECRET = "X-WM-CLIENT-SECRET";
    private static final String POST = "POST";
    private static final String CONTENT_TYPE = "Content-Type";

    private ConnectionService() {
    }

    /**
     * @return возвращает соединение с API переводчика
     */
    public static HttpURLConnection connectWithTranslator() {
        URL url = null;
        try {
            url = new URL(ConnectionData.URL.getInfo());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(POST);
            connection.setRequestProperty(X_WM_CLIENT_ID, ConnectionData.CLIENT_ID.getInfo());
            connection.setRequestProperty(X_WM_CLIENT_SECRET, ConnectionData.CLIENT_SECRET.getInfo());
            connection.setRequestProperty(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return connection;
        } catch (MalformedURLException e) {
            LOGGER.log(Level.ERROR, "ConnectionService URL Error ",e);
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            LOGGER.log(Level.ERROR, "ConnectionService ",e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "ConnectionService ", e);
            throw new RuntimeException(e);
        }
    }
}
