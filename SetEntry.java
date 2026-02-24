package gymWorkoutTracker;

public class SetEntry {

	private int reps;
    private double weight;
    private String notes;

    public SetEntry(int reps, double weight, String notes) {
        this.reps = reps;
        this.weight = weight;
        this.notes = notes;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }

    public String toDisplayString() {
        String result = reps + " reps @ " + weight + " kg";

        if (notes != null && notes.trim().length() > 0) {
            result += " | Notes: " + notes;
        }

        return result;
    }
}
