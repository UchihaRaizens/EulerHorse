package view;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import controller.HorseController;
import model.EulerHorse;

@SuppressWarnings("serial")
public class EulerHorseWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tfSize;
	private JTextField tfXPosition;
	private JTextField tfYPosition;

	/**
	 * Create the frame.
	 */
	public EulerHorseWindow(EulerHorse horse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSize = new JLabel("Size of chessboard");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSize.setBounds(0, 0, 142, 20);
		contentPane.add(lblSize);
		
		tfSize = new JTextField();
		tfSize.setBounds(0, 23, 70, 20);
		contentPane.add(tfSize);
		tfSize.setColumns(10);
		
		JLabel lblStart = new JLabel("Start position");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStart.setBounds(0, 47, 142, 20);
		contentPane.add(lblStart);
		
		tfXPosition = new JTextField();
		tfXPosition.setBounds(0, 73, 40, 20);
		contentPane.add(tfXPosition);
		tfXPosition.setColumns(10);
		
		tfYPosition = new JTextField();
		tfYPosition.setBounds(56, 73, 40, 20);
		contentPane.add(tfYPosition);
		tfYPosition.setColumns(10);
		
		JTextPane tpSolution = new JTextPane();
		tpSolution.setBounds(0, 222, 250, 239);
		contentPane.add(tpSolution);
		
		JLabel lblSolution = new JLabel("Solution");
		lblSolution.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSolution.setBounds(0, 195, 149, 20);
		contentPane.add(lblSolution);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(0, 108, 89, 23);
		contentPane.add(btnStart);
		
		JTextPane tpSequence = new JTextPane();
		tpSequence.setBounds(274, 0, 210, 461);
		contentPane.add(tpSequence);
		
		JScrollPane jsp = new JScrollPane(tpSequence);
		jsp.setBounds(274, 0, 210, 461);
		this.getContentPane().add(jsp);
		btnStart.addActionListener(new HorseController(tfSize,tfXPosition,tfYPosition,horse,tpSolution,tpSequence){
			
		});
		
		
	}
}
