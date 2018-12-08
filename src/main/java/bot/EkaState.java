package bot;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class EkaState {
    private ArrayList<Participant> participantList;
    private Instant latestEka;

    public EkaState() {
        participantList = new ArrayList<>();
        latestEka = null;
    }

    public void setEka(Instant ekaTime) {
        latestEka = ekaTime;
    }

    public Instant getEka() {
        return latestEka;
    }

    public void addParticipant(Participant participant) {
        participantList.add(participant);
    }


}

