
public class Node {
	private boolean isAlive;
	private int xPos;
	private int yPos;

	public Node(int yPos, int xPos, boolean isAlive) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.isAlive = isAlive;
	}
	
	public void setXpos(int xPos) {
		this.xPos = xPos;
	}

	public void setYpos(int yPos) {
		this.yPos = yPos;
	}

	public int getXpos()
	{
		return this.xPos;
	}
	
	public int getYpos()
	{
		return this.yPos;
	}
	
	public void setLife(boolean life)
	{
		this.isAlive = life ? true : false;
	}
	
	public boolean getLife()
	{
		return this.isAlive;
	}
}
