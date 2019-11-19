package view.panel;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.DialogListener;
import controller.ExitMenuListener;
import controller.SpinListener;
import controller.StudentInfoMenuListener;
import view.MainFrame;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	private JButton spinButton = new JButton("SPIN WHEEL");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("MENU");
	private JMenuItem studentInfo = new JMenuItem("Student Information");
	private JMenuItem exit = new JMenuItem("Exit");

	public ToolBar(MainFrame mf) {
		menuBar.add(menu);
		menu.add(studentInfo);
		menu.add(exit);

		add(menuBar);

		studentInfo.addActionListener(new StudentInfoMenuListener());
		exit.addActionListener(new ExitMenuListener());

		JButton addButton = new JButton("ADD PLAYER");
		JButton removeButton = new JButton("REMOVE PLAYER");

		add(addButton);
		add(removeButton);
		add(spinButton);

		addButton.addActionListener(new DialogListener(mf));
		removeButton.addActionListener(new DialogListener(mf));
		spinButton.addActionListener(new SpinListener(mf));

	}

	public JButton getSpinButton() {
		return spinButton;
	}
}
