package miniladok;

/**
 * @author Martin Rydin
 *
 */
public class Student implements Comparable<Student> {
	private String firstName, lastName;
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			return this.compareTo((Student) obj) == 0;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Student s) {
		int diff = lastName.compareToIgnoreCase(s.lastName);
		if (diff == 0) {
			return firstName.compareToIgnoreCase(s.firstName);
		}
		return diff;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return firstName + " " + lastName;
	}
	

}
