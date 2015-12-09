package miniladok;

import java.util.*;

/**
 * @author Martin Rydin
 *
 */
public class Results implements Comparable<Results> {
	private Map<String, Integer> results;
	private int credits = 0;
	
	/**The Results constructor which instantiates a HashMap 
	 * as internal structure.
	 */
	public Results() {
		results = new HashMap<String, Integer>();
	}
	
	/**Adds a result for a course and add credits to the students total amount
	 * if the course s not already present and the grade is higher than the previous one
	 * @param courseCode the code of the course
	 * @param grade the achieved grade
	 * @param courseCredits the number of credits the course is worth
	 */
	public void addResult(String courseCode, int grade, int courseCredits) {
		Integer oldGrade = results.get(courseCode);
		if (oldGrade == null) {
			credits += courseCredits;
		} else if (oldGrade > grade) {
			return;
		}
		results.put(courseCode, new Integer(grade));
	}
	
	/**Returns the achieved grade for a particular course.
	 * @param courseCode the code of the course
	 * @return the achieved grade or null if there's none
	 */
	public Integer getGrade(String courseCode) {
		return results.get(courseCode);
	}
	
	/**Returns a String representation of the Results
	 * @return list with results or null if there are no results
	 */
	public List<String> getResults() {
		if (results.isEmpty()) return null;
		List<String> list = new LinkedList<String>();
		for (Map.Entry<String, Integer> entry : results.entrySet()) {
			list.add(entry.getKey() + " " + entry.getValue());
		}
		return list;
	}
	
	/**Returns the total amount of credits taken
	 * @return the number of credits taken
	 */
	public int getCredits() {
		return credits;
	}

	public int compareTo(Results other) {
		if (credits > other.credits) {
			return 1;
		} else if (credits < other.credits) {
			return -1;
		}
		return 0;
	}
}
