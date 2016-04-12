package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.EulerHorse;
import view.EulerHorseWindow;

public class HorseController implements ActionListener {
	private JTextField tfSize;
	private JTextField tfXPosition;
	private JTextField tfYPosition;
	private JTextPane tpSolution;
	private JTextPane tpSequence;
	private EulerHorse horse;

	/*
	 * Contructor
	 */
	public HorseController(JTextField tfSize, JTextField tfXPosition, JTextField tfYPostition, EulerHorse horse,
			JTextPane tpSolution, JTextPane tpSequence) {
		this.tfSize = tfSize;
		this.tfXPosition = tfXPosition;
		this.tfYPosition = tfYPostition;
		this.horse = horse;
		this.tpSolution = tpSolution;
		this.tpSequence = tpSequence;
	}

	/*After press button*/
	public void actionPerformed(ActionEvent e) {
		Thread start = new Thread() {
			public void run() {
				tpSolution.setText("");
				tpSequence.setText("");
				horse.setSize(Integer.parseInt(tfSize.getText())); //get information from GUI (size, start position)
				Integer x = Integer.parseInt(tfXPosition.getText()) - 1;
				Integer y = Integer.parseInt(tfYPosition.getText()) - 1;
				horse.createChessBoard();				//start find solutions
				horse.setStartPoint(x, y);
				if (horse.depthFirstSearch(x, y)) {    //find solution
					updateTextSolution();
					updateTextSequence();
				} else {
					if (horse.getIteration() >= 100000000) { //to much iteration
						tpSequence.setText("Hladanie prekrocilo limit");
					} else {								//no exist solution	
						tpSequence.setText("Neexistuje riesenie");
					}
				}

			}
		};
		start.start();
	}
	
	/**
	 * Method set chess board text in JTextPane
	 */
	public void updateTextSolution() {
		tpSolution.setText(horse.testArray());
	}

	/**
	 * Method set sequence text in JTextPane 
	 */
	public void updateTextSequence() { 
		tpSequence.setText(horse.findSequence());
	}

	public static void main(String[] args) { //main
		EulerHorse horse = new EulerHorse();
		EulerHorseWindow window = new EulerHorseWindow(horse);
		window.setVisible(true);

	}
}
