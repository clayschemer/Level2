package phonebook;
import java.io.Serializable;
import java.util.*;

public class PhoneBook implements Serializable {
	private static final long serialVersionUID = 1809326345354657211L;
	private Map<String, LinkedList<String>> phoneBook;
	private int size;

	public PhoneBook() {
		phoneBook = new TreeMap<String, LinkedList<String>>();
		size = 0;
	}

	/**
	 * Associates the specified number with the specified name in this phone
	 * book. post: If the specified name is not present in this phone book, the
	 * specified name is added and associated with the specified number.
	 * Otherwise the specified number is added to the set of number associated
	 * with name.
	 * 
	 * @param name
	 *            The name for which a phone number is to be added
	 * @param number
	 *            The number associated with the specified name
	 */
	public void put(String name, String number) {
		if (phoneBook.containsKey(name)) {
			LinkedList<String> list = phoneBook.get(name);
			list.add(number);
		} else {
			LinkedList<String> list = new LinkedList<String>();
			list.add(number);
			phoneBook.put(name, list);
			size++;
		}

	}

	/**
	 * Removes the the specified name from this phone book. post: If the
	 * specified name is present in this phone book, it is removed. Otherwise
	 * this phone book is unchanged.
	 * 
	 * @param name
	 *            The name to be removed.
	 * @return true if the specified name was present.
	 */
	public boolean remove(String name) {
		if (phoneBook.remove(name) != null) {
			size--;
			return true;
		}
		return false;
	}

	/**
	 * Retrieves a list of phone numbers for the specified name. If the
	 * specified name is not present in this phone book an empty list is
	 * returned.
	 * 
	 * @param name
	 *            The name whose associated phone numbers are to be returned
	 * @return The phone numbers associated with the specified name
	 */
	public List<String> findNumber(String name) {
		return phoneBook.get(name);
	}

	/**
	 * Retrieves a list of names associated with the specified phone number. If
	 * the specified number is not present in this phone book an empty list is
	 * returned.
	 * 
	 * @param number
	 *            The number for which the set of associated names is to be
	 *            returned.
	 * @return The list of names associated with the specified number.
	 */
	public List<String> findNames(String number) {
		List<String> names = new LinkedList<String>();
		Iterator<Map.Entry<String, LinkedList<String>>> itr = phoneBook
				.entrySet().iterator();
		Map.Entry<String, LinkedList<String>> cur;
		while (itr.hasNext()) {
			cur = itr.next();
			if (cur.getValue().contains(number)) {
				names.add(cur.getKey());
			}
		}
		return names;
	}

	/**
	 * Retrieves the set of all names present in this phone book. The set's
	 * iterator will return the names in ascending order
	 * 
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		return phoneBook.keySet();
	}

	/**
	 * Returns true if this phone book is empty
	 * 
	 * @return true if this phone book is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of names in this phone book
	 * 
	 * @return The number of names in this phone book
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();
		book.put("Martin Rydin", "070-2399158");
		book.put("Vicki Blomen", "070-8256766");
		book.put("Tommy G", "46and2");
		book.put("Martin Rydin", "0380-14189");
		book.put("Vicki Blomen", "070-2399158");
		System.out.println(book.size() + ": ska va 3");
		Object[] names = book.names().toArray();
		List<String> list = book.findNames("070-2399158");
		Iterator<String> itr = list.iterator();
		for (Object s : names) {
			System.out.println(s);
		}
		while (itr.hasNext()) {
			System.out.println(itr.next() + " är kopplat till 070-2399158");
		}
		list = book.findNumber("Martin Rydin");
		itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next() + " är kopplat till Martin Rydin");
		}
	}
}
