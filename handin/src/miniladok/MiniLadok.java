package miniladok;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Martin Rydin
 *
 */
public class MiniLadok {
	private Map<String, Integer> courses;
	private Map<Student, Results> students;
	
	/**The constructor which instantiates two maps containing the registered courses
	 * and students grades.
	 */
	public MiniLadok() {
		courses = new CourseMap();
		//courses = new HashMap<String, Integer>();
		students = new TreeMap<Student, Results>();
	}
	
	/**Adds a student to the register if not already present.
	 * @param s the student to be added
	 * @return true if student was added and false otherwise
	 */
	public boolean addStudent(Student s) {
		if (!students.containsKey(s)) {
			students.put(s, new Results());
			return true;
		}
		return false;
	}
	
	/**Adds a course to the register if not already present.
	 * @param courseCode the six character code of the course
	 * @param credits the number of credits for the course
	 * @return true if course was added, otherwise false
	 */
	public boolean addCourse(String courseCode, int credits) {
		if (!courses.containsKey(courseCode)) {
			courses.put(courseCode, credits);
			return true;
		}
		return false;
	}
	
	/** Register course result for a student if the student and data for 
	 * the course already exists i the register.
	 * @param s the Student to add result for
	 * @param courseCode the code for the course
	 * @param grade the achieved grade for the course
	 * @return true if the course and student exists, otherwise false
	 */
	public boolean registerCourseResult(Student s, String courseCode, int grade) {
		Results result = students.get(s);
		if (!courses.containsKey(courseCode) || result == null) {
			return false;
		}
		result.addResult(courseCode, grade, courses.get(courseCode));
		return true;
	}
	
	/**Returns the number of students in the register.
	 * @return the number of students in the register
	 */
	public int nbrOfStudents() {
		return students.size();
	}
	
	/**Returns a list with the given students grade.
	 * @param s the student to get the grades for
	 * @return the list with grades if s exists and null otherwise
	 */
	public List<String> listStudentGrades(Student s) {
		if (!students.containsKey(s)) return null;
		return students.get(s).getResults();
	}
	
	/** Returns a List<String> with students with credits >= minimumCredits.
	 * @param minimumCredits the given cut-off
	 * @return a List<String> with name and number o credits otherwise an empty list
	 */
	public List<String> qualifiedStudents(int minimumCredits) {
		List<String> list = new LinkedList<String>();
		int credits = 0;
		for (Map.Entry<Student, Results> entry : students.entrySet()) {
			credits = entry.getValue().getCredits();
			if (credits >= minimumCredits) {
				list.add(entry.getKey() + " " + credits);
			}
		}
		return list;
	}
	
	/**Returns a list with size = bestOnes where the output is ordered
	 * by the number of credits.
	 * @param bestOnes the best and brightest
	 * @return a list with the finest students
	 */
	public List<String> goodStudents(int bestOnes) {
		PriorityQueue<Entry<Student,Results>> heap = 
				new PriorityQueue<Entry<Student,Results>>(nbrOfStudents(), new CredComparator());
		Iterator<Entry<Student, Results>> itr = students.entrySet().iterator();
		List<String> list = new ArrayList<String>(bestOnes);
		while (itr.hasNext()) {
			heap.offer(itr.next());
		}
		Entry<Student, Results> entry;
		for (int i = 0; i < bestOnes; i++) {
			entry = heap.poll();
			list.add(i, entry.getValue().getCredits() + " " + entry.getKey());
		}
		return list;
	}
	
	private class CredComparator implements Comparator<Map.Entry<Student, Results>> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Entry<Student, Results> left,
				Entry<Student, Results> right) {
			if (left.getValue().compareTo(right.getValue()) < 0) {
				return 1;
			} else if (left.getValue().compareTo(right.getValue()) > 0) {
				return -1;
			}
			return left.getKey().compareTo(right.getKey());
		}
		
	}
	
	/**Returns the number of credits taken by student s.
	 * @param s the student
	 * @return the number of credits taken or 0 if student don't exist
	 */
	public int listStudentCredits(Student s) {
		if (!students.containsKey(s)) {
			return 0;
		}
		return students.get(s).getCredits();
	}
	
	/**Returns the number of credits to a course if present.
	 * @param courseCode the code of the course
	 * @return the number of credits or null if course don't exist
	 */
	public Integer getCourseCredits(String courseCode) {
		return courses.get(courseCode);
	}
	
	
	/**Returns an iterator over the students-Map.
	 * @return an iterator over the students-Map.
	 */
	public Iterator<Map.Entry<Student, Results>> studentIterator() {
		return students.entrySet().iterator();
	}
	
	/**Returns an iterator over the courses-Map.
	 * @return an iterator over the courses-Map.
	 */
	public Iterator<Map.Entry<String, Integer>> coursesIterator() {
		return courses.entrySet().iterator();
	}
}