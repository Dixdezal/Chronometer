import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class MF implements ActionListener {

	JFrame frame;
	PanelChrono pi;
	JButton b1, b2;
	JPanel buttons;

	public MF() {
		frame = new JFrame("Chronometer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(250, 315);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setResizable(false);// In 'false' its to not to be able to resize the frame
		frame.setLocationRelativeTo(null);// To center the frame

		buttons = new JPanel();
		buttons.setBackground(Color.GRAY);// Bg color
		frame.add(buttons, BorderLayout.NORTH);

		// 'Start' button
		b1 = new JButton("Start");
		b1.setBackground(Color.BLACK);// Bg color
		b1.setForeground(Color.GREEN);// Text color
		b1.setActionCommand("B1");
		b1.addActionListener(this);
		buttons.add(b1);

		// 'Stop' button
		b2 = new JButton("Stop");
		b2.setBackground(Color.BLACK);// Bg color
		b2.setForeground(Color.GREEN);// Text color
		b2.setActionCommand("B2");
		b2.addActionListener(this);
		buttons.add(b2);

		pi = new PanelChrono();
		frame.add(pi);
		

		frame.setVisible(true);// Always at the end of the elements
	}

	public static void main(String[] args) {
		MF chrono = new MF();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("B1")) {
			if (b1.getText().equals("Start")) {
				b1.setText("Pause");
				pi.startChrono();

			} else if (b1.getText().equals("Pause")) {
				b1.setText("Resume");
				pi.pauseChrono();

			} else if (b1.getText().equals("Resume")) {
				b1.setText("Pause");
				pi.resumeChrono();
			}
		}
		if (e.getActionCommand().equals("B2")) {
			if(b1.getText().equals("Resume")){
				b1.setText("Start");
				pi.stopChrono();
			}
		}
	}
}
