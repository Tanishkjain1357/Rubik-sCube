import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class MovePieces{
	TranslateTransition t;
	public void rotation(Node b, int angleReset, int angleSet, Point3D axis) {
		RotateTransition r = new RotateTransition(Duration.millis(200), b);
		r.setAxis(axis);
		r.setByAngle(angleSet - angleReset);
		r.play();
	}
	
	public void linearMove(Rectangle b, 
			double startX, double stopX, 
			double startY, double stopY, 
			double startZ, double stopZ) {
		t = new TranslateTransition(Duration.millis(600), b);
		
		
//		t.setFromX(startX);
//		t.setToX(stopX);
//		
		//t.setFromY(startY);
//		t.setToY(stopY);
//		t.setFromZ(startZ);
//		t.setToZ(stopZ);
		
		t.setByX(stopX - startX);
		t.setByY(stopY - startY);
		t.setByZ(stopZ - startZ);
		
		t.play();
		t.setOnFinished(event -> {
            b.setX(stopX);
            b.setY(stopY);
            b.setTranslateX(0);
            b.setTranslateY(0);
		});
		
	}
	
	public void move(Rectangle b, double startX, double stopX, double startY, double stopY, double startZ, double stopZ) {
		linearMove(b, startX, stopX, startY, stopY, startZ, stopZ); 
	}
}
