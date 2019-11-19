package view.component;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Field extends JPanel {
	private JTextField textField = new JTextField(10);

	public Field(String label) {
		setLayout(new GridLayout());

		JPanel labelPanel = new JPanel();
		labelPanel.add(new JLabel(label));

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.add(textField);

		add(labelPanel);
		add(textFieldPanel);
	}

	public JTextField getTextField() {
		return textField;
	}
}
