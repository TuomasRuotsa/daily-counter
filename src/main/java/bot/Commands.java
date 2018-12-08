package bot;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.ArrayList;

public class Commands {

    // TODO create domain solution for commands
    public static String handleCommand(Update update) {

        switch (update.getMessage().getText()) {
            case "/help":
                // TODO implement
                return "Help command not implemented";
            case "/about":
                return "This is a new glorious ekabotti by Tuomas";
            case "/whatsup":
                return whatsUp();
            default:
                return "Command not recognized";
        }
    }

    private static String whatsUp() {
        return "Not implemented yet.";
    }

}
