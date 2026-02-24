package gymWorkoutTracker;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutTracker {

	private ArrayList<WorkoutSession> sessions;

    public WorkoutTracker() {
        sessions = new ArrayList<WorkoutSession>();
    }

    public void addWorkoutSession(Scanner input) {
        System.out.println("---- Add Workout Session ----");

        System.out.print("Enter date (e.g., 2026-02-24): ");
        String date = input.nextLine().trim();

        System.out.print("Enter workout type (Push/Pull/Legs/Upper/Lower/etc.): ");
        String workoutType = input.nextLine().trim();

        WorkoutSession session = new WorkoutSession(date, workoutType);

        int exerciseCount = readPositiveInt(input, "How many exercises in this session? ");

        for (int i = 0; i < exerciseCount; i++) {
            System.out.println("\nExercise #" + (i + 1));

            System.out.print("Exercise name: ");
            String exerciseName = input.nextLine().trim();

            System.out.print("Muscle group (Chest/Back/Legs/etc.): ");
            String muscleGroup = input.nextLine().trim();

            ExerciseEntry exercise = new ExerciseEntry(exerciseName, muscleGroup);

            int setCount = readPositiveInt(input, "How many sets for " + exerciseName + "? ");

            for (int j = 0; j < setCount; j++) {
                System.out.println("  Set #" + (j + 1));

                int reps = readPositiveInt(input, "  Reps: ");
                double weight = readNonNegativeDouble(input, "  Weight (kg): ");

                System.out.print("  Notes (optional, press Enter to skip): ");
                String notes = input.nextLine();

                exercise.addSet(new SetEntry(reps, weight, notes));
            }

            session.addExercise(exercise);
        }

        sessions.add(session);
        System.out.println("Workout session added successfully!");
    }

    public void viewAllSessions() {
        System.out.println("---- All Workout Sessions ----");

        if (sessions.size() == 0) {
            System.out.println("No workout sessions found.");
            return;
        }

        for (int i = 0; i < sessions.size(); i++) {
            System.out.println("\nSession #" + (i + 1));
            System.out.println(sessions.get(i).toDisplayString());
        }
    }

    public void searchExerciseHistory(Scanner input) {
        System.out.println("---- Search Exercise History ----");
        if (sessions.size() == 0) {
            System.out.println("No sessions available.");
            return;
        }

        System.out.print("Enter exercise name to search: ");
        String targetOriginal = input.nextLine().trim();
        String target = targetOriginal.toLowerCase();

        boolean found = false;

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutSession session = sessions.get(i);

            for (int j = 0; j < session.getExercises().size(); j++) {
                ExerciseEntry exercise = session.getExercises().get(j);

                if (exercise.getExerciseName().toLowerCase().equals(target)) {
                    found = true;
                    System.out.println("\nDate: " + session.getDate() + " | Type: " + session.getWorkoutType());
                    System.out.println(exercise.toDisplayString());
                }
            }
        }

        if (!found) {
            System.out.println("No history found for exercise: " + targetOriginal);
        }
    }

    public void viewProgressSummary() {
        System.out.println("---- Progress Summary ----");

        if (sessions.size() == 0) {
            System.out.println("No sessions available.");
            return;
        }

        ArrayList<String> exerciseNames = new ArrayList<String>();
        ArrayList<Double> bestWeights = new ArrayList<Double>();
        ArrayList<Double> totalVolumes = new ArrayList<Double>();

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutSession session = sessions.get(i);

            for (int j = 0; j < session.getExercises().size(); j++) {
                ExerciseEntry exercise = session.getExercises().get(j);
                String name = exercise.getExerciseName();

                double bestInThisExercise = exercise.getMaxWeight();
                double volumeInThisExercise = exercise.getTotalVolume();

                int index = findExerciseIndex(exerciseNames, name);

                if (index == -1) {
                    exerciseNames.add(name);
                    bestWeights.add(bestInThisExercise);
                    totalVolumes.add(volumeInThisExercise);
                } else {
                    if (bestInThisExercise > bestWeights.get(index)) {
                        bestWeights.set(index, bestInThisExercise);
                    }
                    totalVolumes.set(index, totalVolumes.get(index) + volumeInThisExercise);
                }
            }
        }

        if (exerciseNames.size() == 0) {
            System.out.println("No exercises recorded yet.");
            return;
        }

        System.out.println("Exercise Progress:");
        for (int i = 0; i < exerciseNames.size(); i++) {
            System.out.printf("- %s | Best Weight: %.2f kg | Total Volume: %.2f%n",
                    exerciseNames.get(i), bestWeights.get(i), totalVolumes.get(i));
        }
    }

    public void deleteSession(Scanner input) {
        System.out.println("---- Delete Session ----");

        if (sessions.size() == 0) {
            System.out.println("No sessions to delete.");
            return;
        }

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutSession s = sessions.get(i);
            System.out.println((i + 1) + ". " + s.getDate() + " - " + s.getWorkoutType());
        }

        int choice = readPositiveInt(input, "Enter session number to delete: ");

        if (choice < 1 || choice > sessions.size()) {
            System.out.println("Invalid session number.");
            return;
        }

        WorkoutSession removed = sessions.remove(choice - 1);
        System.out.println("Deleted session: " + removed.getDate() + " - " + removed.getWorkoutType());
    }

    public void saveToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            // Custom text format:
            // SESSION|date|type
            // EXERCISE|name|muscle
            // SET|reps|weight|notes
            // ENDEXERCISE
            // ENDSESSION

            for (int i = 0; i < sessions.size(); i++) {
                WorkoutSession session = sessions.get(i);
                writer.println("SESSION|" + escape(session.getDate()) + "|" + escape(session.getWorkoutType()));

                for (int j = 0; j < session.getExercises().size(); j++) {
                    ExerciseEntry exercise = session.getExercises().get(j);
                    writer.println("EXERCISE|" + escape(exercise.getExerciseName()) + "|" + escape(exercise.getMuscleGroup()));

                    for (int k = 0; k < exercise.getSets().size(); k++) {
                        SetEntry set = exercise.getSets().get(k);
                        writer.println("SET|" + set.getReps() + "|" + set.getWeight() + "|" + escape(set.getNotes()));
                    }

                    writer.println("ENDEXERCISE");
                }

                writer.println("ENDSESSION");
            }

            writer.close();
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        ArrayList<WorkoutSession> loadedSessions = new ArrayList<WorkoutSession>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            WorkoutSession currentSession = null;
            ExerciseEntry currentExercise = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SESSION|")) {
                    String[] parts = splitSafe(line);
                    if (parts.length >= 3) {
                        String date = unescape(parts[1]);
                        String type = unescape(parts[2]);
                        currentSession = new WorkoutSession(date, type);
                    }
                } else if (line.startsWith("EXERCISE|")) {
                    String[] parts = splitSafe(line);
                    if (parts.length >= 3) {
                        String name = unescape(parts[1]);
                        String muscle = unescape(parts[2]);
                        currentExercise = new ExerciseEntry(name, muscle);
                    }
                } else if (line.startsWith("SET|")) {
                    String[] parts = splitSafe(line);
                    if (parts.length >= 4 && currentExercise != null) {
                        int reps = Integer.parseInt(parts[1]);
                        double weight = Double.parseDouble(parts[2]);
                        String notes = unescape(parts[3]);

                        currentExercise.addSet(new SetEntry(reps, weight, notes));
                    }
                } else if (line.equals("ENDEXERCISE")) {
                    if (currentSession != null && currentExercise != null) {
                        currentSession.addExercise(currentExercise);
                        currentExercise = null;
                    }
                } else if (line.equals("ENDSESSION")) {
                    if (currentSession != null) {
                        loadedSessions.add(currentSession);
                        currentSession = null;
                    }
                }
            }

            reader.close();

            sessions = loadedSessions;
            System.out.println("Data loaded successfully from " + fileName + " (" + sessions.size() + " sessions)");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    private int findExerciseIndex(ArrayList<String> names, String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    private int readPositiveInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine(); // clear newline
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine();
            }
        }
    }

    private double readNonNegativeDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                input.nextLine(); // clear newline
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    // Escape special characters for file saving
    private String escape(String text) {
        if (text == null) {
            return "";
        }
        text = text.replace("\\", "\\\\");
        text = text.replace("|", "\\p");
        return text;
    }

    private String unescape(String text) {
        if (text == null) {
            return "";
        }
        text = text.replace("\\p", "|");
        text = text.replace("\\\\", "\\");
        return text;
    }

    private String[] splitSafe(String line) {
        return line.split("\\|", -1);
    }
}
