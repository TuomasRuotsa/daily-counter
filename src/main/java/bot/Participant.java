package bot;

public class Participant implements Comparable {
    private String name;
    private int ekaCount;
    private int id;

    public Participant(String name, int ekaCount, int id) {
        this.id = id;
        this.name = name;
        this.ekaCount = ekaCount;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {return this.id; }

    public int getScore() {
        return this.ekaCount;
    }

    public int getEkaCount() {
        return this.ekaCount;
    }

    public void addEka() {
        this.ekaCount++;
    }

    public String getAllData() {
        return "Id: " + this.id + "\n"
                + "name: " + this.name + "\n"
                + "ekascore: " + this.ekaCount;
    }

    @Override
    public int compareTo(Object comparestu) {
        int compareScore;
        compareScore = ((Participant)comparestu).getScore();
        /* For Ascending order*/
        return this.ekaCount-compareScore;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    @Override
    public String toString() {
        return this.name + ", " + ekaCount;
    }


}
