package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Editor extends JPanel implements MouseListener{

	JFrame frame = new JFrame("Minadore");
	ComponentResizer resizer = new ComponentResizer();
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	short frameBorderRadius = 40;
	short frameHeaderHeight = 30;
	
	boolean moveFrame;
	int moveFrameOffsetX, moveFrameOffsetY;
	
	
	Editor() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.addMouseListener(this);
		frame.setVisible(true);
		
		resizer.registerComponent(frame);
		resizer.setMinimumSize(new Dimension(500, 300));
		
		while(true) {
			sleep(1);
			
			if(moveFrame)
				frame.setLocation(getMouseX() - moveFrameOffsetX, getMouseY() - moveFrameOffsetY);
			if(getMouseY() == 0 && moveFrame) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				moveFrame = false;
			} else if(getMouseX() == 0 && moveFrame) {
				frame.setBounds(0, 0, toolkit.getScreenSize().width / 2, toolkit.getScreenSize().height);
				moveFrame = false;
			} else if(getMouseX() == toolkit.getScreenSize().getWidth() - 1 && moveFrame) {
				frame.setBounds(toolkit.getScreenSize().width / 2, 0, toolkit.getScreenSize().width / 2, toolkit.getScreenSize().height);
				moveFrame = false;
			}
			
			repaint();
		}
		
	}
	
	public void paint(Graphics g) {
		
		Graphics2D paint = (Graphics2D)g;
		
		paint.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		paint.setColor(new Color(81, 86, 88));
		paint.fillRoundRect(0, 0, frame.getWidth(), frame.getHeight(), frameBorderRadius, frameBorderRadius);
		paint.setColor(new Color(53, 53, 53));
		paint.fillRect(0, 0, this.getWidth(), frameHeaderHeight);
		
	}
	
	int getMouseY() {
		return MouseInfo.getPointerInfo().getLocation().y;
	}
	int getMouseX() {
		return MouseInfo.getPointerInfo().getLocation().x;
	}
	
	void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(getMouseY() <= frame.getY() + frameHeaderHeight && getMouseY() > frame.getY() + 4 &&
				getMouseX() > frame.getX() + 4 && getMouseX() < frame.getX() + frame.getWidth() - 4) {
			
			moveFrameOffsetX = getMouseX() - frame.getX();
			moveFrameOffsetY = getMouseY() - frame.getY();
			
			moveFrame = true;
			
		}
			
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		moveFrame = false;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}