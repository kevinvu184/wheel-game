package view.dialog;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.RemovePlayerListener;
import view.MainFrame;
import view.component.Field;

@SuppressWarnings("serial")
public class RemovePlayerDialog extends JDialog {
	private final int WIDTH = 250;
	private final int HEIGHT = 100;
	private final int X_COORDINATOR = 450;
	private final int Y_COORDINATOR = 250;

	private JButton removeButton = new JButton("REMOVE");
	private Field playerIdField = new Field("ID");

	public RemovePlayerDialog(MainFrame mf) {
		super(mf);
		setTitle("Remove Player");
		setBounds(X_COORDINATOR, Y_COORDINATOR, WIDTH, HEIGHT);
		setResizable(false);
		setModal(true);

		JPanel removePlayerPanel = new JPanel();
		removePlayerPanel.setLayout(new BoxLayout(removePlayerPanel, BoxLayout.Y_AXIS));
		removePlayerPanel.add(playerIdField);

		JPanel buttonPanel = new JPanel();
		removeButton.addActionListener(new RemovePlayerListener(mf, this));
		buttonPanel.add(removeButton);

		add(removePlayerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public Field getPlayerIdPromptField() {
		return playerIdField;
	}
}
