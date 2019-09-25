import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 * 
 * @author Tanishk
 *
 */
public class Cube extends MovePieces{
	
	Rectangle[][] front = new Rectangle[3][3]; //blue
	Rectangle[][] back = new Rectangle[3][3]; //green
	Rectangle[][] right = new Rectangle[3][3]; //red
	Rectangle[][] left = new Rectangle[3][3]; //orange
	Rectangle[][] top = new Rectangle[3][3]; //yellow
	Rectangle[][] bottom = new Rectangle[3][3]; //white
	Rectangle[][] inOne = new Rectangle[3][3];
	
	private int front00x = 250;
	private int front00y = 400;
	
	private int right00x = 505;
	private int right00y = front00y;
	
	private int left00x = front00x - 50;
	private int left00y = front00y;
	
	private int top00x = front00x;
	private int top00y = front00y - 50;
	
	private int bottom00x = front00x;
	private int bottom00y = 655;
	
	private int frontx = 102;  //everything except right and left delta x
	
	final int rightYRot = 90;
	final int leftYRot = -90;
	
	final int topXRot = -90;
	final int bottomXRot = 90;
	
	//Which side front?
	private Rectangle[][] frntx = front;
	private Rectangle[][] frnty = null;
	
	
	public Cube(){
		frontCreate();
		backCreate();
		rightCreate();
		leftCreate();
		topCreate();
		bottomCreate();
	}
		
