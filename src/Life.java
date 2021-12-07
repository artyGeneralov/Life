import java.util.concurrent.TimeUnit;

public class Life {

	public static final int WIDTH = 130;
	public static final int HEIGHT = 60;
	public static final char symbol = '#';
	public static final int SLEEPTIME = 100;
	public static final int margin = 3;

	public static void main(String[] args) throws InterruptedException {
		Grid grid = new Grid(WIDTH, HEIGHT);
		grid.createRandGridMiddle(margin);
		renderScreen(grid);
		
//		int[][] testGrid = new int[HEIGHT][WIDTH];
//		for(int i = 0; i < testGrid.length; i++)
//			for(int j = 0; j < testGrid[0].length; j++)
//				testGrid[i][j] = 0;
//		testGrid[1][2] = 1;
//		testGrid[2][2] = 1;
//		testGrid[3][2] = 1;
//		/*System.out.println("next:");
//		for(int i = 0; i < testGrid.length; i++)
//		{
//			for(int j = 0; j < testGrid[0].length; j++)
//				System.out.print(testGrid[i][j] + " ");
//			System.out.println();
//		}
//		TimeUnit.MINUTES.sleep(SLEEPTIME);*/
//		
//		Grid testGridGrid = new Grid(WIDTH, HEIGHT);
//		testGridGrid.createCustomGrid(testGrid);
//		renderScreen(testGridGrid);
	}

	public static void renderScreen(Grid grid) throws InterruptedException {
		
		while (true) {
			clearScreen();
			grid.nextGridState();
			grid.printGrid(symbol);
			TimeUnit.MILLISECONDS.sleep(SLEEPTIME);
		}
	}

	/*
	 * Clear the screen, compatible with windows and unix systems
	 */
	public static void clearScreen() {
		try {
			String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
