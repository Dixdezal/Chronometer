import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelChrono extends JPanel implements Runnable {
	
	static Thread t = null;
	private boolean finalized = true;
	private boolean paused = false;
	private long initiation;
	private Chrono chrono = new Chrono();
	private Font fuente = new Font("Courier New", Font.BOLD, 30);

	public void startChrono() {
		initiation = System.nanoTime();
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
		System.out.println("The Chrono has been STARTED");
	}

	public synchronized void pauseChrono() {
		paused = true;
		System.out.println("The Chrono has been PAUSED");
	}

	public synchronized void resumeChrono() {

		initiation = System.nanoTime();
		paused = false;
		notifyAll();
		System.out.println("The Chrono has been RESUMED");
	}

	public synchronized void stopChrono() {
		chrono.reset();
		finalized = true;
		paused = false;
		System.out.println("The Chrono has been STOPPED");	
		t = null; //To finish the last Thread and to be able to start a new 
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		FontMetrics fm = g.getFontMetrics();
		String chrono = this.chrono.toString();

		int width = fm.stringWidth(chrono);
		int x = (getWidth() - width) / 2;

		int y = (getHeight() / 2) + fm.getAscent();

		g.drawString(chrono, x, y);
	}

	@Override
	public void run() {
		long end = 0;
		finalized = false;
		while (!finalized) {
			synchronized (this) {
				while (paused == true) {
					try {
						wait();
						break;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			end = System.nanoTime();
			chrono.add(System.nanoTime() - initiation);
			initiation = end;
			repaint();
		}
	}
}
