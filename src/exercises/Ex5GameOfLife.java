package exercises;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.System.*;

/*
 * Program for Conway's game of life.
 * See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * This is a graphical program using JavaFX to draw on the screen.
 * There's a bit of "drawing" code to make this happen (far below).
 * You don't need to implement (or understand) any of it.
 * NOTE: To run tests must uncomment in init() method, see comment
 *
 * Use smallest step development + functional decomposition!
 *
 * See:
 * - UseEnum
 * - BasicJavaFX (don't need to understand, just if you're curious)
 */
public class Ex5GameOfLife extends Application {

	final Random rand = new Random();

	// Enum type for state of Cells
	enum Cell {
		DEAD, ALIVE;
	}

	// This is the *only* accepted modifiable instance variable in program except ...
	Cell[][] world;

	public void init() {
		//test();        // <--------------- Uncomment to test!
		int nLocations = 900;
		double distribution = 0.1;   // % of locations holding a Cell

		world = new Cell[(int) Math.sqrt(nLocations)][(int) Math.sqrt(nLocations)];

		for (int i = 0; i < Math.sqrt(nLocations); i++) {
			for (int j = 0; j < Math.sqrt(nLocations); j++) {
				double dist = rand.nextDouble();

				world[i][j] = Cell.DEAD;
				if(dist < distribution) {
					world[i][j] = Cell.ALIVE;
				}
			}
		}

	}


	long timeLastUpdate;   // ... this, used for speed of animation.

	// Implement this method (using functional decomposition)
	// Every involved method should be tested, see below, method test()
	// This method is automatically called by a JavaFX timer (don't need to call it)
	void update(long now) {
		if (now - timeLastUpdate > 900_000_000) {  // Has time passed?
			// TODO update world
			timeLastUpdate = now;
			
			System.out.println("Tick");
			
			//Cell[][] temp = new Cell[world.length][world.length];
			//temp = world;
			
			
			for (int i = 0; i < world.length; i++) {
				for (int j = 0; j < world[i].length; j++) {
					
					if(checkCells(j, i, world)) {
						
						//temp[i][j] = Cell.ALIVE;
						world[j][i] = Cell.ALIVE;
						
					}else {
						
						world[j][i] = Cell.DEAD;
						//temp[i][j] = Cell.DEAD;
					}
					
				}
			}
			
			//world = temp;
			
		}
	}


	// -------- Write methods below this --------------

	boolean checkCells(int x, int y, Cell[][] c) {
		
		if(reproduction(y, x, c)) {
			System.out.println(reproduction(y, x, c));
			return true;
		}else if(livesToNextGen(y, x, c)) {
			return true;
		}else if(isOverPolulated(y, x, c)) {
			return false;
		}else if(isUnderPolulated(y, x, c)) {
			return false;
		}else {
			return false;
		}
	}

	int amountOfAliveCellsInArea(int y, int x, Cell[][] c) {
		int alive = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {

				int indexX = x+j;
				int indexY = y+i;

				if(indexX < 0 || indexX >= c[0].length) {//Forutsatter att det ar en kvadratisk matris. 

				}else if(indexY < 0 || indexY >= c.length) {

				}else if(c[indexY][indexX].equals(Cell.ALIVE)) {
					alive++;
				}

			}

		}
		
		if(c[y][x].equals(Cell.ALIVE)) {
			alive -= 1; //metoden raknar med sig sjlalv en gang
		}
		
		
		return alive;
	}


	//if more than 3
	boolean isOverPolulated(int x, int y, Cell[][] c) {
		
		
		if(c[y][x].equals(Cell.DEAD)) {
			return false;		//Not Alive!!!
		}
		
		if(amountOfAliveCellsInArea(y, x, c) > 3) {
			return true;
		}else {
			return false;
		}
		
	}

	//if less than 2
	boolean isUnderPolulated(int x, int y, Cell[][] c) {
		
		if(c[y][x].equals(Cell.DEAD)) {
			return false;		//Not Alive!!!
		}
		
		if(amountOfAliveCellsInArea(y, x, c) < 2) {
			return true;
		}else {
			return false;
		}
	}

	//if 2 or 3 cell lives on
	boolean livesToNextGen(int x, int y, Cell[][] c) {
		
		if(c[y][x].equals(Cell.DEAD)) {
			return false;		//Not Alive!!!
		}
		
		int amount = amountOfAliveCellsInArea(y, x, c);
		
		if((amount == 3 || amount == 2)) {
			return true;
		}else {
			return false;
		}

	}

	//if 3 alive, dead cell becomes alive 
	boolean reproduction(int x, int y, Cell[][] c) {
		
		if(c[y][x].equals(Cell.ALIVE)) {
			return false;		//Not Dead!!!
		}
		
		if(amountOfAliveCellsInArea(y, x, c) == 3) {
			return true;
		}else {
			return false;
		}
	}


	// ---------- Testing -----------------
	// Here you run your tests i.e. call your logic methods
	// to see that they really work
	void test() {
		// Hard coded test world
		Cell[][] testWorld = {
				{Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
				{Cell.ALIVE, Cell.DEAD, Cell.DEAD},
				{Cell.DEAD, Cell.DEAD, Cell.DEAD},

		};
		int size = testWorld.length;


		System.out.println(amountOfAliveCellsInArea(1, 1, testWorld));
		System.out.println(reproduction(1, 1, testWorld));
		System.out.println(isOverPolulated(1, 1, testWorld));
		System.out.println(isUnderPolulated(1, 1, testWorld));
		System.out.println(livesToNextGen(1, 1, testWorld));


		exit(0);
	}




	// -------- Below is JavaFX stuff, nothing to do --------------

	void render() {
		gc.clearRect(0, 0, width, height);
		int size = world.length;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int x = 10 * col + 50;
				int y = 10 * row + 50;
				renderCell(x, y, world[row][col]);
			}
		}
	}

	void renderCell(int x, int y, Cell cell) {
		if (cell == Cell.ALIVE) {
			gc.setFill(Color.RED);
		} else {
			gc.setFill(Color.WHITE);
		}
		gc.fillOval(x, y, 10, 10);
	}

	final int width = 400;   // Size of window
	final int height = 400;

	GraphicsContext gc;

	// Must have public before more later.
	@Override
	public void start(Stage primaryStage) throws Exception {

		// JavaFX stuff
		Group root = new Group();
		Canvas canvas = new Canvas(width, height);
		root.getChildren().addAll(canvas);
		gc = canvas.getGraphicsContext2D();

		// Create a timer
		AnimationTimer timer = new AnimationTimer() {
			// This method called by FX at a certain rate, parameter is the current time
			public void handle(long now) {
				update(now);
				render();

			}
		};
		// Create a scene and connect to the stage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game of Life");
		primaryStage.show();
		timer.start();  // Start simulation
	}

	public static void main(String[] args) {
		launch(args);   // Launch JavaFX
	}
}