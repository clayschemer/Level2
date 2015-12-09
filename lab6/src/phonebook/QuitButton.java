package phonebook;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class QuitButton extends JButton implements ActionListener {
	@SuppressWarnings("unused")
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public QuitButton(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Quit");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String fileName = JOptionPane.showInputDialog("Save book with the desired filename");
		if (fileName != null && fileName != "") {
			gui.save(fileName);
		}
		System.exit(0);
	}
}
