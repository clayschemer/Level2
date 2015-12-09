package phonebook;

import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;

@SuppressWarnings("serial")
public class FindNamesMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public FindNamesMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find name(s)");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String number = JOptionPane.showInputDialog("Enter number");
		Iterator<String> itr = phoneBook.findNames(number).iterator();
		if (!itr.hasNext()) {
			gui.setMessage("There's noone in the phonebook with the number "
					+ number);
		} else {
			gui.setMessage("The number " + number + " is registered with:\n");
			while (itr.hasNext()) {
				gui.setList(itr.next());
			}
		}
	}
}