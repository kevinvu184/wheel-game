package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ExitMenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Good Bye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

}
