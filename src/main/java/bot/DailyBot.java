package bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static bot.Commands.handleCommand;
import static bot.Tools.readEkaFile;

public class DailyBot extends TelegramLongPollingBot {
    //TODO handle support for multiple chat channels
    //TODO implement support for different languages

    @Override
    public void onUpdateReceived(Update update) {
        EkaState state = readEkaState();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            state = checkAndUpdateEka(update, state);
            if (state != null) {
                sendMessage(update, "Onneksi olkoon ekasta " + update.getMessage().getFrom().getFirstName() + "!"); // TODO proper message
                sendMessage(update, state.getScoreMessage());
            }

            if (isBotCommand(update)) {

                String response = handleCommand(update, state);
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
        try {
            Gson gson = new Gson();
            EkaState state = gson.fromJson(ekaFile, EkaState.class);
            return state;
        } catch (Exception e) {
            // TODO which exception and handle
            return null;
        }
    }

    private void writeEkaState(EkaState state) {
        // TODO create proper class etc. for serialization and deserialization stuff
        Gson g = new Gson();
        String jsonString = g.toJson(state);

        try (PrintWriter out = new PrintWriter("test.json")) { // TODO actual filename
            out.println(jsonString);
        } catch (FileNotFoundException e) {
            // TODO handle case
            System.out.println("Couldn't create file");
        }

    }

    private Participant getOrCreateParticipant(Update update) {
        // TODO move this to Participantfactory etc.
        // TODO implement
        return null;
    }

    private EkaState checkAndUpdateEka(Update update, EkaState state) {
        // TODO does Instant.now() suffice here?
        int participantId = update.getMessage().getFrom().getId();
        Instant candidate = Instant.now();
        if (isThisEka(state.getEka(), candidate)) {
            state = updateEka(state, candidate);
            state = updateParticipantScore(state, update);
            writeEkaState(state);
            return state;
        } else {
            return null;
        }
    }

    private boolean isThisEka(Instant first, Instant second) {
        // TODO remove debugging setting
        if (true == true) { return true; }

        if (first == null || second == null) {
            // TODO handle the case better
            return false;
        }

        boolean newIsLater = isSecondLaterThanFirst(first, second);
        boolean differentDates = hasDayChangedBetween(first, second);
        return newIsLater && differentDates;
    }

    private boolean isSecondLaterThanFirst(Instant first, Instant second) {
        return first.compareTo(second) > 0;
    }

    private boolean hasDayChangedBetween(Instant first, Instant second) {
        Instant firstDays = first.truncatedTo(ChronoUnit.DAYS);
        Instant secondDays = second.truncatedTo(ChronoUnit.DAYS);
        return secondDays.isAfter(firstDays);
    }

    private EkaState updateEka(EkaState state, Instant eka) {
        state.setEka(eka);
        return state;
    }

    private EkaState updateParticipantScore(EkaState state, Update update) {
        // TODO does it make sense to add Participants here?
        User user = update.getMessage().getFrom();
        Participant participant = state.getParticipantById(user.getId());
        if (participant == null) {
            String name = user.getFirstName() + " " + user.getLastName();
            int initialScore = 0;
            participant = new Participant(name, initialScore, user.getId());
        }
        participant.addEka();
        state.addOrUpdateParticipant(participant);
        return state;
    }

    @Override
    public String getBotUsername() {
        return "daily-counter";
    }

    // TODO add token properly
    @Override
    public String getBotToken() {
        // TODO dont commit this
        return "TODO";
    }
}