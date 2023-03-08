import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Window extends JPanel{

	Image frame1 = new ImageIcon("src\\frame_1.png").getImage();
	Image frame2 = new ImageIcon("src\\frame_2.png").getImage();
	Image frame3 = new ImageIcon("src\\frame_3.png").getImage();
	
	JFrame frame = new JFrame("It's-a-me, Mario!");
	
	Image currentFrame = frame1;
	
	short XVelocity = 1, YVelocity = 1;
	
	long lastTick = getTick();
	
	Window() {
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setSize(500, 300);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		while(true) {
			lastTick = getTick();
			sleep(1);
			
			if(frame.getState() != JFrame.NORMAL)
				frame.setState(JFrame.NORMAL);
			
			frame.setLocation(frame.getX() + XVelocity*(int)(getTick() - lastTick),
					frame.getY() + YVelocity*(int)(getTick() - lastTick));
			
			if(frame.getX() >= toolkit.getScreenSize().width - frame.getWidth() ||
					frame.getX() <= 0 ||
					frame.getY() >= toolkit.getScreenSize().height - frame.getHeight() ||
					frame.getY() <= 0) {
				
				if(currentFrame == frame1) currentFrame = frame2;
				else if(currentFrame == frame2) currentFrame = frame3;
				else if(currentFrame == frame3) currentFrame = frame1;
				
				repaint();
			}
			
			if(frame.getX() >= toolkit.getScreenSize().width - frame.getWidth())
				XVelocity = -1;
			else if(frame.getX() <= 0) XVelocity = 1;
			
			if(frame.getY() >= toolkit.getScreenSize().height - frame.getHeight())
				YVelocity = -1;
			else if(frame.getY() <= 0) YVelocity = 1;
			
			sleep(1);
		}
		
	}
	
	public void paint(Graphics g) {
		
		Graphics2D paint = (Graphics2D) g;
		
		paint.drawImage(currentFrame, null, null);
		
	}
	
	long getTick() {
		return System.currentTimeMillis();
	}
	
	void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}