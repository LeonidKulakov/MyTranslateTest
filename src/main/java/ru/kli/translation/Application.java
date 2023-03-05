package ru.kli.translation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kli.translation.controller.SentenceController;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = Logger.getLogger(SentenceController.class);
    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Старт");
       SpringApplication.run(Application.class, args);
    }
}
