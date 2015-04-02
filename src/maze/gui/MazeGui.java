package maze.gui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

@SuppressWarnings("serial")
public class MazeGui extends JPanel implements KeyListener{

	private JFrame frame;
	private JPanel panel;
	private JButton newGame;
	private JButton exit;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGui window = new MazeGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Labirinto");
		frame.setBounds(100, 100, 607, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 37, 591, 381);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 591, 39);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Play Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,
						"Start Game", "Start Game?", JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
						startMaze();
				}
				
			}
		});
		btnNewButton.setBounds(40, 0, 140, 39);
		panel_1.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(422, 0, 135, 39);
		panel_1.add(btnExit);
		
		JButton btnSetup = new JButton("Setup");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog setupWindow=new JDialog();
				setupWindow.getContentPane().setLayout(new GridLayout(4,2));
				setupWindow.setBounds(20, 20, 300, 300);
				setupWindow.setModal(true);
				
				setupWindow.getContentPane().add(new JLabel("Nr Dragons:"));
				
				JTextField nrDragoes=new JTextField("2");
				setupWindow.getContentPane().add(nrDragoes);
				
				setupWindow.getContentPane().add(new JLabel("Board Size:"));
				
				JTextField boardSize=new JTextField("15");
				setupWindow.getContentPane().add(boardSize);
				
				setupWindow.getContentPane().add(new JLabel("Type Dragon:"));
				ButtonGroup group = new ButtonGroup();
				JRadioButton parado =new JRadioButton("Parado");
				parado.setSelected(true);
				JRadioButton aleatorio=new JRadioButton("Aleatorio");
				JRadioButton aleatorioDormir=new JRadioButton("Aleatorio+Dormir");
				JPanel teste =new JPanel();
				group.add(parado);
				teste.add(parado);
				group.add(aleatorio);
				teste.add(aleatorio);
				group.add(aleatorioDormir);
				teste.add(aleatorioDormir);
				
				setupWindow.add(teste);
				
				setupWindow.setVisible(true);
				
			}
		});
		btnSetup.setBounds(237, -1, 121, 41);
		panel_1.add(btnSetup);
		frame.addKeyListener(this);

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			System.out.print("cenas");
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void startMaze(){
		
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
