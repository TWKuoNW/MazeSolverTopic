package Test;

public class Run {

	public static void main(String args[]) {
		int mazeAmount = 10;
		MazeRecordVer3 rm = new MazeRecordVer3(mazeAmount);
		for (int i = 1; i <= mazeAmount; i++) {
			MazeCreatVer3 m = new MazeCreatVer3(11, 11); // 新增迷宮

			long startTime = System.nanoTime();
			MazeSolverVer3 solver = new MazeSolverVer3(m.getIntMaze()); // 解決迷宮
			long endTime = System.nanoTime();

			solver.printSolution();

			int time = (int) (endTime - startTime); // 紀錄時間

			rm.addMaze(m.getMaze(), time); // 儲存地圖與時間

		}
		System.out.println("=================");
		rm.getMazeData();
	}
}
