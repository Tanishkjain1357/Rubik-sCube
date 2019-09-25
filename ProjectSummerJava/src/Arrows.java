import javafx.scene.control.Button;

public class Arrows{
	Button cubeLeft;
	Button cubeRight;
	Button cubeUp;
	Button cubeDown;
	
	
	Button R = new Button("Right Up");
	Button Ri = new Button("Right down");
	
	Button L = new Button("Left Up");
	Button Li;
	
	Button U;
	Button Ui;
	
	Button D;
	Button Di;
	
	Button M;
	Button Mi;
	Button Mr = new Button("Middle Right");
	Button Ml = new Button("Middle Left");
	
	Button F = new Button("Front Right");
	Button Fi = new Button("Front Left");
	Button MF = new Button("Middle Front Right");
	Button MFi = new Button("Middle Front Left");
	
	Button B = new Button("Back Left");
	Button Bi = new Button("Back Right");
	
	Arrows(){
		rightUp();
		rightInverted();
		leftUp();
		leftInverted();
		up();
		upInverted();
		down();
		downInverted();
		middleUp();
		middleInverted();
		middleRight();
		middleLeft();
		frontRight();
		frontLeft();
		midFrontRight();
		midFrontLeft();
		backLeft();
		backRight();
	}
	
	public void rightUp() {
		R.setTranslateX(470);
		R.setTranslateY(360);
	}
	
	public void rightInverted() {
		Ri.setTranslateX(470);
		Ri.setTranslateY(720);
	}
	
	public void leftUp() {
		L.setTranslateX(260);
		L.setTranslateY(360);
	}
	
	public void leftInverted() {
		Li = new Button("Left Down");
		Li.setTranslateX(260);
		Li.setTranslateY(720);
	}
	
	public void up() {
		U = new Button("Top Left");
		U.setTranslateX(160);
		U.setTranslateY(410);
	}
	
	public void upInverted() {
		Ui = new Button("Top Right");
		Ui.setTranslateX(570);
		Ui.setTranslateY(410);
	}
	
	public void down() {
		D = new Button("Down Right");
		D.setTranslateX(570);
		D.setTranslateY(670);
	}
	
	public void downInverted() {
		Di = new Button("Down Left");
		Di.setTranslateX(160);
		Di.setTranslateY(670);
	}
	
	public void middleUp() {
		M = new Button("Middle Up");
		M.setTranslateX(360);
		M.setTranslateY(360);
	}
	
	public void middleInverted() {
		Mi = new Button("Middle Down");
		Mi.setTranslateX(355);
		Mi.setTranslateY(720);
	}
	public void middleRight() {
		Mr.setTranslateX(570);
		Mr.setTranslateY(540);
	}
	
	public void middleLeft() {
		Ml.setTranslateX(140);
		Ml.setTranslateY(540);
	}
	
	public void frontRight() {
		F.setTranslateX(950);
		F.setTranslateY(360);
	}
	
	public void frontLeft() {
		Fi.setTranslateX(950);
		Fi.setTranslateY(400);
	}
	
	public void midFrontRight() {
		MF.setTranslateX(950);
		MF.setTranslateY(500);
	}
	
	public void midFrontLeft() {
		MFi.setTranslateX(950);
		MFi.setTranslateY(540);
	}
	
	public void backLeft() {
		B.setTranslateX(950);
		B.setTranslateY(600);
	}
	
	public void backRight() {
		Bi.setTranslateX(950);
		Bi.setTranslateY(650);
	}
}
