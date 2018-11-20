package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String arsg[]) {
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
