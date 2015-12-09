package phonebook;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AddMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public AddMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Add");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int oldSize = phoneBook.size();
		String name = JOptionPane.showInputDialog("Enter name");
		String number = JOptionPane.showInputDialog("Enter Phone-number");
		if (name != null && number != null) {
			phoneBook.put(name, number);
			if (phoneBook.size() > oldSize) {
				gui.setMessage("A new entry for the name " + name
						+ " was created with the number " + number);
			} else {
				gui.setMessage("The number " + number + " was added to " + name);
			}
		} else {
			gui.setMessage("No name and/or number was specified");
		}
	}
}
