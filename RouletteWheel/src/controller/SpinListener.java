package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.MainFrame;
import view.interfaces.GameEngineCallback;
import view.panel.StatusBar;

public class SpinListener implements ActionListener {
	private static final int INITIAL_DELAY = 1;
	private static final int FINAL_DELAY = 500;
	private static final int DELAY_INCREMENT = 25;

	private GameEngine model;
	private StatusBar stp;
	private MainFrame mf;

	public SpinListener(MainFrame mf) {
		this.mf = mf;
		model = mf.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stp = mf.getStatusBar();

		if (model.getAllPlayers().size() == 0) {
			JOptionPane.showMessageDialog(null, "No player exists!", "Invalid Action", JOptionPane.ERROR_MESSAGE);
		} else {
			GameEngineCallback GEC = new GameEngineCallbackImpl();
			GameEngineCallback GECGUI = new GameEngineCallbackGUI(mf);

			model.getWheelSlots();
			model.addGameEngineCallback(GECGUI);
			model.addGameEngineCallback(GEC);

			new Thread() {
				@Override
				public void run() {
					model.spin(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
					model.removeGameEngineCallback(GECGUI);
					model.removeGameEngineCallback(GEC);
				}
			}.start();
			stp.updateWheelStatus(true);
		}
	}
}
