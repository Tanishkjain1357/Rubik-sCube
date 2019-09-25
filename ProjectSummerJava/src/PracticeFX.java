import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PracticeFX extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Line line = new Line(100, 100, 200, 100); //line --> (startX, startY, endX, endY)
		Rectangle rect = new Rectangle(100, 100, 200, 200);
		rect.setFill(Color.BLACK);
		/*
		line.setTranslateX(300);
		line.setTranslateY(250);
		*/
		Box box = new Box(150, 150, 150);
		box.setTranslateX(400);
		box.setTranslateY(400);
		
		
		Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS); 
		Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS); 
	    Rotate rzBox = new Rotate(30, 0, 0, 0, Rotate.Z_AXIS);  
	    box.getTransforms().addAll(rxBox, ryBox, rzBox); 
		MovePieces m = new MovePieces();
	    m.rotation(box, 30, 60, Rotate.Z_AXIS);
	     
	    //System.out.println(rxBox.getAngle());
	    
	    
		Group root = new Group(box, rect);
		
		Scene scene = new Scene(root, 800, 800);
		scene.setFill(Color.LIGHTBLUE);
		
		primaryStage.setTitle("Test FX"); 
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
