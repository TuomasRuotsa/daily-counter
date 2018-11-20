package bot;


import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DailyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            if (isBotCommand(update)) {

                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText("The bot is still under construction :) ");
                //.setText(update.getMessage().getText());
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isBotCommand(Update update) {
        return update.getMessage().getText().charAt(0) == '/';
    }

    @Override
    public String getBotUsername() {
        return "daily-counter";
    }

    @Override
    public String getBotToken() {
        return "TODO set token";
    }
}