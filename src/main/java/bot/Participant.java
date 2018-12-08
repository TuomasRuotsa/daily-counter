package bot;

public class Participant {
    private String name;
    private int ekaCount;

    public Participant(String name, int ekaCount) {
        this.name = name;
        this.ekaCount = ekaCount;
    }

    public String getName() {
        return this.name;
    }

    public int getEkaCount() {
        return this.ekaCount;
    }
}
