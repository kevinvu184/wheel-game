package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class StudentInfoMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Student name: Khoa Vu Duy Anh\nStudent ID: s3678490", "Student Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
