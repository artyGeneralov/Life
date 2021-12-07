import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid {
	private int width;
	private int height;
	private Node[][] nodeGrid;

	// Constructor:
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		nodeGrid = new Node[height][width];

		// Always initialize grid to 0s
		createZeroGrid();
	}

	/*
	 * creates the next state of the grid, main logic is here
	 */
	public void nextGridState() {
		/*
		 * the main grid is not updated until the next state is fully calculated! We
		 * need to account for the first row, first column, last row, last column, also
		 * corners for every node, count its neighbors
		 * 
		 * if node is alive: if there are 1 or 0 neighbors set node to dead
		 * 
		 * if the are 2 or 3 neighbors do nothing (node left alive)
		 * 
		 * if there are more then 3 live neighbors set node to dead
		 * 
		 * if node is dead: if there are EXACTLY 3 neighbors, set node to alive
		 */

		Node[][] nextGrid = copyNodeGrid(this.nodeGrid);
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				int neighbors = countNeighbors(nodeGrid[i][j]);

				if (nodeGrid[i][j].getLife()) {
					if (neighbors < 2)
						nextGrid[i][j].setLife(false); // dead
					else if (neighbors > 3)
						nextGrid[i][j].setLife(false);
				} else if (neighbors == 3)
					nextGrid[i][j].setLife(true);
			}
		}
		this.nodeGrid = copyNodeGrid(nextGrid);
	}

	private int countNeighbors(Node n) {
		Logger logger = Logger.getLogger(Grid.class.getName());
		int x = n.getXpos(); // col
		int y = n.getYpos(); // row
		//logger.log(Level.INFO, "x = " + x + " y = " + y);
		int neighbors = 0;
		for (int startY = y - 1; startY <= y + 1; startY++) {
			for (int startX = x - 1; startX <= x + 1; startX++) {
				if ((startX >= 0 && startX < this.width && startY >= 0 && startY < this.height)
						&& !(startX == x && startY == y))
				{
					if (nodeGrid[startY][startX].getLife() == true)
					{
						neighbors++;
					}
				}
			}
		}

		return neighbors;
	}

	/*
	 * Initialize the grid with a custom configuration
	 */
	public void createCustomGrid(int[][] grid) {
		if (grid.length != height || grid[0].length != width) {
			System.out.println("Grid lengths don't match");
			return;
		}
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				nodeGrid[i][j].setLife(grid[i][j] == 0 ? false : true);

	}

	/*
	 * Initialize the grid with random values
	 */
	public void createRandGrid() {
		Random r = new Random();
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++) {
				int randNum = r.nextInt(4);
				if(randNum > 2)
					randNum = 1;
				nodeGrid[i][j].setLife(randNum == 0 ? false : true);
			}
	}
	
	public void createRandGridMiddle(int margin) {
		Random r = new Random();
		int halfHeight = this.height / 2;
		int halfWidth = this.width / 2;
		for(int i = halfHeight - margin; i < halfHeight + margin; i++)
			for(int j = halfWidth - margin; j < halfWidth + margin; j++)
			{
				int randNum = r.nextInt(2);
				nodeGrid[i][j].setLife(randNum == 0 ? false : true);
			}
	}

	/*
	 * Initialize grid to contain all 0s
	 */
	public void createZeroGrid() {
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				nodeGrid[i][j] = new Node(i, j, false);
	}

	/*
	 * Returns the current state of the grid
	 */
	public int[][] getGrid() {
		int[][] tempGrid = new int[this.height][this.width];
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				tempGrid[i][j] = nodeGrid[i][j].getLife() ? 1 : 0;
		return tempGrid;
	}

	private Node[][] copyNodeGrid(Node[][] grid) {
		Node[][] newGrid = new Node[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				newGrid[i][j] = new Node(grid[i][j].getXpos(), grid[i][j].getYpos(), grid[i][j].getLife());
		return newGrid;
	}

	public void printGrid(char symbol) {
		String theString = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
			{
				if(nodeGrid[i][j].getLife())
				{
					theString += (Character.toString(symbol));
				}
				else
					theString += " ";
			}
			theString += "\n";
//				System.out.print(nodeGrid[i][j].getLife() ? symbol : " ");
//			System.out.println();
		}
		System.out.print(theString);

	}
}
