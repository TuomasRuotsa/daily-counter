package bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String arsg[]) {

        // TODO add init steps

        System.out.println("Starting dailycounter");

        System.out.println("Initializing ekaState");

        //Instant instant = Instant.now();
        //long timeStampMillis = instant.toEpochMilli();

        EkaState state = new EkaState();
        state.setEka(Instant.now());
        Participant p1 = new Participant("Henri", 0);
        Participant p2 = new Participant("Tiia", 1);

        state.addParticipant(p1);
        state.addParticipant(p2);


        Gson g = new Gson();
        String jsonString = g.toJson(state);

        try (PrintWriter out = new PrintWriter("test.json")) {
            out.println(jsonString);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create file");
        }

        System.out.println("Starting bot");
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new DailyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
