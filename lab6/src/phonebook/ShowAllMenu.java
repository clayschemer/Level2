package phonebook;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ShowAllMenu extends JButton implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public ShowAllMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Show all");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		gui.setMessage("");
		for (String s : phoneBook.names()) {
			gui.setList(s);
			for (String t : phoneBook.findNumber(s)) {
				gui.setList(t);
			}
			gui.setList("");
		}
	}
}
