package gymWorkoutTracker;

import java.util.ArrayList;

public class ExerciseEntry {
	 private String exerciseName;
	    private String muscleGroup;
	    private ArrayList<SetEntry> sets;

	    public ExerciseEntry(String exerciseName, String muscleGroup) {
	        this.exerciseName = exerciseName;
	        this.muscleGroup = muscleGroup;
	        this.sets = new ArrayList<SetEntry>();
	    }

	    public void addSet(SetEntry set) {
	        sets.add(set);
	    }

	    public String getExerciseName() {
	        return exerciseName;
	    }

	    public String getMuscleGroup() {
	        return muscleGroup;
	    }

	    public ArrayList<SetEntry> getSets() {
	        return sets;
	    }

	    public double getMaxWeight() {
	        double max = 0.0;

	        for (int i = 0; i < sets.size(); i++) {
	            if (sets.get(i).getWeight() > max) {
	                max = sets.get(i).getWeight();
	            }
	        }

	        return max;
	    }

	    public double getTotalVolume() {
	        double total = 0.0;

	        for (int i = 0; i < sets.size(); i++) {
	            total += sets.get(i).getReps() * sets.get(i).getWeight();
	        }

	        return total;
	    }

	    public String toDisplayString() {
	        String result = exerciseName + " (" + muscleGroup + ")\n";

	        if (sets.size() == 0) {
	            result += "    No sets recorded";
	            return result;
	        }

	        for (int i = 0; i < sets.size(); i++) {
	            result += "    Set " + (i + 1) + ": " + sets.get(i).toDisplayString() + "\n";
	        }

	        return result.trim();
	    }

}