	public void frontCreate() {
		Color blue = Color.DARKBLUE;
		double size = 100;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				front[i][j] = new Rectangle
						(front00x + (i * 102), front00y + (j * 102), size, size);
				front[i][j].setFill(blue);
			}
		}
	}

	public void backCreate() {
		Color green = Color.GREEN;
		double size = 100;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				back[i][j] = new Rectangle
						(front00x + (i * 102), front00y + 2*frontx - (j * 102), size, size);
				back[i][j].setFill(green);
				back[i][j].setTranslateZ(102 * 3);
			}
		}
	}

	public void rightCreate() {
		Color red = Color.RED;
		double size = 100;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				right[i][j] = new Rectangle
						(right00x, right00y + (j * 102), size, size);
				right[i][j].setFill(red);
				right[i][j].setRotationAxis(Rotate.Y_AXIS);
				right[i][j].setRotate(rightYRot);
				right[i][j].setTranslateZ(50 + 102 * i);
			}	
		}
	}
	
	public void leftCreate() {
		Color orange = Color.ORANGE;
		double size = 100; 
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				left[i][j] = new Rectangle
						(left00x, left00y + (2 * 102) - (102 * j), size, size);
				left[i][j].setFill(orange);
				left[i][j].setRotationAxis(Rotate.Y_AXIS);
				left[i][j].setRotate(leftYRot);
				left[i][j].setTranslateZ(50 + 102 * i);
			}	
		}
	}

	public void topCreate() {
		Color yellow = Color.YELLOW;
		double size = 100;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				top[i][j] = new Rectangle
						(top00x + (i * 102), top00y, size, size);
				top[i][j].setFill(yellow);
				top[i][j].setVisible(true);
				top[i][j].setRotationAxis(Rotate.X_AXIS);
				top[i][j].setRotate(topXRot);
				top[i][j].setTranslateZ(254 - 102 * j);
			}
		}
	}
	
	public void bottomCreate() {
		Color white = Color.WHITE;
		double size = 100;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				bottom[i][j] = new Rectangle
						(bottom00x + (i * 102), bottom00y, size, size);
				bottom[i][j].setFill(white);
				bottom[i][j].setVisible(true);
				bottom[i][j].setRotationAxis(Rotate.X_AXIS);
				bottom[i][j].setRotate(bottomXRot);
				bottom[i][j].setTranslateZ(50 + 102 * j);
			}
		}
	}
	
	public void R() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		//retrieve
		corner[0] = right[0][0];
		corner[1] = right[2][0];
		corner[2] = right[2][2];
		corner[3] = right[0][2];
			
		right[2][0] = corner[0]; //top
		right[2][2] = corner[1]; //right
		right[0][2] = corner[2]; //bottom
		right[0][0] = corner[3]; //left
		
		edge[0] = right[0][1];
		edge[1] = right[1][0];
		edge[2] = right[2][1];
		edge[3] = right[1][2];
		
		right[1][0] = edge[0];
		right[2][1] = edge[1];
		right[1][2] = edge[2];
		right[0][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[2][i];
			extra[1][i] = top[2][i];
			extra[2][i] = back[2][i];
			extra[3][i] = bottom[2][i];
			
			top[2][i] = extra[0][i];
			back[2][i] = extra[1][i];
			bottom[2][i] = extra[2][i];
			front[2][i] = extra[3][i];
		
			move(top[2][i], 
					top[2][i].getX(), top00x + 204,
					top[2][i].getY(), top00y, 
					0, 254 - 102 * i);
			rotation(top[2][i], 0, topXRot, Rotate.X_AXIS);
			
			move(back[2][i], 
					back[2][i].getX(), front00x + 2*frontx,
					back[2][i].getY(), front00y + frontx * 2 - (102 * i),
					254 - 102 * i, 102*3);
			rotation(back[2][i], topXRot, 0, Rotate.X_AXIS);
			
			move(bottom[2][i], 
					bottom[2][i].getX(), bottom00x + 2*frontx,
					bottom[2][i].getY(), bottom00y,
					102*3 , 50 + 102 * i);
			rotation(bottom[2][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(front[2][i], 
					front[2][i].getX(), front00x + 2*frontx,
					front[2][i].getY(), front00y + frontx * i,
					50 + 102 * i, 0);
			rotation(front[2][i], bottomXRot, 0, Rotate.X_AXIS);
		}
		move(right[2][0],
				right[2][0].getX(), right00x,
				right[2][0].getY(), right00y,
				50, 254);
		move(right[2][2],
				right[2][2].getX(), right00x,
				right[2][2].getY(), right00y + 2*102,
				254, 254);
		move(right[0][2],
				right[0][2].getX(), right00x,
				right[0][2].getY(), right00y + 2*102,
				254, 50);
		move(right[0][0],
				right[0][0].getX(), right00x,
				right[0][0].getY(), right00y,
				50, 50);
		move(right[1][0],
				right[1][0].getX(), right00x,
				right[1][0].getY(), right00y,
				0, 102);
		move(right[2][1],
				right[2][1].getX(), right00x,
				right[2][1].getY(), right00y + 102,
				0, 102);
		move(right[1][2],
				right[1][2].getX(), right00x,
				right[1][2].getY(), right00y + 204,
				102, 0);
		move(right[0][1],
				right[0][1].getX(), right00x,
				right[0][1].getY(), right00y + 102,
				102, 0);
	}
	
	public void Ri() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = right[0][0];
		corner[1] = right[2][0];
		corner[2] = right[2][2];
		corner[3] = right[0][2];
			
		right[0][2] = corner[0]; //top
		right[0][0] = corner[1]; //right
		right[2][0] = corner[2]; //bottom
		right[2][2] = corner[3]; //left
		
		edge[0] = right[0][1];
		edge[1] = right[1][0];
		edge[2] = right[2][1];
		edge[3] = right[1][2];
		
		right[1][2] = edge[0];
		right[0][1] = edge[1];
		right[1][0] = edge[2];
		right[2][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[2][i];
			extra[1][i] = bottom[2][i];
			extra[2][i] = back[2][i];
			extra[3][i] = top[2][i];
			
			bottom[2][i] = extra[0][i];
			back[2][i] = extra[1][i];
			top[2][i] = extra[2][i];
			front[2][i] = extra[3][i];
		
			move(bottom[2][i], 
					bottom[2][i].getX(), bottom00x + 204,
					bottom[2][i].getY(), bottom00y, 
					0, 50 + 102 * i);
			rotation(bottom[2][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(back[2][i], 
					back[2][i].getX(), front00x + 2*frontx,
					back[2][i].getY(), front00y + frontx * 2 - (102 * i),
					50 + 102 * i, 102*3);
			rotation(back[2][i], topXRot, 0, Rotate.X_AXIS);

			move(top[2][i], 
					top[2][i].getX(), top00x + 2*frontx,
					top[2][i].getY(), top00y,
					102*3 , 254 - 102 * i);
			rotation(top[2][i], 0, topXRot, Rotate.X_AXIS);
		
			move(front[2][i], 
					front[2][i].getX(), front00x + 2*frontx,
					front[2][i].getY(), front00y + frontx * i,
					254 - 102 * i, 0);
			rotation(front[2][i], bottomXRot, 0, Rotate.X_AXIS);
		}
		move(right[0][0],
			right[0][0].getX(), right00x,
			right[0][0].getY(), right00y,
			254, 50);
		move(right[2][0],
				right[2][0].getX(), right00x,
				right[2][0].getY(), right00y,
				254, 254);
		move(right[2][2],
				right[2][2].getX(), right00x,
				right[2][2].getY(), right00y + 204,
				50, 254);
		move(right[0][2],
				right[0][2].getX(), right00x,
				right[0][2].getY(), right00y + 204,
				50, 50);
		
		move(right[1][0],
				right[1][0].getX(), right00x,
				right[1][0].getY(), right00y,
				102, 0);
		move(right[2][1],
				right[2][1].getX(), right00x,
				right[2][1].getY(), right00y + 102,
				0, 102);
		move(right[1][2],
				right[1][2].getX(), right00x,
				right[1][2].getY(), right00y + 204,
				0, 102);
		move(right[0][1],
				right[0][1].getX(), right00x,
				right[0][1].getY(), right00y + 102,
				102, 0);
	}
	
	public void M() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		//retrieve	
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[1][i];
			extra[1][i] = top[1][i];
			extra[2][i] = back[1][i];
			extra[3][i] = bottom[1][i];
			
			top[1][i] = extra[0][i];
			back[1][i] = extra[1][i];
			bottom[1][i] = extra[2][i];
			front[1][i] = extra[3][i];
		}
		
		for(int i = 0; i < 3; i++) {
			move(top[1][i], 
					top[1][i].getX(), top00x + 102,
					top[1][i].getY(), top00y, 
					0, 254 - 102 * i);
			rotation(top[1][i], 0, topXRot, Rotate.X_AXIS);
		
			move(back[1][i], 
					back[1][i].getX(), front00x + frontx,
					back[1][i].getY(), front00y + frontx * 2 - (102 * i),
					254 - 102 * i, 102*3);
			rotation(back[1][i], topXRot, 0, Rotate.X_AXIS);
		
			move(bottom[1][i], 
					bottom[1][i].getX(), bottom00x + frontx,
					bottom[1][i].getY(), bottom00y,
					102*3 , 50 + 102 * i);
			rotation(bottom[1][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(front[1][i], 
					front[1][i].getX(), front00x + frontx,
					front[1][i].getY(), front00y + frontx * i,
					50 + 102 * i, 0);
			rotation(front[1][i], bottomXRot, 0, Rotate.X_AXIS);
		}
	}
	
	public void Mi() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[1][i];
			extra[1][i] = bottom[1][i];
			extra[2][i] = back[1][i];
			extra[3][i] = top[1][i];
			
			bottom[1][i] = extra[0][i];
			back[1][i] = extra[1][i];
			top[1][i] = extra[2][i];
			front[1][i] = extra[3][i];
		
			move(bottom[1][i], 
					bottom[1][i].getX(), bottom00x + frontx,
					bottom[1][i].getY(), bottom00y, 
					0, 50 + 102 * i);
			rotation(bottom[1][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(back[1][i], 
					back[1][i].getX(), front00x + frontx,
					back[1][i].getY(), front00y + frontx * 2 - (102 * i),
					50 + 102 * i, 102*3);
			rotation(back[1][i], topXRot, 0, Rotate.X_AXIS);

			move(top[1][i], 
					top[1][i].getX(), top00x + frontx,
					top[1][i].getY(), top00y,
					102*3 , 254 - 102 * i);
			rotation(top[1][i], 0, topXRot, Rotate.X_AXIS);
		
			move(front[1][i], 
					front[1][i].getX(), front00x + frontx,
					front[1][i].getY(), front00y + frontx * i,
					254 - 102 * i, 0);
			rotation(front[1][i], bottomXRot, 0, Rotate.X_AXIS);
		}
	}
	
	public void Ml() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][1];
			extra[1][i] = right[i][1];
			extra[2][i] = back[i][1];
			extra[3][i] = left[i][1];
			
			front[i][1] = extra[1][i];
			back[i][1] = extra[3][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[i][1] = extra[2][j];
			left[i][1] = extra[0][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][1], 
					front[i][1].getX(), front00x + frontx*i,
					front[i][1].getY(), front00y + frontx, 
					50 + 102 * i, 0);
			rotation(front[i][1], leftYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][1], 
					right[i][1].getX(), right00x,
					right[i][1].getY(), right00y + frontx ,
					102*3, 50 + 102 * i);
			rotation(right[i][1], 0, rightYRot, Rotate.Y_AXIS);
			
			move(back[i][1], 
					back[i][1].getX(), front00x + frontx*i,
					back[i][1].getY(), front00y + frontx,
					50 + 102 * i , 102*3);
			rotation(back[i][1], leftYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][1], 
					left[i][1].getX(), left00x,
					left[i][1].getY(), left00y + frontx,
					0, 50 + 102 * i);
			rotation(left[i][1], 0, leftYRot, Rotate.Y_AXIS);
		}
	}
	
	public void Mr() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][1];
			extra[1][i] = right[i][1];
			extra[2][i] = back[i][1];
			extra[3][i] = left[i][1];
			
			right[i][1] = extra[0][i];
			left[i][1] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			back[i][1] = extra[1][j];
			front[i][1] = extra[3][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][1], 
					front[i][1].getX(), front00x + frontx*i,
					front[i][1].getY(), front00y + frontx, 
					254 - (102 * i), 0);
			rotation(front[i][1], leftYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][1], 
					right[i][1].getX(), right00x,
					right[i][1].getY(), right00y + frontx ,
					0, 50 + 102 * i);
			rotation(right[i][1], 0, leftYRot, Rotate.Y_AXIS);
			
			move(back[i][1], 
					back[i][1].getX(), front00x + frontx*i,
					back[i][1].getY(), front00y + frontx,
					254 - (102 * i) , 102*3);
			rotation(back[i][1], leftYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][1], 
					left[i][1].getX(), left00x,
					left[i][1].getY(), left00y + frontx,
					102*3, 50 + 102 * i);
			rotation(left[i][1], 0, leftYRot, Rotate.Y_AXIS);
		}
	}
	
	public void L() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = left[0][0];
		corner[1] = left[2][0];
		corner[2] = left[2][2];
		corner[3] = left[0][2];
			
		left[2][0] = corner[0]; //top
		left[2][2] = corner[1]; //left
		left[0][2] = corner[2]; //bottom
		left[0][0] = corner[3]; //left

		edge[0] = left[0][1];
		edge[1] = left[1][0];
		edge[2] = left[2][1];
		edge[3] = left[1][2];
		
		left[1][0] = edge[0];
		left[2][1] = edge[1];
		left[1][2] = edge[2];
		left[0][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[0][i];
			extra[1][i] = bottom[0][i];
			extra[2][i] = back[0][i];
			extra[3][i] = top[0][i];
			
			bottom[0][i] = extra[0][i];
			back[0][i] = extra[1][i];
			top[0][i] = extra[2][i];
			front[0][i] = extra[3][i];
		
			move(bottom[0][i], 
					bottom[0][i].getX(), bottom00x,
					bottom[0][i].getY(), bottom00y, 
					0, 50 + 102 * i);
			rotation(bottom[0][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(back[0][i], 
					back[0][i].getX(), front00x,
					back[0][i].getY(), front00y + frontx * 2 - (102 * i),
					50 + 102 * i, 102*3);
			rotation(back[0][i], topXRot, 0, Rotate.X_AXIS);

			move(top[0][i], 
					top[0][i].getX(), top00x,
					top[0][i].getY(), top00y,
					102*3 , 254 - 102 * i);
			rotation(top[0][i], 0, topXRot, Rotate.X_AXIS);
		
			move(front[0][i], 
					front[0][i].getX(), front00x,
					front[0][i].getY(), front00y + frontx * i,
					254 - 102 * i, 0);
			rotation(front[0][i], bottomXRot, 0, Rotate.X_AXIS);
		}
		move(left[0][0],
		left[0][0].getX(), left00x,
		left[0][0].getY(), left00y + 204,
		50, 50);
		
		move(left[2][0],
				left[2][0].getX(), left00x,
				left[2][0].getY(), left00y + 204,
				50, 254);
		move(left[2][2],
				left[2][2].getX(), left00x,
				left[2][2].getY(), left00y,
				254, 254);
		move(left[0][2],
				left[0][2].getX(), left00x,
				left[0][2].getY(), left00y,
				254, 50);
		
		move(left[1][0],
				left[1][0].getX(), left00x,
				left[1][0].getY(), left00y + 204,
				0, 102);
		move(left[2][1],
				left[2][1].getX(), left00x,
				left[2][1].getY(), left00y + 102,
				0, 102);
		move(left[1][2],
				left[1][2].getX(), left00x,
				left[1][2].getY(), left00y,
				102, 0);
		move(left[0][1],
				left[0][1].getX(), left00x,
				left[0][1].getY(), left00y + 102,
				102, 0);
	}
	
	public void Li(){
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = left[0][0];
		corner[1] = left[2][0];
		corner[2] = left[2][2];
		corner[3] = left[0][2];
			
		left[0][2] = corner[0]; //top
		left[0][0] = corner[1]; //right
		left[2][0] = corner[2]; //bottom
		left[2][2] = corner[3]; //left
		
		edge[0] = left[0][1];
		edge[1] = left[1][0];
		edge[2] = left[2][1];
		edge[3] = left[1][2];
		
		left[1][2] = edge[0];
		left[0][1] = edge[1];
		left[1][0] = edge[2];
		left[2][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[0][i];
			extra[1][i] = top[0][i];
			extra[2][i] = back[0][i];
			extra[3][i] = bottom[0][i];
			
			top[0][i] = extra[0][i];
			back[0][i] = extra[1][i];
			bottom[0][i] = extra[2][i];
			front[0][i] = extra[3][i];
		
			move(top[0][i], 
					top[0][i].getX(), top00x,
					top[0][i].getY(), top00y, 
					0, 254 - 102 * i);
			rotation(top[0][i], 0, topXRot, Rotate.X_AXIS);
			
			move(back[0][i], 
					back[0][i].getX(), front00x,
					back[0][i].getY(), front00y + frontx * 2 - (102 * i),
					254 - 102 * i, 102*3);
			rotation(back[0][i], topXRot, 0, Rotate.X_AXIS);
		
			move(bottom[0][i], 
					bottom[0][i].getX(), bottom00x,
					bottom[0][i].getY(), bottom00y,
					102*3 , 50 + 102 * i);
			rotation(bottom[0][i], 0, bottomXRot, Rotate.X_AXIS);
		
			move(front[0][i], 
					front[0][i].getX(), front00x,
					front[0][i].getY(), front00y + frontx * i,
					50 + 102 * i, 0);
			rotation(front[0][i], bottomXRot, 0, Rotate.X_AXIS);
		}
		move(left[2][0],
				left[2][0].getX(), left00x,
				left[2][0].getY(), left00y + 204,
				254, 254);
		move(left[2][2],
				left[2][2].getX(), left00x,
				left[2][2].getY(), left00y,
				50, 254);
		move(left[0][2],
				left[0][2].getX(), left00x,
				left[0][2].getY(), left00y,
				50, 50);
		move(left[0][0],
				left[0][0].getX(), left00x,
				left[0][0].getY(), left00y + 204,
				254, 50);
		
		move(left[1][0],
				left[1][0].getX(), left00x,
				left[1][0].getY(), left00y + 204,
				102, 0);
		move(left[2][1],
				left[2][1].getX(), left00x,
				left[2][1].getY(), left00y + 102,
				0, 102);
		move(left[1][2],
				left[1][2].getX(), left00x,
				left[1][2].getY(), left00y,
				0, 102);
		move(left[0][1],
				left[0][1].getX(), left00x,
				left[0][1].getY(), left00y + 102,
				102, 0);
	}
	
	public void U() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = top[0][0];
		corner[1] = top[2][0];
		corner[2] = top[2][2];
		corner[3] = top[0][2];
			
		top[2][0] = corner[0]; //top
		top[2][2] = corner[1]; //right
		top[0][2] = corner[2]; //bottom
		top[0][0] = corner[3]; //left
		
		edge[0] = top[0][1];
		edge[1] = top[1][0];
		edge[2] = top[2][1];
		edge[3] = top[1][2];
		
		top[1][0] = edge[0];
		top[2][1] = edge[1];
		top[1][2] = edge[2];
		top[0][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][0];
			extra[1][i] = right[i][0];
			extra[2][i] = back[i][2];
			extra[3][i] = left[i][2];
			
			front[i][0] = extra[1][i];
			back[i][2] = extra[3][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[i][0] = extra[2][j];
			left[i][2] = extra[0][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][0], 
					front[i][0].getX(), front00x + frontx*i,
					front[i][0].getY(), front00y, 
					50 + 102 * i, 0);
			rotation(front[i][0], leftYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][0], 
					right[i][0].getX(), right00x,
					right[i][0].getY(), right00y,
					102*3, 50 + 102 * i);
			rotation(right[i][0], 0, rightYRot, Rotate.Y_AXIS);
			
			move(back[i][2], 
					back[i][2].getX(), front00x + frontx*i,
					back[i][2].getY(), front00y,
					50 + 102 * i , 102*3);
			rotation(back[i][2], leftYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][2], 
					left[i][2].getX(), left00x,
					left[i][2].getY(), left00y,
					0, 50 + 102 * i);
			rotation(left[i][2], 0, rightYRot, Rotate.Y_AXIS);
		}
		move(top[2][0],
				top[2][0].getX(), top00x + 204,
				top[2][0].getY(), top00y,
				254, 254);
		move(top[2][2],
				top[2][2].getX(), top00x + 204,
				top[2][2].getY(), top00y,
				254, 50);
		move(top[0][2],
				top[0][2].getX(), top00x,
				top[0][2].getY(), top00y,
				50, 50);
		move(top[0][0],
				top[0][0].getX(), top00x,
				top[0][0].getY(), top00y,
				50, 254);

		move(top[1][0],
				top[1][0].getX(), top00x + 102,
				top[1][0].getY(), top00y,
				0, 102);
		move(top[2][1],
				top[2][1].getX(), top00x + 204,
				top[2][1].getY(), top00y,
				102, 0);
		move(top[1][2],
				top[1][2].getX(), top00x + 102,
				top[1][2].getY(), top00y,
				102, 0);
		move(top[0][1],
				top[0][1].getX(), top00x,
				top[0][1].getY(), top00y,
				0, 102);
	}
	
	public void Ui() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = top[0][0];
		corner[1] = top[2][0];
		corner[2] = top[2][2];
		corner[3] = top[0][2];
			
		top[0][2] = corner[0]; //top
		top[0][0] = corner[1]; //right
		top[2][0] = corner[2]; //bottom
		top[2][2] = corner[3]; //left
		
		edge[0] = top[0][1];
		edge[1] = top[1][0];
		edge[2] = top[2][1];
		edge[3] = top[1][2];
		
		top[1][2] = edge[0];
		top[0][1] = edge[1];
		top[1][0] = edge[2];
		top[2][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][0];
			extra[1][i] = right[i][0];
			extra[2][i] = back[i][2];
			extra[3][i] = left[i][2];
			
			right[i][0] = extra[0][i];
			left[i][2] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			back[i][2] = extra[1][j];
			front[i][0] = extra[3][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][0], 
					front[i][0].getX(), front00x + frontx*i,
					front[i][0].getY(), front00y, 
					254 - (102 * i), 0);
			rotation(front[i][0], rightYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][0], 
					right[i][0].getX(), right00x,
					right[i][0].getY(), right00y,
					0, 50 + 102 * i);
			rotation(right[i][0], 0, leftYRot, Rotate.Y_AXIS);
			
			move(back[i][2], 
					back[i][2].getX(), front00x + frontx*i,
					back[i][2].getY(), front00y,
					254 - (102 * i) , 102*3);
			rotation(back[i][2], rightYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][2], 
					left[i][2].getX(), left00x,
					left[i][2].getY(), left00y,
					102*3, 50 + 102 * i);
			rotation(left[i][2], 0, leftYRot, Rotate.Y_AXIS);
		}
		move(top[2][0],
				top[2][0].getX(), top00x + 204,
				top[2][0].getY(), top00y,
				50, 254);
		move(top[2][2],
				top[2][2].getX(), top00x + 204,
				top[2][2].getY(), top00y,
				50, 50);
		move(top[0][2],
				top[0][2].getX(), top00x,
				top[0][2].getY(), top00y,
				254, 50);
		move(top[0][0],
				top[0][0].getX(), top00x,
				top[0][0].getY(), top00y,
				254, 254);

		move(top[1][0],
				top[1][0].getX(), top00x + 102,
				top[1][0].getY(), top00y,
				0, 102);
		move(top[2][1],
				top[2][1].getX(), top00x + 204,
				top[2][1].getY(), top00y,
				0, 102);
		move(top[1][2],
				top[1][2].getX(), top00x + 102,
				top[1][2].getY(), top00y,
				102, 0);
		move(top[0][1],
				top[0][1].getX(), top00x,
				top[0][1].getY(), top00y,
				102, 0);
	}
	
	public void D() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = bottom[0][0];
		corner[1] = bottom[2][0];
		corner[2] = bottom[2][2];
		corner[3] = bottom[0][2];
			
		bottom[2][0] = corner[0]; //top
		bottom[2][2] = corner[1]; //right
		bottom[0][2] = corner[2]; //bottom
		bottom[0][0] = corner[3]; //left
		
		edge[0] = bottom[0][1];
		edge[1] = bottom[1][0];
		edge[2] = bottom[2][1];
		edge[3] = bottom[1][2];
		
		bottom[1][0] = edge[0];
		bottom[2][1] = edge[1];
		bottom[1][2] = edge[2];
		bottom[0][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][2];
			extra[1][i] = right[i][2];
			extra[2][i] = back[i][0];
			extra[3][i] = left[i][0];
			
			right[i][2] = extra[0][i];
			left[i][0] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			back[i][0] = extra[1][j];
			front[i][2] = extra[3][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][2], 
					front[i][2].getX(), front00x + frontx*i,
					front[i][2].getY(), front00y + 204, 
					254 - (102 * i), 0);
			rotation(front[i][2], rightYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][2], 
					right[i][2].getX(), right00x,
					right[i][2].getY(), right00y + 204,
					0, 50 + 102 * i);
			rotation(right[i][2], 0, leftYRot, Rotate.Y_AXIS);
			
			move(back[i][0], 
					back[i][0].getX(), front00x + frontx*i,
					back[i][0].getY(), front00y + 204,
					254 - (102 * i) , 102*3);
			rotation(back[i][0], rightYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][0], 
					left[i][0].getX(), left00x,
					left[i][0].getY(), left00y + 204,
					102*3, 50 + 102 * i);
			rotation(left[i][0], 0, leftYRot, Rotate.Y_AXIS);
		}
		move(bottom[2][0],
				bottom[2][0].getX(), bottom00x + 204,
				bottom[2][0].getY(), bottom00y,
				50, 50);
		move(bottom[2][2],
				bottom[2][2].getX(), bottom00x + 204,
				bottom[2][2].getY(), bottom00y,
				50, 254);
		move(bottom[0][2],
				bottom[0][2].getX(), bottom00x,
				bottom[0][2].getY(), bottom00y,
				254, 254);
		move(bottom[0][0],
				bottom[0][0].getX(), bottom00x,
				bottom[0][0].getY(), bottom00y,
				254, 50);

		move(bottom[1][0],
				bottom[1][0].getX(), bottom00x + 102,
				bottom[1][0].getY(), bottom00y,
				102, 0);
		move(bottom[2][1],
				bottom[2][1].getX(), bottom00x + 204,
				bottom[2][1].getY(), bottom00y,
				0, 102);
		move(bottom[1][2],
				bottom[1][2].getX(), bottom00x + 102,
				bottom[1][2].getY(), bottom00y,
				0, 102);
		move(bottom[0][1],
				bottom[0][1].getX(), bottom00x,
				bottom[0][1].getY(), bottom00y,
				102, 0);
	}
	
	public void Di() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = bottom[0][0];
		corner[1] = bottom[2][0];
		corner[2] = bottom[2][2];
		corner[3] = bottom[0][2];
			
		bottom[0][2] = corner[0]; //top
		bottom[0][0] = corner[1]; //right
		bottom[2][0] = corner[2]; //bottom
		bottom[2][2] = corner[3]; //left
		
		edge[0] = bottom[0][1];
		edge[1] = bottom[1][0];
		edge[2] = bottom[2][1];
		edge[3] = bottom[1][2];
		
		bottom[1][2] = edge[0];
		bottom[0][1] = edge[1];
		bottom[1][0] = edge[2];
		bottom[2][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = front[i][2];
			extra[1][i] = right[i][2];
			extra[2][i] = back[i][0];
			extra[3][i] = left[i][0];
			
			front[i][2] = extra[1][i];
			back[i][0] = extra[3][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[i][2] = extra[2][j];
			left[i][0] = extra[0][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(front[i][2], 
					front[i][2].getX(), front00x + frontx*i,
					front[i][2].getY(), front00y + 204, 
					50 + 102 * i, 0);
			rotation(front[i][2], leftYRot, 0, Rotate.Y_AXIS);
		
			move(right[i][2], 
					right[i][2].getX(), right00x,
					right[i][2].getY(), right00y + 204,
					102*3, 50 + 102 * i);
			rotation(right[i][2], 0, rightYRot, Rotate.Y_AXIS);
			
			move(back[i][0], 
					back[i][0].getX(), front00x + frontx*i,
					back[i][0].getY(), front00y + 204,
					50 + 102 * i , 102*3);
			rotation(back[i][0], leftYRot, 0, Rotate.Y_AXIS);
			
			move(left[i][0], 
					left[i][0].getX(), left00x,
					left[i][0].getY(), left00y + 204,
					0, 50 + 102 * i);
			rotation(left[i][0], 0, rightYRot, Rotate.Y_AXIS);
		}
		move(bottom[2][0],
				bottom[2][0].getX(), bottom00x + 204,
				bottom[2][0].getY(), bottom00y,
				254, 50);
		move(bottom[2][2],
				bottom[2][2].getX(), bottom00x + 204,
				bottom[2][2].getY(), bottom00y,
				254, 254);
		move(bottom[0][2],
				bottom[0][2].getX(), bottom00x,
				bottom[0][2].getY(), bottom00y,
				50, 254);
		move(bottom[0][0],
				bottom[0][0].getX(), bottom00x,
				bottom[0][0].getY(), bottom00y,
				50, 50);

		move(bottom[1][0],
				bottom[1][0].getX(), bottom00x + 102,
				bottom[1][0].getY(), bottom00y,
				102, 0);
		move(bottom[2][1],
				bottom[2][1].getX(), bottom00x + 204,
				bottom[2][1].getY(), bottom00y,
				102, 0);
		move(bottom[1][2],
				bottom[1][2].getX(), bottom00x + 102,
				bottom[1][2].getY(), bottom00y,
				0, 102);
		move(bottom[0][1],
				bottom[0][1].getX(), bottom00x,
				bottom[0][1].getY(), bottom00y,
				0, 102);
	}

	public void F() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = front[0][0];
		corner[1] = front[2][0];
		corner[2] = front[2][2];
		corner[3] = front[0][2];
			
		front[0][2] = corner[2]; //top
		front[0][0] = corner[3]; //right
		front[2][0] = corner[0]; //bottom
		front[2][2] = corner[1]; //left
		
		edge[0] = front[0][1];
		edge[1] = front[1][0];
		edge[2] = front[2][1];
		edge[3] = front[1][2];
		
		front[1][0] = edge[0];
		front[2][1] = edge[1];
		front[1][2] = edge[2];
		front[0][1] = edge[3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[0][i];
			extra[1][i] = top[i][2];
			extra[2][i] = left[0][i];
			extra[3][i] = bottom[i][0];
			
			right[0][i] = extra[1][i];
			top[i][2] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			left[0][i] = extra[3][j];
			bottom[i][0] = extra[0][j];
		}
		
		for(int i = 0; i < 3; i++) {
			move(right[0][i], 
					right[0][i].getX(), right00x,
					right[0][i].getY(), right00y + 102*i, 
					0, 0);
			rotation(right[0][i], 0, 180, Rotate.Y_AXIS);
			
			move(top[i][2], 
					top[i][2].getX(), top00x + 102*i,
					top[i][2].getY(), top00y,
					0, 0);
			rotation(top[i][2], 0, bottomXRot * 2, Rotate.X_AXIS);
		
			move(left[0][i], 
					left[0][i].getX(), left00x,
					left[0][i].getY(), left00y + 204 - (102*i),
					0, 0);
			rotation(left[0][i], 0, 180, Rotate.Y_AXIS);
		
			move(bottom[i][0], 
					bottom[i][0].getX(), bottom00x+ 102*i,
					bottom[i][0].getY(), bottom00y,
					0, 0);
			rotation(bottom[i][0], bottomXRot * 2, 0, Rotate.X_AXIS);
		}
		move(front[2][0],
				front[2][0].getX(), front00x + 204,
				front[2][0].getY(), front00y,
				0, 0);
		move(front[2][2],
				front[2][2].getX(), front00x + 204,
				front[2][2].getY(), front00y + 204,
				0, 0);
		move(front[0][2],
				front[0][2].getX(), front00x,
				front[0][2].getY(), front00y + 204,
				0, 0);
		move(front[0][0],
				front[0][0].getX(), front00x,
				front[0][0].getY(), front00y,
				0, 0);
		
		move(front[1][0],
				front[1][0].getX(), front00x + 102,
				front[1][0].getY(), front00y,
				0, 0);
		move(front[2][1],
				front[2][1].getX(), front00x + 204,
				front[2][1].getY(), front00y + 102,
				0, 0);
		move(front[1][2],
				front[1][2].getX(), front00x + 102,
				front[1][2].getY(), front00y + 204,
				0, 0);
		move(front[0][1],
				front[0][1].getX(), front00x,
				front[0][1].getY(), front00y + 102,
				0, 0);
	}
	
	public void Fi() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = front[0][0];
		corner[1] = front[2][0];
		corner[2] = front[2][2];
		corner[3] = front[0][2];
			
		front[0][2] = corner[0]; //top
		front[0][0] = corner[1]; //right
		front[2][0] = corner[2]; //bottom
		front[2][2] = corner[3]; //left
		
		edge[0] = front[0][1];
		edge[1] = front[1][0];
		edge[2] = front[2][1];
		edge[3] = front[1][2];
		
		front[1][0] = edge[2];
		front[2][1] = edge[3];
		front[1][2] = edge[0];
		front[0][1] = edge[1];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[0][i];
			extra[1][i] = top[i][2];
			extra[2][i] = left[0][i];
			extra[3][i] = bottom[i][0];
			
			left[0][i] = extra[1][i];
			top[i][2] = extra[0][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[0][i] = extra[3][j];
			bottom[i][0] = extra[2][j];
		}
		translate("fi");
	}

	public void MF() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[1][i];
			extra[1][i] = top[i][1];
			extra[2][i] = left[1][i];
			extra[3][i] = bottom[i][1];
			
			right[1][i] = extra[1][i];
			top[i][1] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			left[1][i] = extra[3][j];
			bottom[i][1] = extra[0][j];
		}
		translate("mf");
	}

	public void MFi() {
		Rectangle[][] extra = new Rectangle[4][3];
		
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[1][i];
			extra[1][i] = top[i][1];
			extra[2][i] = left[1][i];
			extra[3][i] = bottom[i][1];
			
			left[1][i] = extra[1][i];
			top[i][1] = extra[0][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[1][i] = extra[3][j];
			bottom[i][1] = extra[2][j];
		}
		translate("mfi");
	}
	
	public void B() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = back[0][0];
		corner[1] = back[2][0];
		corner[2] = back[2][2];
		corner[3] = back[0][2];
			
		back[0][2] = corner[2]; //top
		back[0][0] = corner[3]; //right
		back[2][0] = corner[0]; //bottom
		back[2][2] = corner[1]; //left
		
		edge[0] = back[0][1];
		edge[1] = back[1][0];
		edge[2] = back[2][1];
		edge[3] = back[1][2];
		
		back[1][0] = edge[0];
		back[2][1] = edge[1];
		back[1][2] = edge[2];
		back[0][1] = edge[3];
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[2][i];
			extra[1][i] = top[i][0];
			extra[2][i] = left[2][i];
			extra[3][i] = bottom[i][2];
			
			left[2][i] = extra[1][i];
			top[i][0] = extra[0][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			right[2][i] = extra[3][j];
			bottom[i][2] = extra[2][j];
		}
		translate("b");
	}
	
	public void Bi() {
		Rectangle[][] extra = new Rectangle[4][3];
		Rectangle[] corner = new Rectangle[4];
		Rectangle[] edge = new Rectangle[4];
		
		//retrieve
		corner[0] = back[0][0];
		corner[1] = back[2][0];
		corner[2] = back[2][2];
		corner[3] = back[0][2];
			
		back[0][2] = corner[0]; //top
		back[0][0] = corner[1]; //right
		back[2][0] = corner[2]; //bottom
		back[2][2] = corner[3]; //left
		
		edge[0] = back[0][1];
		edge[1] = back[1][0];
		edge[2] = back[2][1];
		edge[3] = back[1][2];
		
		back[1][0] = edge[2];
		back[2][1] = edge[3];
		back[1][2] = edge[0];
		back[0][1] = edge[1];
		for(int i = 0; i < 3; i++) {
			extra[0][i] = right[2][i];
			extra[1][i] = top[i][0];
			extra[2][i] = left[2][i];
			extra[3][i] = bottom[i][2];
			
			right[2][i] = extra[1][i];
			top[i][0] = extra[2][i];
		}
		for(int i = 0, j = 2; i < 3; i++, j--) {
			left[2][i] = extra[3][j];
			bottom[i][2] = extra[0][j];
		}
		translate("bi");
	}

	
	
	public void translate(String x) {
		switch(x) {
		case "r":
			break;
		case "fi":
			for(int i = 0; i < 3; i++) {
				move(right[0][i], 
						right[0][i].getX(), right00x,
						right[0][i].getY(), right00y + 102*i, 
						0, 0);
				rotation(right[0][i], 0, 180, Rotate.Y_AXIS);
				
				move(top[i][2], 
						top[i][2].getX(), top00x + 102*i,
						top[i][2].getY(), top00y,
						0, 0);
				rotation(top[i][2], 0, bottomXRot * 2, Rotate.X_AXIS);
			
				move(left[0][i], 
						left[0][i].getX(), left00x,
						left[0][i].getY(), left00y + 204 - (102*i),
						0, 0);
				rotation(left[0][i], 0, 180, Rotate.Y_AXIS);
			
				move(bottom[i][0], 
						bottom[i][0].getX(), bottom00x+ 102*i,
						bottom[i][0].getY(), bottom00y,
						0, 0);
				rotation(bottom[i][0], bottomXRot * 2, 0, Rotate.X_AXIS);
			}
			move(front[2][0],
					front[2][0].getX(), front00x + 204,
					front[2][0].getY(), front00y,
					0, 0);
			move(front[2][2],
					front[2][2].getX(), front00x + 204,
					front[2][2].getY(), front00y + 204,
					0, 0);
			move(front[0][2],
					front[0][2].getX(), front00x,
					front[0][2].getY(), front00y + 204,
					0, 0);
			move(front[0][0],
					front[0][0].getX(), front00x,
					front[0][0].getY(), front00y,
					0, 0);
			
			move(front[1][0],
					front[1][0].getX(), front00x + 102,
					front[1][0].getY(), front00y,
					0, 0);
			move(front[2][1],
					front[2][1].getX(), front00x + 204,
					front[2][1].getY(), front00y + 102,
					0, 0);
			move(front[1][2],
					front[1][2].getX(), front00x + 102,
					front[1][2].getY(), front00y + 204,
					0, 0);
			move(front[0][1],
					front[0][1].getX(), front00x,
					front[0][1].getY(), front00y + 102,
					0, 0);
			break;
			case "mf":
				for(int i = 0; i < 3; i++) {
					move(right[1][i], 
							right[1][i].getX(), right00x,
							right[1][i].getY(), right00y + 102*i, 
							0, 0);
					rotation(right[1][i], 0, 180, Rotate.Y_AXIS);
					
					move(top[i][1], 
							top[i][1].getX(), top00x + 102*i,
							top[i][1].getY(), top00y,
							0, 0);
					rotation(top[i][1], 0, bottomXRot * 2, Rotate.X_AXIS);
				
					move(left[1][i], 
							left[1][i].getX(), left00x,
							left[1][i].getY(), left00y + 204 - (102*i),
							0, 0);
					rotation(left[1][i], 0, 180, Rotate.Y_AXIS);
				
					move(bottom[i][1], 
							bottom[i][1].getX(), bottom00x+ 102*i,
							bottom[i][1].getY(), bottom00y,
							0, 0);
					rotation(bottom[i][1], bottomXRot * 2, 0, Rotate.X_AXIS);
				}
				break;
			case "mfi":
				for(int i = 0; i < 3; i++) {
					move(right[1][i], 
							right[1][i].getX(), right00x,
							right[1][i].getY(), right00y + 102*i, 
							0, 0);
					rotation(right[1][i], 0, 180, Rotate.Y_AXIS);
					
					move(top[i][1], 
							top[i][1].getX(), top00x + 102*i,
							top[i][1].getY(), top00y,
							0, 0);
					rotation(top[i][1], 0, bottomXRot * 2, Rotate.X_AXIS);
				
					move(left[1][i], 
							left[1][i].getX(), left00x,
							left[1][i].getY(), left00y + 204 - (102*i),
							0, 0);
					rotation(left[1][i], 0, 180, Rotate.Y_AXIS);
				
					move(bottom[i][1], 
							bottom[i][1].getX(), bottom00x+ 102*i,
							bottom[i][1].getY(), bottom00y,
							0, 0);
					rotation(bottom[i][1], bottomXRot * 2, 0, Rotate.X_AXIS);
				}
				break;
			case "b":
				for(int i = 0; i < 3; i++) {
					move(right[2][i], 
							right[2][i].getX(), right00x,
							right[2][i].getY(), right00y + 102*i, 
							0, 0);
					rotation(right[2][i], 0, 180, Rotate.Y_AXIS);
					
					move(top[i][0], 
							top[i][0].getX(), top00x + 102*i,
							top[i][0].getY(), top00y,
							0, 0);
					rotation(top[i][0], 0, bottomXRot * 2, Rotate.X_AXIS);
				
					move(left[2][i], 
							left[2][i].getX(), left00x,
							left[2][i].getY(), left00y + 204 - (102*i),
							0, 0);
					rotation(left[2][i], 0, 180, Rotate.Y_AXIS);
				
					move(bottom[i][2], 
							bottom[i][2].getX(), bottom00x+ 102*i,
							bottom[i][2].getY(), bottom00y,
							0, 0);
					rotation(bottom[i][2], bottomXRot * 2, 0, Rotate.X_AXIS);
				}
				move(back[2][0],
						back[2][0].getX(), front00x + 204,
						back[2][0].getY(), front00y + 204,
						0, 0);
				move(back[2][2],
						back[2][2].getX(), front00x + 204,
						back[2][2].getY(), front00y,
						0, 0);
				move(back[0][2],
						back[0][2].getX(), front00x,
						back[0][2].getY(), front00y,
						0, 0);
				move(back[0][0],
						back[0][0].getX(), front00x,
						back[0][0].getY(), front00y + 204,
						0, 0);
				
				move(back[1][0],
						back[1][0].getX(), front00x + 102,
						back[1][0].getY(), front00y + 204,
						0, 0);
				move(back[2][1],
						back[2][1].getX(), front00x + 204,
						back[2][1].getY(), front00y + 102,
						0, 0);
				move(back[1][2],
						back[1][2].getX(), front00x + 102,
						back[1][2].getY(), front00y,
						0, 0);
				move(back[0][1],
						back[0][1].getX(), front00x,
						back[0][1].getY(), front00y + 102,
						0, 0);
				break;
			default:
				for(int i = 0; i < 3; i++) {
					move(right[2][i], 
							right[2][i].getX(), right00x,
							right[2][i].getY(), right00y + 102*i, 
							0, 0);
					rotation(right[2][i], 0, 180, Rotate.Y_AXIS);
					
					move(top[i][0], 
							top[i][0].getX(), top00x + 102*i,
							top[i][0].getY(), top00y,
							0, 0);
					rotation(top[i][0], 0, bottomXRot * 2, Rotate.X_AXIS);
				
					move(left[2][i], 
							left[2][i].getX(), left00x,
							left[2][i].getY(), left00y + 204 - (102*i),
							0, 0);
					rotation(left[2][i], 0, 180, Rotate.Y_AXIS);
				
					move(bottom[i][2], 
							bottom[i][2].getX(), bottom00x+ 102*i,
							bottom[i][2].getY(), bottom00y,
							0, 0);
					rotation(bottom[i][2], bottomXRot * 2, 0, Rotate.X_AXIS);
				}
				move(back[2][0],
						back[2][0].getX(), front00x + 204,
						back[2][0].getY(), front00y + 204,
						0, 0);
				move(back[2][2],
						back[2][2].getX(), front00x + 204,
						back[2][2].getY(), front00y,
						0, 0);
				move(back[0][2],
						back[0][2].getX(), front00x,
						back[0][2].getY(), front00y,
						0, 0);
				move(back[0][0],
						back[0][0].getX(), front00x,
						back[0][0].getY(), front00y + 204,
						0, 0);
				
				move(back[1][0],
						back[1][0].getX(), front00x + 102,
						back[1][0].getY(), front00y + 204,
						0, 0);
				move(back[2][1],
						back[2][1].getX(), front00x + 204,
						back[2][1].getY(), front00y + 102,
						0, 0);
				move(back[1][2],
						back[1][2].getX(), front00x + 102,
						back[1][2].getY(), front00y,
						0, 0);
				move(back[0][1],
						back[0][1].getX(), front00x,
						back[0][1].getY(), front00y + 102,
						0, 0);
				break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Extra Functions for the cube

	public Rectangle[][] getside(Rectangle[][] side){
		return side;
	}

	public void setFront(Rectangle[][] fx, Rectangle[][] fy) {
		Rectangle[][][] horizontal = {front, right, back, left};
		Rectangle[][][] vertical = {front, top, back, bottom};
		frntx = fx;
		frnty = fy;
		
		if(fy == null) {
			if(fx == right) {
				Rectangle[][] extra = new Rectangle[4][3];
				Rectangle[] corner = new Rectangle[4];
				Rectangle[] edge = new Rectangle[4];
				
				//retrieve
				corner[0] = top[0][0];
				corner[1] = top[2][0];
				corner[2] = top[2][2];
				corner[3] = top[0][2];
					
				top[0][2] = corner[0]; //top
				top[0][0] = corner[1]; //right
				top[2][0] = corner[2]; //bottom
				top[2][2] = corner[3]; //left
				
				edge[0] = top[0][1];
				edge[1] = top[1][0];
				edge[2] = top[2][1];
				edge[3] = top[1][2];
				
				top[1][2] = edge[0];
				top[0][1] = edge[1];
				top[1][0] = edge[2];
				top[2][1] = edge[3];
				
				for(int i = 0; i < 3; i++) {
					extra[0][i] = front[i][0];
					extra[1][i] = right[i][0];
					extra[2][i] = back[i][2];
					extra[3][i] = left[i][2];
					
					right[i][0] = extra[0][i];
					left[i][2] = extra[2][i];
				}
				for(int i = 0, j = 2; i < 3; i++, j--) {
					back[i][2] = extra[1][j];
					front[i][0] = extra[3][j];
				}
			}
		}
	}
}
