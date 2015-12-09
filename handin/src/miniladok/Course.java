package miniladok;

public class Course implements Comparable<Course> {
	private String courseCode;
	private int credits;
	
	public Course(String courseCode, int credits) {
		this.courseCode = courseCode;
		this.credits = credits;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			return this.compareTo((Course) obj) == 0;
		}
		return false;
	}
	
	@Override
	public int compareTo(Course other) {
		return courseCode.compareToIgnoreCase(other.courseCode);
	}

	public String toString() {
		return courseCode + "" + credits;
	}
}
