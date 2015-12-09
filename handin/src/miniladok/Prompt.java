package miniladok;

import java.util.Scanner;

/**
 * Class used to prompt and sanitize input from user.
 * 
 * @author Martin Rydin
 */
public class Prompt {
	private static Scanner keyIn = new Scanner(System.in);

	/**Method used to ask for a number nbr that has to be in the specified
	 * interval [lower, upper] and of type int otherwise the question is
	 * asked again.
	 * @param ask ask the question to be printed to the user
	 * @param lower the lowest permissible answer
	 * @param upper the highest permissible answer
	 * @return the number entered by the user
	 */
	static int askForNumber(String ask, int lower, int upper) {
		boolean ok = false;
		int nbr = 0;
		System.out.print(ask);
		while (!ok) {
			try {
				String oneLine = keyIn.nextLine();
				if (lower > -1) nbr = Integer.parseInt(oneLine.trim());
				if (lower < 0 || (lower <= nbr && nbr <= upper)) {
					ok = true;
				} else {
					System.out.println("Invalid response.");
					System.out.println("Enter number between " + lower + " and " + upper + ".");
					System.out.print(ask);
				}

			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("Not a number!");
				System.out.print(ask);
			}
		}
		System.out.println();
		return nbr;
	}

	/**Method that asks a question where a name is supposed
	 * to be the answer. Asks again if the format isn't "firstname lastname".
	 * @param ask the question to be printed to the user
	 * @return a Student object
	 */
	static Student askForName(String ask) {
		boolean ok = false;
		String firstName = "", lastName = "";
		Scanner lineScanner;
		System.out.print(ask);
		while (!ok) {
			try {
				String oneLine = keyIn.nextLine();
				lineScanner = new Scanner(oneLine);
				firstName = lineScanner.next();
				lastName = lineScanner.next();
				ok = true;
			} catch (Exception e) {
				System.out.println();
				System.out.println("Not a valid name!");
				System.out.print(ask);
			}
		}
		System.out.println();
		return new Student(firstName, lastName);
	}

	/**Method that asks for a course code (if type = 0) otherwise
	 * it takes any other string.
	 * @param ask the question to be printed to the user
	 * @param type the type of input (0= coursecode)
	 * @return a String of the answer.
	 */
	static String askForCourseCode(String ask, int type) {
		boolean ok = false;
		String oneLine = "";
		System.out.print(ask);
		while (!ok) {
			try {
				oneLine = keyIn.nextLine();
				if (oneLine.length() != 6 && type == 0) throw new Exception();
				ok = true;
			} catch (Exception e) {
				System.out.println();
				System.out.println("Not a valid course code!");
				System.out.print(ask);
			}
		}
		System.out.println();
		return oneLine.trim();
	}

}
