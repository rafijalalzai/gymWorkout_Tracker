# gymWorkout_Tracker (Java)
A console-based Java application for tracking workout session, exercises, sets, reps, and weights.
This project was built to practice **object-oreinted programming (OOP)**, **ArrayList-based data management**, **input validation**, and **Java file I/O**.

## Fetures
- Add workout session (date + workout type)
- Add exercieses to a session (e.g., Bench Press, Squat)
- Add sets with reps, weight, and optional notes
- View all workout sessions and exercise name
- View progress summary:
    - Best weight per exercise
    - Total training volume per exercise
- Delete workout sessions
- Save workout data to a text file
- Load workout data from a text file

 ## Technologies Used
 - **Java**
 - **Object-Oriented Programming (OOP)**
 - **ArrayList**
 - **Java File I/O**
     - `BufferedReader`
     - `FileReader`
     - `FileWriter`
     - `PrintWriter`
  -**Eclipse IDE**

## Project Structure
- `Main.java` — Displays the menu, handles user input, and controls program flow
- `WorkoutTracker.java` — Core application logic (add/view/search/delete/save/load)
- `WorkoutSession.java` — Represents a single workout session (date, type, exercises)
- `ExerciseEntry.java` — Represents one exercise and its sets
- `SetEntry.java` — Represents one set (reps, weight, notes)

## How to Run (Eclipse)

1. Open Eclipse and create a **Java Project**
2. Create a package named `gymWorkoutTracker`
3. Add the following files to the package:
   - `Main.java`
   - `WorkoutTracker.java`
   - `WorkoutSession.java`
   - `ExerciseEntry.java`
   - `SetEntry.java`
4. Run `Main.java` as:
   - **Run As → Java Application**

## How to Use

1. Start the program
2. Choose menu options (1–8)
3. Add a workout session and enter exercises/sets
4. View all sessions or search exercise history
5. Save your data using the **Save Data** option
6. Load previous data using the **Load Data** option

## Sample Skills Demonstrated

- Class design and object modeling
- Working with nested collections (`sessions -> exercises -> sets`)
- Input validation using `Scanner`
- Menu-driven console application design
- File persistence (save/load)
- Searching and summarizing stored data

## Future Improvements

- Edit existing sessions/exercises/sets
- Sort sessions by date
- Filter by workout type or muscle group
- Export workout history to CSV
- Add PR (personal record) tracking
- Build a GUI version (JavaFX)

## Author

**Abdul Rafi Jalalzai**  
York University — BSc (Honours) Computer Science
