package phonebook;
import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name");
		if (phoneBook.remove(name)) {
			gui.setMessage(name + " was removed.");
		} else {
			gui.setMessage("No user with the name " + name + " exists.");
		}
	 }
}
