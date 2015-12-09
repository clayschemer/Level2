package miniladok;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class RecordKeeper {
	
	public RecordKeeper() {}
	
	/**Method used to load the stored data for MiniLadok
	 * from a text file.
	 * @param fileName the .txt-file to load
	 */
	public void load(String fileName, MiniLadok miniladok) {
		
		try {
			Scanner in = new Scanner(new FileInputStream(fileName));
			String line = "ko", courseCode;
			int credits, grade;
			Scanner lineScanner;
			while (in.hasNextLine() && !line.equals("#")) {
				line = in.nextLine();
				if (!line.equals("#")) {
					lineScanner = new Scanner(line);
					courseCode = lineScanner.next();
					credits = lineScanner.nextInt();
					miniladok.addCourse(courseCode, credits);
				}
			}
//			miniladok.printCourses();
			String firstName, lastName;
			while (in.hasNextLine()) {
				line = in.nextLine();
				lineScanner = new Scanner(line);
				lastName = lineScanner.next();
				firstName = lineScanner.next();
				miniladok.addStudent(new Student(firstName, lastName));
				line = in.nextLine();
				while (!line.equals("#")) {
					lineScanner = new Scanner(line);
					courseCode = lineScanner.next();
					grade = lineScanner.nextInt();
					miniladok.registerCourseResult(new Student(firstName,
							lastName), courseCode, grade);
					line = in.nextLine();
				}
			}
//			miniladok.printResults();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fail i Menu.load()");
		}
	}

	/**Method called to load the results of an exam into MiniLadok
	 * the file specified has to be of .txt format.
	 * @param fileName the name of the file to read
	 * @return true if file could be read and false otherwise
	 */
	public boolean loadResults(String resultsFileName, MiniLadok miniladok) {
		Iterator<Map.Entry<Student, Results>> studItr = miniladok.studentIterator();
		Map.Entry<Student, Results> studEntry = null;
		boolean found = false;
		try {
			Scanner in = new Scanner(new FileInputStream(resultsFileName));
			String courseCode, firstName, lastName;
			int grade; 
			Scanner lineScanner;
			String line = in.nextLine();
			lineScanner = new Scanner(line);
			courseCode = lineScanner.next();
			Integer credits = miniladok.getCourseCredits(courseCode);
			if (credits == null) {
				System.out.println("The course is not registered!");
				return false;
			}
			while (in.hasNextLine()) {
				line = in.nextLine();
				lineScanner = new Scanner(line);
				lastName = lineScanner.next();
				firstName = lineScanner.next();
				Student s = new Student(firstName, lastName);
				line = in.nextLine();
				lineScanner = new Scanner(line);
				grade = lineScanner.nextInt();
				while (studItr.hasNext() && !found) {
					studEntry = studItr.next();
					if (s.equals(studEntry.getKey())) {
						studEntry.getValue().addResult(courseCode, grade, credits);
						found = true;
					}
				}
				found = false;
			}
		} catch (Exception e) {
			System.out.println("Error while reading results!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**Saves the content of MiniLadok to a file with the specified name.
	 * @param fileName the name of the file to save to
	 */
	public void save(String fileName, MiniLadok miniladok) {
		Iterator<Map.Entry<String, Integer>> courses = miniladok.coursesIterator();
		Iterator<Map.Entry<Student, Results>> students = miniladok.studentIterator();
		Map.Entry<String, Integer> courseEntry;
		Map.Entry<Student, Results> curEntry;
		Iterator<String> result;
		Student s = null;
		try {
			PrintWriter out = new PrintWriter(new File(fileName));
			while (courses.hasNext()) {
				courseEntry = courses.next();
				out.println(courseEntry.getKey() + " " + courseEntry.getValue());
			}
			out.println("#");
			while (students.hasNext()) {
				curEntry = students.next();
				s = curEntry.getKey();
				out.println(s);
				if (curEntry.getValue().getCredits() > 0) {
					result = curEntry.getValue().getResults().iterator();
					while (result.hasNext()) {
						out.println(result.next());
					}
				}
				out.println("#");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Menu.save()-fail");
		}
	}
}
