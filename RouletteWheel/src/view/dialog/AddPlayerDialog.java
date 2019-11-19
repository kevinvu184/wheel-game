package view.dialog;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.AddPlayerListener;
import view.MainFrame;
import view.component.Field;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {
	private final int WIDTH = 250;
	private final int HEIGHT = 170;
	private final int X_COORDINATOR = 450;
	private final int Y_COORDINATOR = 250;

	private JButton addButton = new JButton("ADD");
	private Field playerNameField = new Field("Name");
	private Field playerIdField = new Field("ID");
	private Field initialMoneyField = new Field("Initial Money");

	public AddPlayerDialog(MainFrame mf) {
		super(mf);
		setTitle("Add Player");
		setBounds(X_COORDINATOR, Y_COORDINATOR, WIDTH, HEIGHT);
		setResizable(false);
		setModal(true);

		JPanel addPlayerPanel = new JPanel();
		addPlayerPanel.setLayout(new BoxLayout(addPlayerPanel, BoxLayout.Y_AXIS));
		addPlayerPanel.add(playerNameField);
		addPlayerPanel.add(playerIdField);
		addPlayerPanel.add(initialMoneyField);

		JPanel buttonPanel = new JPanel();
		addButton.addActionListener(new AddPlayerListener(mf, this));
		buttonPanel.add(addButton);

		add(addPlayerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public Field getPlayerNamePromptField() {
		return playerNameField;
	}

	public Field getPlayerIdPromtField() {
		return playerIdField;
	}

	public Field getInitialMoneyPromtField() {
		return initialMoneyField;
	}
}
