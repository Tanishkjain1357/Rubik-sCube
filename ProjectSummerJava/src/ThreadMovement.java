import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point3D;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ThreadMovement extends Thread {
	private Thread t;
	private String threadName = "THREAD";
	private Box box;
	private int angle = 0;
	private Point3D axis = Rotate.X_AXIS;
	private int startX = 0;
	private int stopX = 0;
	private int startY = 0;
	private int stopY = 0;
	
	ThreadMovement(Box b, int a, Point3D ax){
		box = b;
		angle = a;
		axis = ax;
	}
	
	ThreadMovement(Box b, int startX, int stopX, int startY, int stopY){
		box = b;
		this.startX = startX;
		this.stopX = stopX;
		
		this.startY = startY;
		this.stopY = stopY;
	}
	
	@Override
	public void run() {
		try {
			//Thread.sleep(200);
			rotation(box, angle, axis);
			linearMove(box, startX, stopX, startY, stopY);
			Thread.sleep(10);
		}
		catch (InterruptedException e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
	      System.out.println("Thread " +  threadName + " exiting.");
	}
	
	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
	         t = new Thread (this, threadName);
	         t.start();
		}
	}
	
	public void rotation(Box b, int angle, Point3D axis) {
		RotateTransition r = new RotateTransition(Duration.millis(1000), b);
		r.setAxis(axis);
		r.setByAngle(angle * -1);
		r.play();
	}
	
	public void linearMove(Box b, int startX, int stopX, int startY, int stopY) {
		TranslateTransition t = new TranslateTransition(Duration.millis(1000), b);
		t.setByX(stopX - startX);
		t.setByY(stopY - startY);
		t.play();
	}
}
