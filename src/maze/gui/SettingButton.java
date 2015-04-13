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
	private JDialog setupWindow;
	private JButton lastButtonPressed;

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
		JSlider mazeSize = new JSlider(JSlider.HORIZONTAL, 11, 25, settings.getMazeSize());
		JSlider nrDragons = new JSlider(JSlider.HORIZONTAL, 1, 10, settings.getNumDragons());

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

		setupWindow.getContentPane().setLayout(new GridLayout(4, 2));
		setupWindow.setBounds(50, 50, 500, 500);
		setupWindow.setModal(true);

		setupWindow.getContentPane().add(new JLabel("Nr Dragons:"));
		setupWindow.getContentPane().add(nrDragons);

		setupWindow.getContentPane().add(new JLabel("Board Size:"));
		setupWindow.getContentPane().add(mazeSize);

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
		buttonBox.setLayout(new GridLayout(4, 2));

		JButton upKey = new JButton("UP=" + KeyEvent.getKeyText(settings.getUp()));
		upKey.setName("UP");
		JButton downKey = new JButton("DOWN=" + KeyEvent.getKeyText(settings.getDown()));
		downKey.setName("DOWN");
		JButton leftKey = new JButton("LEFT=" + KeyEvent.getKeyText(settings.getLeft()));
		leftKey.setName("LEFT");
		JButton rigthKey = new JButton("RIGTH=" + KeyEvent.getKeyText(settings.getRight()));
		rigthKey.setName("RIGTH");
		JButton shootUp = new JButton("SHOOT UP=" + KeyEvent.getKeyText(settings.getShootUp()));
		shootUp.setName("SHOOT UP");
		JButton shootDown = new JButton("SHOOT DOWN=" + KeyEvent.getKeyText(settings.getShootDown()));
		shootDown.setName("SHOOT DOWN");
		JButton shootLeft = new JButton("SHOOT LEFT=" + KeyEvent.getKeyText(settings.getShootLeft()));
		shootLeft.setName("SHOOT LEFT");
		JButton shootRigth = new JButton("SHOOT RIGTH=" + KeyEvent.getKeyText(settings.getShootRigth()));
		shootRigth.setName("SHOOT RIGTH");

		ActionListener updateKeyPressed = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastButtonPressed = (JButton) e.getSource();
			}
		};

		upKey.addKeyListener(this);
		downKey.addKeyListener(this);
		leftKey.addKeyListener(this);
		rigthKey.addKeyListener(this);
		shootUp.addKeyListener(this);
		shootDown.addKeyListener(this);
		shootLeft.addKeyListener(this);
		shootRigth.addKeyListener(this);

		upKey.addActionListener(updateKeyPressed);
		downKey.addActionListener(updateKeyPressed);
		leftKey.addActionListener(updateKeyPressed);
		rigthKey.addActionListener(updateKeyPressed);
		shootUp.addActionListener(updateKeyPressed);
		shootDown.addActionListener(updateKeyPressed);
		shootLeft.addActionListener(updateKeyPressed);
		shootRigth.addActionListener(updateKeyPressed);

		buttonBox.add(upKey);
		buttonBox.add(downKey);
		buttonBox.add(leftKey);
		buttonBox.add(rigthKey);
		buttonBox.add(shootUp);
		buttonBox.add(shootDown);
		buttonBox.add(shootLeft);
		buttonBox.add(shootRigth);

		return buttonBox;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (lastButtonPressed == null) {
			return;
		} else {
			if (lastButtonPressed.getName().equals("UP")) {
				settings.setUp(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("DOWN")) {
				settings.setDown(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("LEFT")) {
				settings.setLeft(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("RIGTH")) {
				settings.setRight(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("SHOOT UP")) {
				settings.setShootUp(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("SHOOT DOWN")) {
				settings.setShootDown(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("SHOOT LEFT")) {
				settings.setShootLeft(e.getKeyCode());
			} else if (lastButtonPressed.getName().equals("SHOOT RIGTH")) {
				settings.setShootRigth(e.getKeyCode());
			}

			lastButtonPressed.setText(lastButtonPressed.getName() + "=" + KeyEvent.getKeyText(e.getKeyCode()));

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
