package gymWorkoutTracker;

import java.util.ArrayList;

public class WorkoutSession {

	private String date;
    private String workoutType;
    private ArrayList<ExerciseEntry> exercises;

    public WorkoutSession(String date, String workoutType) {
        this.date = date;
        this.workoutType = workoutType;
        this.exercises = new ArrayList<ExerciseEntry>();
    }

    public void addExercise(ExerciseEntry exercise) {
        exercises.add(exercise);
    }

    public String getDate() {
        return date;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public ArrayList<ExerciseEntry> getExercises() {
        return exercises;
    }

    public String toDisplayString() {
        String result = "Date: " + date + "\n";
        result += "Workout Type: " + workoutType + "\n";
        result += "Exercises:\n";

        if (exercises.size() == 0) {
            result += "  (No exercises)\n";
            return result;
        }

        for (int i = 0; i < exercises.size(); i++) {
            result += "  [" + (i + 1) + "] " + exercises.get(i).toDisplayString() + "\n";
        }

        return result;
    }
}
