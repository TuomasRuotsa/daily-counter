package bot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Commands {

    // TODO create domain solution for commands
    public static String handleCommand(Update update, EkaState state) {

        switch (update.getMessage().getText()) {
            case "/help":
                // TODO implement properly
                return "Supported commands:\n/help\n/about\n/whatsup\n/scoreboard\n/gdpr\n/requestfeature"; // TODO make this sensible to maintain
            case "/about":
                return "This is a new glorious ekabotti.";
            case "/whatsup":
                return whatsUp();
            case "/scoreboard":
                return state.getScoreMessage();
            case "/gdpr":
                return "Thanks for asking " + update.getMessage().getFrom().getFirstName() + "!\nThis is what I know about you: " + state.getParticipantById(update.getMessage().getFrom().getId()).getAllData();
            case "/requestfeature":
                return "Sorry not possible.";
            default:
                return "Command not recognized";
        }
    }

    private static String whatsUp() {
        return "Not implemented yet.";
    }
}
