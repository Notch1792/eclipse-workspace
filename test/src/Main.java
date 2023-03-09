import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;

public class Main {

	public static void main(String[] args) throws InterruptedException, AWTException {

		Robot robot = new Robot();
		PointerInfo pointer = MouseInfo.getPointerInfo();
		
		Thread.sleep(3000);
		
		System.out.println(robot.getPixelColor((int)pointer.getLocation().getX(), (int)pointer.getLocation().getY()));

	}

}