package gymWorkoutTracker;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
        WorkoutTracker tracker = new WorkoutTracker();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(input, "Choose an option: ");

            switch (choice) {
                case 1:
                    tracker.addWorkoutSession(input);
                    break;
                case 2:
                    tracker.viewAllSessions();
                    break;
                case 3:
                    tracker.searchExerciseHistory(input);
                    break;
                case 4:
                    tracker.viewProgressSummary();
                    break;
                case 5:
                    tracker.saveToFile("workouts.txt");
                    break;
                case 6:
                    tracker.loadFromFile("workouts.txt");
                    break;
                case 7:
                    tracker.deleteSession(input);
                    break;
                case 8:
                    System.out.println("Goodbye! Keep training 💪");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-8.");
            }

            System.out.println();
        }

        input.close();
    }

    private static void printMenu() {
        System.out.println("========== GYM WORKOUT TRACKER ==========");
        System.out.println("1. Add Workout Session");
        System.out.println("2. View All Sessions");
        System.out.println("3. Search Exercise History");
        System.out.println("4. View Progress Summary");
        System.out.println("5. Save Data");
        System.out.println("6. Load Data");
        System.out.println("7. Delete Session");
        System.out.println("8. Exit");
        System.out.println("=========================================");
    }

    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine(); // clear newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // discard bad input
            }
        }
   }
	

}
