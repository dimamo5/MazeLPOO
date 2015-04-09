package maze.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SettingButton extends JButton implements ActionListener, KeyListener {
	private Settings settings;
	private KeyEvent keyPressed;

	public SettingButton(String n, Settings settings) {
		this.setText(n);
		this.addActionListener(this);
		this.settings = settings;
	}

	public Settings getSettings() {
		return settings;
	}

	public void actionPerformed(ActionEvent e) {
		JDialog setupWindow = new JDialog();
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

		setupWindow.getContentPane().setLayout(new GridLayout(4, 2));
		setupWindow.setBounds(50, 50, 400, 400);
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

	}

	public JPanel createKeyBidings() {
		JPanel buttonBox = new JPanel();
		JButton upKey = new JButton("UP KEY");
		JButton downKey = new JButton("DOWN");
		JButton leftKey = new JButton("LEFT KEY");
		JButton rigthKey = new JButton("RIGTH KEY");
		JButton shoot = new JButton("SHOOT KEY");

		upKey.addKeyListener(this);

		upKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				upKey.setText("UP=" + keyPressed.getKeyChar());

				// keyPressed = null;
				// JDialog dial = new JDialog();
				// dial.setModal(true);
				// dial.setVisible(false);
				//
				// if (keyPressed != null) {
				// dial.dispose();
				// }
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
		keyPressed = e;
		
		System.out.println("teste");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
