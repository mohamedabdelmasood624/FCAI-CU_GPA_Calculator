import java.util.*;

public class FCAICUGPACalculator {

    // ANSI color codes for terminal output
    private static final String RESET = "\u001B[0m";
//    this red
    private static final String RED = "\u001B[31m";
//    this green
    private static final String GREEN = "\u001B[32m";
//    this yellow
    private static final String YELLOW = "\u001B[33m";
//    this blue
    private static final String BLUE = "\u001B[34m";
//    this purple
    private static final String PURPLE = "\u001B[35m";
//    this sky blue
    private static final String CYAN = "\u001B[36m";
//    this bold
    private static final String BOLD = "\u001B[1m";


    static class Course {
        String code;
        String name;
        int credits;
        double percentage;
        String letterGrade;
        double gradePoints;
        int semester;
        String level;

//        =======================================constructor=======================================================
        Course(String code, String name, int credits, int semester, String level) {
            this.code = code;
            this.name = name;
            this.credits = credits;
            this.semester = semester;
            this.level = level;
        }
    }



    static class Semester {
        int semesterNumber;
        String level;
        List<Course> courses;

        Semester(int semesterNumber, String level) {
            this.semesterNumber = semesterNumber;
            this.level = level;
            this.courses = new ArrayList<>();
        }

        void addCourse(Course course) {
            courses.add(course);
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printWelcomeScreen();

        // Create all courses organized by semester
        List<Semester> allSemesters = createCourseCatalog();

        while (running) {
            displayCourseCatalog(allSemesters);

            System.out.println("\n" + YELLOW + BOLD + "⚡ Choose calculation mode:" + RESET);
            System.out.println("1. Calculate all courses");
            System.out.println("2. Calculate specific semester");
            System.out.println("3. Calculate specific level");
            System.out.println("4. Calculate specific course ");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice (1-5): ");

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    calculateAllCourses(allSemesters, scanner);
                    break;
                case 2:
                    calculateSemester(allSemesters, scanner);
                    break;
                case 3:
                    calculateLevel(allSemesters, scanner);
                    break;
                case 4:
                    calculateSpecificCourses(allSemesters, scanner);
                    break;
                case 5:
                    System.out.println(GREEN + "\n✅ Exiting program. Goodbye!" + RESET);
                    running = false;
                    continue;
                default:
                    System.out.println(RED + "❌ Invalid choice! Please enter 1-5." + RESET);
                    continue;
            }

            // Ask if user wants to perform another calculation
            if (!askForAnotherCalculation(scanner)) {
                running = false;
            }
        }

        scanner.close();
        printFooter();
    }




    private static boolean askForAnotherCalculation(Scanner scanner) {
        System.out.println("\n" + YELLOW + BOLD + "🔄 Would you like to perform another calculation?" + RESET);
        System.out.println("1. Yes, implement another calculation");
        System.out.println("2. No, exit program");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();

        if (choice == 2) {
            System.out.println(GREEN + "\n✅ Exiting program. Goodbye!" + RESET);
            return false;
        } else if (choice != 1) {
            System.out.println(RED + "❌ Invalid choice. Exiting program." + RESET);
            return false;
        }

        // Clear the screen (optional - prints new lines for better readability) make a space between calculations
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }

