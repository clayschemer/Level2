package phonebook;
import javax.swing.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

@SuppressWarnings("serial")
public class PhoneBookGUI extends JFrame {
	private PhoneBook phoneBook;
	private JTextArea messageArea;
		
	public PhoneBookGUI(PhoneBook pb) {
		super("PhoneBook");
		
		String fileName = JOptionPane.showInputDialog("Enter name of file to load");
		if (fileName != null && fileName != "") {
			load(fileName);
		} else {
			phoneBook = pb;
		}
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		Locale.setDefault(new Locale("en"));
		/* To avoid hardcoded Swedish text on OptionPane dialogs */
		UIManager.put("OptionPane.cancelButtonText","Cancel");
		
		setLayout(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);
		editMenu.add(new AddMenu(phoneBook,this));
		editMenu.add(new RemoveMenu(phoneBook,this));
		
		JMenu findMenu = new JMenu("Find");
		menubar.add(findMenu);
		findMenu.add(new FindNumbersMenu(phoneBook,this));
		findMenu.add(new FindNamesMenu(phoneBook,this));
		
		JMenu viewMenu = new JMenu("View");
		menubar.add(viewMenu);
		viewMenu.add(new ShowAllMenu(phoneBook,this));
		
		JPanel southPanel = new JPanel();
		messageArea = new JTextArea(4,25);
		messageArea.setEditable(false);
		southPanel.add(new JScrollPane(messageArea));
		southPanel.add(new QuitButton(phoneBook,this));
		add(southPanel,BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	public void setMessage(String s) {
		messageArea.setText(s);
	}
	
	public void setList(String part) {
		messageArea.append(part + "\n");
	}
	
	public void load(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			phoneBook = (PhoneBook) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Phonebook load() fail.");
			System.exit(1);
		}
	}
	
	public void save(String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(phoneBook);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
