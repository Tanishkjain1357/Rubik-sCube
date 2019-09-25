import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
/**
 * 
 * @author Tanishk
 * TODO
 * Use various x and y rotates to determine front
 */
public class CubePlay extends Application{
	
	Cube cube;
	Arrows arrows;
	private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    
    private Rotate rotateX = new Rotate(0, 403, 553, 153, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, 403, 553, 153, Rotate.Y_AXIS);
	@Override
	public void start(Stage primaryStage) throws Exception{
		Cube cube = new Cube();
		this.cube = cube;
		
		Group root = new Group();
		Group buttonRoot = new Group();
		Scene scene = new Scene(buttonRoot, 1200, 900);
        
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.front[i][j]);
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.back[i][j]);
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.right[i][j]);
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.left[i][j]);
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.top[i][j]);
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				root.getChildren().add(cube.bottom[i][j]);
			}
		}
		root.getTransforms().addAll(rotateX, rotateY);
		
		Arrows a = new Arrows();
		a.R.setOnAction(RUp());
		a.Ri.setOnAction(RDown());	
		a.L.setOnAction(LUp());
		a.Li.setOnAction(LDown());
		a.U.setOnAction(TopLeft());
		a.Ui.setOnAction(TopRight());
		a.D.setOnAction(BottomRight());
		a.Di.setOnAction(BottomLeft());
		a.M.setOnAction(MUp());
		a.Mi.setOnAction(MDown());
		a.Mr.setOnAction(MRight());
		a.Ml.setOnAction(MLeft());
		a.F.setOnAction(FRight());
		a.Fi.setOnAction(FLeft());
		a.MF.setOnAction(MFRight());
		a.MFi.setOnAction(MFLeft());
		a.B.setOnAction(BackLeft());
		a.Bi.setOnAction(BackRight());
		buttonRoot.getChildren().addAll(a.R, a.Ri, a.L, a.Li, 
				a.U, a.Ui, a.D, a.Di, a.M, a.Mi, a.Mr, a.Ml, a.F, a.Fi, a.MF, a.MFi, a.B, a.Bi);
		
		scene.setFill(Color.SILVER);
		PerspectiveCamera camera = new PerspectiveCamera();
        camera.setNearClip(0.1);
        camera.setFarClip(1.05);
        camera.setTranslateZ(-10);
		
        root.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        root.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mouseOldY - mousePosY)/3);
            rotateY.setAngle(rotateY.getAngle()+(mouseOldX - mousePosX)/3);
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            
        });
        root.setOnMouseReleased(me -> {
        	System.out.printf("Vertical: %d\nHorizontal: %d\n", getXRot(), getYRot());
        	getFront();
        });
        
        buttonRoot.getChildren().add(root);
        scene.setCamera(camera);
		
        primaryStage.setTitle("Test FX"); 
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Right Side buttons
	public EventHandler<ActionEvent> RUp() {
		EventHandler<ActionEvent> right = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.R();
			}
		};
		return right;
	}
	
	public EventHandler<ActionEvent> RDown() {
		EventHandler<ActionEvent> rightinv = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.Ri();
			}
		};
		return rightinv;
	}
	
	
	//Left Side buttons
	public EventHandler<ActionEvent> LUp() {
		EventHandler<ActionEvent> leftinv = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Li();
			}
		};
		return leftinv;
	}
	
	public EventHandler<ActionEvent> LDown() {
		EventHandler<ActionEvent> left = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.L();
			}
		};
		return left;
	}
	
	
	//Top side Buttons
	public EventHandler<ActionEvent> TopRight() {
		EventHandler<ActionEvent> upinv = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.Ui();
			}
		};
		return upinv;
	}
	
	public EventHandler<ActionEvent> TopLeft() {
		EventHandler<ActionEvent> up = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.U();
			}
		};
		return up;
	}
	
	
	//Bottom Side Buttons
	public EventHandler<ActionEvent> BottomRight() {
		EventHandler<ActionEvent> down = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.D();
			}
		};
		return down;
	}
	
	public EventHandler<ActionEvent> BottomLeft() {
		EventHandler<ActionEvent> downinv = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) { 
				cube.Di();
			}
		};
		return downinv;
	}
	
	
	//Middle Buttons
	public EventHandler<ActionEvent> MUp() {
		EventHandler<ActionEvent> middle = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.M();
			}
		};
		return middle;
	}
	
	public EventHandler<ActionEvent> MDown() {
		EventHandler<ActionEvent> middleinv = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Mi();
			}
		};
		return middleinv;
	}
	
	public EventHandler<ActionEvent> MRight() {
		EventHandler<ActionEvent> middleRight = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Mr();
			}
		};
		return middleRight;
	}
	
	public EventHandler<ActionEvent> MLeft() {
		EventHandler<ActionEvent> middleLeft = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Ml();
			}
		};
		return middleLeft;
	}
	
	public EventHandler<ActionEvent> FRight() {
		EventHandler<ActionEvent> frontRight = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.F();
			}
		};
		return frontRight;
	}
	
	public EventHandler<ActionEvent> FLeft() {
		EventHandler<ActionEvent> frontLeft = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Fi();
			}
		};
		return frontLeft;
	}
	
	public EventHandler<ActionEvent> MFRight() {
		EventHandler<ActionEvent> midfrontRight = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.MF();
			}
		};
		return midfrontRight;
	}
	
	public EventHandler<ActionEvent> MFLeft() {
		EventHandler<ActionEvent> midfrontLeft = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.MFi();
			}
		};
		return midfrontLeft;
	}

	public EventHandler<ActionEvent> BackLeft() {
		EventHandler<ActionEvent> backLeft = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.B();
			}
		};
		return backLeft;
	}

	public EventHandler<ActionEvent> BackRight() {
		EventHandler<ActionEvent> backRight = new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent e) {  
				cube.Bi();
			}
		};
		return backRight;
	}
	
	//Getter
	public int getXRot() {
		
		return (int) rotateX.getAngle();
	}
	public int getYRot() {
		if(rotateY.getAngle() > 360) {
			rotateY.setAngle(rotateY.getAngle() - 360);
		}
		if(rotateY.getAngle() < -360) {
			rotateY.setAngle(rotateY.getAngle() + 360);
		}
		return (int) rotateY.getAngle();
	}
	
	//Check front side for cube
		public void getFront(){
			int y = getXRot();
			int x = getYRot();
			
			Rectangle[][] fx = cube.front;
			Rectangle[][] fy = null;
			if(x >= -45 && x <= 45) {
				fx = cube.front;
				System.out.println("front");
			}
			else if(x <= -225 && x >= -315 || x > 45 && x <= 135) {
				fx = cube.right;
				System.out.println("right");
			}
			else if(x > 135 && x < 225 || x < -135 && x >= -225) {
				fx = cube.back;
				System.out.println("back");
			}
			else if(x >= 225 && x <= 315 || x < -45 && x > -135) {
				fx = cube.left;
				System.out.println("left");
			}
			else {
				System.out.println("front");
			}
			cube.setFront(fx, fy);
			
		}
		
	public static void main(String[] args) {
		launch(args);
	}
}