        return true;
    }


    private static void printWelcomeScreen() {
        System.out.println(CYAN + "╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     " + BOLD + "FACULTY OF COMPUTERS & ARTIFICIAL INTELLIGENCE" + RESET + CYAN + "         ║");
        System.out.println("║              " + BOLD + "FCAI-CU GPA CALCULATOR SYSTEM" + RESET + CYAN + "                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝" + RESET);
    }



    private static List<Semester> createCourseCatalog() {
        List<Semester> semesters = new ArrayList<>();

        //    object composition in action

        // Level 1 - Semester 1
        Semester level1Sem1 = new Semester(1, "Level 1 - Semester 1");
        level1Sem1.addCourse(new Course("MA111", "Mathematics-1", 3, 1, "Level 1"));
        level1Sem1.addCourse(new Course("MA112", "Discrete Mathematics", 3, 1, "Level 1"));
        level1Sem1.addCourse(new Course("IT111", "Electronics", 3, 1, "Level 1"));
        level1Sem1.addCourse(new Course("CS111", "Fundamentals of Computer Science", 3, 1, "Level 1"));
        level1Sem1.addCourse(new Course("HU111", "Technical Report Writing", 2, 1, "Level 1"));
        level1Sem1.addCourse(new Course("HU113", "Creative Thinking and Communication Skills", 2, 1, "Level 1"));
        semesters.add(level1Sem1);

        // Level 1 - Semester 2
        Semester level1Sem2 = new Semester(2, "Level 1 - Semester 2");
        level1Sem2.addCourse(new Course("MA113", "Mathematics-2", 3, 2, "Level 1"));
        level1Sem2.addCourse(new Course("ST121", "Probability and Statistics-1", 3, 2, "Level 1"));
        level1Sem2.addCourse(new Course("CS112", "Structured Programming", 3, 2, "Level 1"));
        level1Sem2.addCourse(new Course("HU118", "Selected Topics in Humanities", 2, 2, "Level 1"));
        level1Sem2.addCourse(new Course("HU112", "Ethics and Professionalism", 2, 2, "Level 1"));
        level1Sem2.addCourse(new Course("HU121", "Fundamentals of Economics", 2, 2, "Level 1"));
        level1Sem2.addCourse(new Course("HU124", "Critical Thinking", 0, 2, "Level 1"));
        semesters.add(level1Sem2);

        // Level 2 - Semester 1
        Semester level2Sem1 = new Semester(3, "Level 2 - Semester 1");
        level2Sem1.addCourse(new Course("MA214", "Mathematics-3", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("ST222", "Probability and Statistics-2", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("IT212", "Logic Design", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("CS212", "Operating Systems", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("CS213", "Object Oriented Programming", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("IT221", "Computer Networks Technology", 3, 3, "Level 2"));
        level2Sem1.addCourse(new Course("HU225", "Entrepreneurship", 0, 3, "Level 2"));
        semesters.add(level2Sem1);

        // Level 2 - Semester 2
        Semester level2Sem2 = new Semester(4, "Level 2 - Semester 2");
        level2Sem2.addCourse(new Course("CS251", "Introduction to Software Engineering", 3, 4, "Level 2"));
        level2Sem2.addCourse(new Course("IS211", "Introduction to Database Systems", 3, 4, "Level 2"));
        level2Sem2.addCourse(new Course("CS214", "Data Structures", 3, 4, "Level 2"));
        level2Sem2.addCourse(new Course("IS231", "Web Technology", 3, 4, "Level 2"));
        level2Sem2.addCourse(new Course("DS251", "Fundamentals of Management", 2, 4, "Level 2"));
        level2Sem2.addCourse(new Course("HU117", "Social Issues", 0, 4, "Level 2"));
        semesters.add(level2Sem2);

        return semesters;
    }


    private static void displayCourseCatalog(List<Semester> semesters) {
        System.out.println("\n" + CYAN + BOLD + "📚 COURSE CATALOG" + RESET);
        for (Semester sem : semesters) {
            System.out.println("\n" + GREEN + BOLD + "📖 " + sem.level + RESET);
            System.out.println("┌─────────┬──────────────────────────────────────┬────────┐");
            System.out.println("│ Code    │ Course Name                          │ Credit │");
            System.out.println("├─────────┼──────────────────────────────────────┼────────┤");

            for (Course c : sem.courses) {
                String creditDisplay = c.credits == 0 ? "0" : String.valueOf(c.credits);
//                ternary operator to display "0" for zero credit courses instead of just blank
                System.out.printf("│ %-7s │ %-36s │ %6s │\n",
                        c.code, truncateString(c.name, 36), creditDisplay);
//                tuncateString method to limit course name to 36 characters for better formatting
            }
            System.out.println("└─────────┴──────────────────────────────────────┴────────┘");
        }
    }



    private static boolean hasZeroCreditCourses(List<Course> courses) {
        for (Course c : courses) {
            if (c.credits == 0) return true;
        }
        return false;
    }

    private static void calculateAllCourses(List<Semester> semesters, Scanner scanner) {
        List<Course> allCourses = new ArrayList<>();
        for (Semester sem : semesters) {
            allCourses.addAll(sem.courses);
//            add all () add all courses from each semester to the allCourses list
        }
        processCourses(allCourses, scanner, "All Courses (All Levels)");
    }

    private static void calculateSemester(List<Semester> semesters, Scanner scanner) {
        System.out.println("\n" + YELLOW + "Select Semester:" + RESET);
        for (int i = 0; i < semesters.size(); i++) {
            System.out.println((i + 1) + ". " + semesters.get(i).level);
//            why start from i+1 as list index starts from 0 but we want to display semester numbers starting from 1 for better user experience
        }
        System.out.println("0. Back to main menu");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println(YELLOW + "Returning to main menu..." + RESET);
            return;
        }

        if (choice > 0 && choice <= semesters.size()) {
            processCourses(semesters.get(choice - 1).courses, scanner, semesters.get(choice - 1).level);
        } else {
            System.out.println(RED + "Invalid semester!" + RESET);
        }
    }


    private static void calculateLevel(List<Semester> semesters, Scanner scanner) {
        System.out.println("\n" + YELLOW + "Select Level:" + RESET);
        System.out.println("1. Level 1 (All Semesters)");
        System.out.println("2. Level 2 (All Semesters)");
        System.out.println("0. Back to main menu");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println(YELLOW + "Returning to main menu..." + RESET);
            return;
        }

        List<Course> levelCourses = new ArrayList<>();
        String levelName = "";

        if (choice == 1) {
            levelName = "Level 1";
            for (Semester sem : semesters) {
                if (sem.level.contains("Level 1")) {
//                    firltering + aggregation in action
                    levelCourses.addAll(sem.courses);
                }
            }
        } else if (choice == 2) {
            levelName = "Level 2";
            for (Semester sem : semesters) {
                if (sem.level.contains("Level 2")) {
                    levelCourses.addAll(sem.courses);
                }
            }
        } else {
            System.out.println(RED + "Invalid choice!" + RESET);
            return;
        }

        processCourses(levelCourses, scanner, levelName + " Courses");
    }


    private static void calculateSpecificCourses(List<Semester> semesters, Scanner scanner) {
        List<Course> allCourses = new ArrayList<>();
        for (Semester sem : semesters) {
            allCourses.addAll(sem.courses);
        }

        System.out.println("\n" + YELLOW + "Select courses to calculate (enter codes separated by commas):" + RESET);
        System.out.println("Enter '0' to return to main menu");
        System.out.print("Example: CS111,MA111,IT111: ");
        scanner.nextLine(); // consume newline
        String input = scanner.nextLine();

        if (input.trim().equals("0")) {
//            early return
            System.out.println(YELLOW + "Returning to main menu..." + RESET);
            return;
        }

        String[] selectedCodes = input.split(",");
        List<Course> selectedCourses = new ArrayList<>();

        for (String code : selectedCodes) {
            code = code.trim().toUpperCase();
//            trim to delete any extra spaces and convert to uppercase for better matching with course codes
            boolean found = false;
            for (Course c : allCourses) {
                if (c.code.equals(code)) {
                    selectedCourses.add(c);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println(YELLOW + "Warning: Course " + code + " not found" + RESET);
            }
        }

        if (selectedCourses.isEmpty()) {
            System.out.println(RED + "No valid courses selected!" + RESET);
            return;
        }

        processCourses(selectedCourses, scanner, "Custom");
    }


    private static void processCourses(List<Course> courses, Scanner scanner, String title) {
        double totalWeightedPoints = 0;
        int totalCredits = 0;
        Map<String, Double> results = new LinkedHashMap<>();
        List<Course> zeroCreditCourses = new ArrayList<>();

        System.out.println("\n" + CYAN + BOLD + "📝 " + title + " - Enter Percentages:" + RESET);
        System.out.println("(Enter -1 to skip a course, -2 to exit to main menu)");

        for (Course c : courses) {
            if (c.credits == 0) {
                zeroCreditCourses.add(c);
                continue;
            }

            while (true) {
                try {
                    System.out.printf("%s[%s]%s %-40s (Credits: %d): ",
                            PURPLE, c.code, RESET, c.name, c.credits);
                    double percentage = scanner.nextDouble();

                    if (percentage == -2) {
                        System.out.println(YELLOW + "Returning to main menu..." + RESET);
                        return;
                    }

                    if (percentage == -1) {
                        System.out.println(YELLOW + "⏭️  Course skipped" + RESET);
                        break;
                    }

                    if (percentage < 0 || percentage > 100) {
                        System.out.println(RED + "❌ Please enter a percentage between 0 and 100" + RESET);
                        continue;
                    }

                    double gpa = convertToGPA(percentage);
                    totalWeightedPoints += gpa * c.credits;
                    totalCredits += c.credits;
                    results.put(c.code, percentage);

                    // Show immediate feedback
                    String gradeColor = getGradeColor(percentage);
                    System.out.println(gradeColor + "   → Grade: " + convertToLetter(percentage) +
                            " (GPA: " + String.format("%.2f", gpa) + ")" + RESET);
                    break;

                } catch (InputMismatchException e) {
                    System.out.println(RED + "❌ Invalid input! Please enter a number." + RESET);
                    scanner.next(); // Clear invalid input
                }
            }
        }

        // Handle zero credit courses
        if (!zeroCreditCourses.isEmpty()) {
            System.out.println("\n" + YELLOW + "📌 Zero Credit Courses (Pass/Fail - not included in GPA):" + RESET);
            System.out.println("(Enter P for Pass, F for Fail, or -2 to exit)");

            for (Course c : zeroCreditCourses) {
                while (true) {
                    System.out.printf("%s[%s]%s %s: ", PURPLE, c.code, RESET, c.name);
                    String status = scanner.next();

                    if (status.equals("-2")) {
                        System.out.println(YELLOW + "Returning to main menu..." + RESET);
                        return;
                    }

                    if (status.equalsIgnoreCase("P") || status.equalsIgnoreCase("F")) {
                        System.out.println(YELLOW + "   → " + (status.equalsIgnoreCase("P") ? "Pass" : "Fail") + RESET);
                        break;
                    } else {
                        System.out.println(RED + "❌ Please enter P (Pass) or F (Fail)" + RESET);
                    }
                }
            }
        }

        if (totalCredits == 0) {
            System.out.println(RED + "\n❌ No courses with credits selected!" + RESET);
            return;
        }

        double cgpa = totalWeightedPoints / totalCredits;
        displayResults(results, totalCredits, cgpa, title, zeroCreditCourses);
    }

    private static void displayResults(Map<String, Double> results, int totalCredits, double cgpa,
                                       String title, List<Course> zeroCreditCourses) {
        System.out.println("\n" + BOLD + "═══════════════════════════════════════════════════════════");
        System.out.println("                    DETAILED RESULTS - " + title);
        System.out.println("═══════════════════════════════════════════════════════════" + RESET);

        System.out.println("\n" + BOLD + "Course-wise Breakdown:" + RESET);
        System.out.println("┌─────────┬──────────────────────────────────────┬──────────┬───────┬───────┐");
        System.out.println("│ Code    │ Course Name                          │ Percent  │ Grade │ GPA   │");
        System.out.println("├─────────┼──────────────────────────────────────┼──────────┼───────┼───────┤");

        for (Map.Entry<String, Double> entry : results.entrySet()) {
            String code = entry.getKey();
            double percent = entry.getValue();
            String grade = convertToLetter(percent);
            double gpa = convertToGPA(percent);
            String courseName = getCourseName(code);

            String gradeColor = getGradeColor(percent);
            System.out.printf("│ %-7s │ %-36s │ %6.1f%% │ %s%-5s%s │ %4.2f │\n",
                    code,
                    truncateString(courseName, 36),
                    percent,
                    gradeColor, grade, RESET,
                    gpa);
        }
        System.out.println("└─────────┴──────────────────────────────────────┴──────────┴───────┴───────┘");

        if (!zeroCreditCourses.isEmpty()) {
            System.out.println("\n" + YELLOW + "Zero Credit Courses (Not included in GPA):" + RESET);
            for (Course c : zeroCreditCourses) {
                System.out.printf("  • %s - %s\n", c.code, c.name);
            }
        }

        System.out.println("\n" + BOLD + "📊 Summary Statistics:" + RESET);
        System.out.println("   Total Credit Hours: " + totalCredits);
        System.out.printf("   Cumulative GPA: %.2f\n", cgpa);
        System.out.println("   Academic Standing: " + getStanding(cgpa));

        // GPA Visualization
        displayGPAVisualization(cgpa);

    }

    private static String getCourseName(String code) {
        Map<String, String> courseNames = new HashMap<>();
        courseNames.put("MA111", "Mathematics-1");
        courseNames.put("MA112", "Discrete Mathematics");
        courseNames.put("IT111", "Electronics");
        courseNames.put("CS111", "Fundamentals of Computer Science");
        courseNames.put("HU111", "Technical Report Writing");
        courseNames.put("HU113", "Creative Thinking and Communication Skills");
        courseNames.put("MA113", "Mathematics-2");
        courseNames.put("ST121", "Probability and Statistics-1");
        courseNames.put("CS112", "Structured Programming");
        courseNames.put("HU118", "Selected Topics in Humanities");
        courseNames.put("HU112", "Ethics and Professionalism");
        courseNames.put("HU121", "Fundamentals of Economics");
        courseNames.put("HU124", "Critical Thinking");
        courseNames.put("MA214", "Mathematics-3");
        courseNames.put("ST222", "Probability and Statistics-2");
        courseNames.put("IT212", "Logic Design");
        courseNames.put("CS212", "Operating Systems");
        courseNames.put("CS213", "Object Oriented Programming");
        courseNames.put("IT221", "Computer Networks Technology");
        courseNames.put("HU225", "Entrepreneurship");
        courseNames.put("CS251", "Introduction to Software Engineering");
        courseNames.put("IS211", "Introduction to Database Systems");
        courseNames.put("CS214", "Data Structures");
        courseNames.put("IS231", "Web Technology");
        courseNames.put("DS251", "Fundamentals of Management");
        courseNames.put("HU117", "Social Issues");

        return courseNames.getOrDefault(code, code);
    }

    private static String getGradeColor(double percentage) {
        if (percentage >= 88) return GREEN;
        if (percentage >= 76) return CYAN;
        if (percentage >= 64) return YELLOW;
        return RED;
    }

    private static void displayGPAVisualization(double gpa) {
        int filledBars = (int)(gpa * 10); // Max 40 bars for 4.0 GPA
        System.out.println("\n" + BOLD + "📈 GPA Visualization:" + RESET);
        System.out.print("   [");
        for (int i = 0; i < 40; i++) {
            if (i < filledBars) {
                if (i < 20) System.out.print(GREEN + "█" + RESET);
                else if (i < 30) System.out.print(YELLOW + "█" + RESET);
                else System.out.print(RED + "█" + RESET);
            } else {
                System.out.print("░");
            }
        }
        System.out.printf("] %.2f/4.0\n", gpa);
    }


    private static String truncateString(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    private static double convertToGPA(double p) {
        if (p >= 96) return 4.0;
        else if (p >= 92) return 3.7;
        else if (p >= 88) return 3.4;
        else if (p >= 84) return 3.2;
        else if (p >= 80) return 3.0;
        else if (p >= 76) return 2.8;
        else if (p >= 72) return 2.6;
        else if (p >= 68) return 2.4;
        else if (p >= 64) return 2.2;
        else if (p >= 60) return 2.0;
        else if (p >= 55) return 1.5;
        else if (p >= 50) return 1.0;
        else return 0.0;
    }

    private static String convertToLetter(double p) {
        if (p >= 96) return "A+";
        else if (p >= 92) return "A";
        else if (p >= 88) return "A-";
        else if (p >= 84) return "B+";
        else if (p >= 80) return "B";
        else if (p >= 76) return "B-";
        else if (p >= 72) return "C+";
        else if (p >= 68) return "C";
        else if (p >= 64) return "C-";
        else if (p >= 60) return "D+";
        else if (p >= 55) return "D";
        else if (p >= 50) return "D-";
        else return "F";
    }

    private static String getStanding(double gpa) {
        if (gpa < 1) return "ضعيف جداً (Very Poor)";
        else if (gpa < 2) return "ضعيف (Poor)";
        else if (gpa < 2.5) return "مقبول (Pass)";
        else if (gpa < 3) return "جيد (Good)";
        else if (gpa < 3.5) return "جيد جداً (Very Good)";
        else return "ممتاز (Excellent)";
    }

    private static void printFooter() {
        System.out.println("\n" + CYAN + "╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     Thank you for using FCAI Professional GPA Calculator   ║");
        System.out.println("║            " + BOLD + "Keep striving for excellence!" + RESET + CYAN + "                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝" + RESET);
    }
}