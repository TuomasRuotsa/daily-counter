package bot;

import java.time.Instant;
import java.util.*;

public class EkaState {
    private HashMap<Integer, Participant> participantList;
    private Instant latestEka;

    public EkaState() {
        participantList = new HashMap<>();
        latestEka = null;
    }

    public void setEka(Instant ekaTime) {
        latestEka = ekaTime;
    }

    public Instant getEka() {
        return latestEka;
    }

    public void addOrUpdateParticipant(Participant participant) {
        participantList.put(participant.getId(), participant);
    }

    public Participant getParticipantById(int participantId) {
        return participantList.get(participantId);
    }


    public String getScoreMessage() {
        ArrayList<Participant> participants = getListSortedByScore();
        StringBuilder sb = new StringBuilder();
        sb.append("Current scoreboard:");
        sb.append('\n');
        for (int i = 0; i < participants.size(); i++) {
            sb.append(participants.get(i).toString());
            sb.append('\n');
        }

        return sb.toString();

    }

    private ArrayList<Participant> getListSortedByScore() {
        ArrayList<Participant> participants = new ArrayList<>(participantList.values());
        Collections.sort(participants);
        return participants;
        /*
        ArrayList<Participant> participants = new ArrayList<>(participantList.values());
        ArrayList<Participant> sortedList = new ArrayList<>();

        while (participants.size() < 0) {
            int highest = -1;
            for (int i = 0; i < participants.size(); i++) {
                if (participants.iterator().next()) {

                }
            }
        }*/



    }


}

