package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.GameEngineImpl;
import view.panel.StatusBar;
import view.panel.ToolBar;
import view.panel.WestPanel;
import view.panel.WheelPanel;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private final int WIDTH = 950;
	private final int HEIGHT = 750;
	private final GameEngineImpl model = new GameEngineImpl();
	
	private ToolBar toolBar;
	private WheelPanel wheelPanel;
	private WestPanel westPanel;
	private StatusBar statusBar;
	
	private JPanel contentPane = new JPanel();

	public MainFrame() {
		toolBar = new ToolBar(this);
		wheelPanel = new WheelPanel();
		westPanel = new WestPanel(this);
		statusBar = new StatusBar();
		
		setTitle("Roulette Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(wheelPanel, BorderLayout.CENTER);
		contentPane.add(statusBar, BorderLayout.SOUTH);
	}

	public GameEngineImpl getModel() {
		return model;
	}

	public ToolBar getToolBarPanel() {
		return toolBar;
	}

	public WheelPanel getWheelPanel() {
		return wheelPanel;
	}

	public WestPanel getWestPanel() {
		return westPanel;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
}
