package bot;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

import static bot.Commands.handleCommand;
import static bot.Tools.readEkaFile;

public class DailyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        EkaState state = readEkaState();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            checkAndUpdateEka(update, state);

            if (isBotCommand(update)) {

                String response = handleCommand(update);
                sendMessage(update, response);
            }
        }
    }

    private boolean isBotCommand(Update update) {
        return update.getMessage().getText().charAt(0) == '/';
    }

    private void sendMessage(Update update, String msg) {
        if (msg == "" || msg == null) {
            msg = "Something went wrong. Please contact sysadmin";
        }
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(msg);
        //.setText(update.getMessage().getText());
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private EkaState readEkaState() {
        String ekaFile = readEkaFile();
        if (ekaFile == "") {
            // TODO problem with the file, handle!
        }

        // TODO create proper class etc. for serialization and deserialization stuff
        Gson gson = new Gson();
        EkaState state = gson.fromJson(ekaFile, EkaState.class);
        return state;
    }

    private boolean checkAndUpdateEka(Update update, EkaState state) {

        return false;
    }

    private boolean hasDayChangedBetween(Instant first, Instant second) {
        return false;
    }

    @Override
    public String getBotUsername() {
        return "daily-counter";
    }

    // TODO add token properly
    @Override
    public String getBotToken() {
        // TODO dont commit thsi
        return "token here";
    }
}