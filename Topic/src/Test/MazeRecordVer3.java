package Test;

public class MazeRecordVer3 {

	private char[][][] mazeSet;
	private int[] mazeTimeSet;
	private int count;
	private int amount;

	public MazeRecordVer3(int amount) {
		mazeSet = new char[amount][][];
		mazeTimeSet = new int[amount];
		this.amount = amount;
		count = 0;
	}

	public void addMaze(char[][] maze, int time) {
		if (count <= amount) {
			mazeSet[count] = maze;
			mazeTimeSet[count] = time;
			System.out.println("Saved successfully");
		} else {
			System.out.println("Index is full");
		}
		count++;

	}

	public void getMazeData() {
		for (int i = 0; i < amount; i++) {
			System.out.println(i + 1 + "Time = " + mazeTimeSet[i]);
			for (int j = 0 ; j < mazeSet[i].length ; j++) {
				for (int k = 0 ; k < mazeSet[i][j].length ; k++) {
					System.out.print(mazeSet[i][j][k] + " ");
				}
				System.out.println();
			}
		}
	}

}
