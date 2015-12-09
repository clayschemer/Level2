package phonebook;
import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;

@SuppressWarnings("serial")
public class FindNumbersMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public FindNumbersMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find number(s)");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 Iterator<String> itr = phoneBook.findNumber(name).iterator();
		 if (!itr.hasNext()) {
				gui.setMessage("There's noone in the phonebook with the name " + name);
			} else {
				gui.setMessage("The name " + name + " is registered with:\n");
				while (itr.hasNext()) {
					gui.setList(itr.next());
				}
			}
	 }
}