package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MainFrame;
import view.dialog.AddPlayerDialog;
import view.dialog.RemovePlayerDialog;

public class DialogListener implements ActionListener {
	private MainFrame mf;

	public DialogListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText().equals("ADD PLAYER")) {
			new AddPlayerDialog(mf);
		} else if (((JButton) e.getSource()).getText().equals("REMOVE PLAYER")) {
			new RemovePlayerDialog(mf);
		}
	}
}
