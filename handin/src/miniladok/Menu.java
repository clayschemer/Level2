package miniladok;

import java.util.List;

/**
 * @author Martin Rydin
 *
 */
public class Menu {
	private MiniLadok miniladok;
	private RecordKeeper recordKeeper;
	
	/**Constructor which takes the register to use and filename
	 * for storage as arguments.
	 * @param ladok the MiniLadok register to use
	 * @param fileName the name of the text file for storage
	 */
	public Menu(MiniLadok ladok, String fileName) {
		miniladok = ladok;
		recordKeeper = new RecordKeeper();
		recordKeeper.load(fileName, miniladok);
		menu();
		recordKeeper.save(fileName, miniladok);
		System.out.println("Bye!");
	}

	/**Main method to execute miniladok
	 * takes an optional argument args to specify the
	 * text file name to use for load/save when the 
	 * application starts and exits.
	 * @param args the filename to use for store/load
	 * 			got to end with .txt.
	 */
	public static void main(String[] args) {
		String fileName = null;
		if (args.length > 0) {
			fileName = args[0].trim();
		} else {
			fileName = "students.txt";
		}
		System.out.println("The register will be loaded/saved from/to: " + fileName + "\n");
		new Menu(new MiniLadok(), fileName);

	}

	/**Method that shows the available commands to a user 
	 * to control MiniLadok with. Calls different methods in this class
	 * and in MiniLadok.java and formats the answers for viewing.
	 */
	public void menu() {
		System.out.println("Welcome to MiniLadok!");
		boolean finnish = false;
		while (!finnish) {
			System.out
					.println("\n1.\tAdd a new student to the register.\n"
							+ "2.\tRegister a passed course and grade for a student.\n"
							+ "3.\tGet the number of students in the register.\n"
							+ "4.\tAdd a new course.\n"
							+ "5.\tGet a list of passed courses with grades for a student.\n"
							+ "6.\tGet the total amount of credits for a student.\n"
							+ "7.\tGet a list with students who has, at least, taken a specified number of credits.\n"
							+ "8.\tGet a list of the students with the most credits.\n"
							+ "9.\tUpdate the register with the results of an exam from a file.\n"
							+ "10.\tExit and save.\n");
			
			int command = Prompt.askForNumber("Enter command (number between 1 and 10): ", 1, 10);

			switch (command) {
			case 1:
				Student stud = Prompt.askForName("Enter the name of the student you want to add: ");
				boolean added = miniladok.addStudent(stud);
				if (added) {
					System.out.println("Student successfully added!");
				} else {
					System.out.println("There's already a registered student with that name!");
				}
				break;
			case 2:
				Student s = Prompt.askForName("Enter the name of the student: ");
				String courseCode = Prompt.askForCourseCode("Enter the course code: ", 0);
				int grade = Prompt.askForNumber("Enter the grade(3-5): ", 3, 5);
				boolean registered = miniladok.registerCourseResult(s, courseCode, grade);
				if (registered) {
					System.out.println("Result successfully added!");
				} else {
					System.out.println("Check your input. Entered student or course don't exist!");
				}
				break;
			case 3:
				System.out.println("\nThere's " + miniladok.nbrOfStudents()
						+ " registered students in MiniLadok.");
				break;
			case 4:
				String courseCode2 = Prompt.askForCourseCode("Enter the course code: ", 0);
				int credits = Prompt.askForNumber("Enter the number of credits: ", 1, 30);
				boolean addCourse = miniladok.addCourse(courseCode2, credits);
				if (addCourse) {
					System.out.println("Course successfully added!");
				} else {
					System.out.println("There's already a course registered with that code!");
				}
				break;
			case 5:
				Student p = Prompt.askForName("Enter the name of the student: ");
				List<String> grades = miniladok.listStudentGrades(p);
				if (grades != null) {
					for (String t : grades)
						System.out.println(t);
				} else {
					System.out.println("There's no registered results for " + p + ".");
				}
				break;
			case 6:
				Student t = Prompt.askForName("Enter the name of the student: ");
				int totalcred = miniladok.listStudentCredits(t);
				System.out.println(t + " has " + totalcred + " credits.");
				break;
			case 7:
				int treshold = Prompt.askForNumber("Enter the number of credits: ", 0,
						50000);
				List<String> minPoints = miniladok.qualifiedStudents(treshold);
				if (!minPoints.isEmpty()) {
					for (String qs : minPoints) System.out.println(qs);
				} else {
					System.out.println("Maybe your standards are a wee bit high...");
				}
				break;
			case 8:
				int bestOnes = Prompt.askForNumber("Enter the number of students: ",
						1, miniladok.nbrOfStudents());
				List<String> best = miniladok.goodStudents(bestOnes);
				for (String q : best) System.out.println(q);
				break;
			case 9:
				String path = Prompt.askForCourseCode("Enter the path (or filename): ", 1);
				boolean load = recordKeeper.loadResults(path, miniladok);
				if (load) {
					System.out.println("Results successfully loaded!");
				} else {
					System.out.println("Check your input. Entered filename or path can't be read!");
				}
				break;
			case 10:
				finnish = true;
				break;
			}
			if (command != 10) Prompt.askForNumber("\nPress enter to continue.", -1, 0);
		}
	}
}
