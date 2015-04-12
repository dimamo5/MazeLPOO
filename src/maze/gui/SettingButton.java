package maze.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SettingButton extends JButton implements ActionListener, KeyListener {
	private Settings settings;
	
	private KeyEvent keyPressed;
<<<<<<< HEAD
	private JButton buttonPressed;
	
	
=======
	private JDialog setupWindow;
	JButton upKey;
	JButton lastPressed;

>>>>>>> 053cdd1d8c7c408e22dbf863587c02f2a8205ab0
	public SettingButton(String n, Settings settings) {
		this.setText(n);
		this.addActionListener(this);
		this.settings = settings;
	}

	public Settings getSettings() {
		return settings;
	}

	public void actionPerformed(ActionEvent e) {
		setupWindow = new JDialog();
		JSlider mazeSize = new JSlider(JSlider.HORIZONTAL, 11, 21, settings.getMazeSize());
		JSlider nrDragons = new JSlider(JSlider.HORIZONTAL, 1, 5, settings.getNumDragons());

		mazeSize.setMinorTickSpacing(2);
		mazeSize.setPaintTicks(true);
		mazeSize.setPaintLabels(true);
		mazeSize.setLabelTable(mazeSize.createStandardLabels(2));

		mazeSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (mazeSize.getValue() % 2 == 0) {
					mazeSize.setValue(mazeSize.getValue() + 1);
				}
			}
		});

		nrDragons.setPaintTicks(true);
		nrDragons.setPaintLabels(true);
		nrDragons.setLabelTable(nrDragons.createStandardLabels(1));
		// ==================

		setupWindow.getContentPane().setLayout(new GridLayout(5, 2));
		setupWindow.setBounds(50, 50, 600, 650);
		setupWindow.setModal(true);

		setupWindow.getContentPane().add(new JLabel("Nr Dragons:"));
		setupWindow.getContentPane().add(nrDragons);

		setupWindow.getContentPane().add(new JLabel("Board Size:"));
		setupWindow.getContentPane().add(mazeSize);

		setupWindow.getContentPane().add(new JLabel("Maze type:"));
		ButtonGroup group_mtype = new ButtonGroup();
		JRadioButton predefinido = new JRadioButton("Aleatorio");
		JRadioButton custom = new JRadioButton("Aleatorio");

		if (settings.getMazeType() == 1) {
			predefinido.setSelected(false);
			custom.setSelected(true);
		} else {
			predefinido.setSelected(true);
			custom.setSelected(false);
		}

		JPanel teste2 = new JPanel();

		group_mtype.add(custom);
		teste2.add(custom);
		group_mtype.add(predefinido);
		teste2.add(predefinido);

		setupWindow.getContentPane().add(teste2);

		setupWindow.getContentPane().add(new JLabel("Type Dragon:"));
		ButtonGroup group = new ButtonGroup();
		JRadioButton parado = new JRadioButton("Parado");
		JRadioButton aleatorio = new JRadioButton("Aleatorio");
		JRadioButton aleatorioDormir = new JRadioButton("Aleatorio+Dormir");

		if (settings.getTypeDragons() == 'p') {
			parado.setSelected(true);
		} else if (settings.getTypeDragons() == 'a') {
			aleatorio.setSelected(true);
		} else if (settings.getTypeDragons() == 'z') {
			aleatorioDormir.setSelected(true);
		}

		JPanel teste = new JPanel();
		group.add(parado);
		teste.add(parado);
		group.add(aleatorio);
		teste.add(aleatorio);
		group.add(aleatorioDormir);
		teste.add(aleatorioDormir);

		setupWindow.getContentPane().add(teste);

		setupWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				settings.setMazeSize(mazeSize.getValue());
				settings.setNumDragons(nrDragons.getValue());
				if (parado.isSelected()) {
					settings.setTypeDragons('p');
				} else if (aleatorio.isSelected()) {
					settings.setTypeDragons('a');
				} else if (aleatorioDormir.isSelected()) {
					settings.setTypeDragons('z');
				}
				if (custom.isSelected()) {
					settings.setMazeType(1);
				} else if (predefinido.isSelected())
					settings.setMazeType(0);
			}
		});

		setupWindow.add(new JLabel("Key Bindings"));
		
		setupWindow.add(createKeyBidings());

		setupWindow.setVisible(true);

		setupWindow.addKeyListener(this);
		setupWindow.requestFocus();

	}

	public JPanel createKeyBidings() {
		JPanel buttonBox = new JPanel();

		upKey = new JButton("UP=" + KeyEvent.getKeyText(settings.getUp()));
		JButton downKey = new JButton("DOWN=" + KeyEvent.getKeyText(settings.getDown()));
		JButton leftKey = new JButton("LEFT=" + KeyEvent.getKeyText(settings.getLeft()));
		JButton rigthKey = new JButton("RIGTH=" + KeyEvent.getKeyText(settings.getRight()));
		JButton shoot = new JButton("SHOOT");

		upKey.addKeyListener(this);
		downKey.addKeyListener(this);
		leftKey.addKeyListener(this);
		rigthKey.addKeyListener(this);
		shoot.addKeyListener(this);

		upKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				System.out.println(settings.getUp());
				if (keyPressed != null) {
					settings.setUp(keyPressed.getKeyCode());
					upKey.setText("UP=" + keyPressed.getKeyChar());
				}
				
				System.out.println(settings.getUp());				
=======
				lastPressed = upKey;

				// if (keyPressed != null) {
				// settings.setUp(keyPressed.getKeyCode());
				// upKey.setText("UP=" + keyPressed.getKeyChar());
				// }
>>>>>>> 053cdd1d8c7c408e22dbf863587c02f2a8205ab0
			}
		});
		
		buttonBox.add(upKey);
		buttonBox.add(downKey);
		buttonBox.add(leftKey);
		buttonBox.add(rigthKey);
		buttonBox.add(shoot);

		return buttonBox;
	}

	public KeyEvent getKey() {
		return keyPressed;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (lastPressed == null) {
			return;
		} else {
			settings.setUp(e.getKeyCode());
			upKey.setText("UP=" + e.getKeyChar());
		}
		keyPressed = e;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
