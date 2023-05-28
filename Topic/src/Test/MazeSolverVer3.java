package Test;

import java.util.LinkedList;
import java.util.Queue;

public class MazeSolverVer3 {

    private int[][] maze; // 迷宮圖形
    private int[][] solution; // 解決方案
    private int startRow, startCol; // 起點座標
    private int endRow, endCol; // 終點座標
    private int numRows, numCols; // 迷宮尺寸

    public MazeSolverVer3(int[][] maze) {
        this.maze = maze;
        numRows = maze.length;
        numCols = maze[0].length;
        solution = new int[numRows][numCols];
        startRow = 0;
        startCol = 0;
        endRow = maze.length - 1;
        endCol = maze[0].length - 1;
    }

    public boolean solveMaze() {
        boolean[][] visited = new boolean[numRows][numCols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                return true; // 找到終點，解決成功
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    solution[newRow][newCol] = maze[newRow][newCol] == 0 ? 1 : -1; // 記錄最短路徑
                }
            }
        }

        return false; // 無法找到解決方案
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && maze[row][col] != -1;
    }

    public void printSolution() {
        if (solveMaze()) {
            System.out.println("找到解決方案：");
        } else {
            System.out.println("無法找到解決方案");
        }
        solution[0][0] = 1;
        char[][] maze = new char[solution.length][solution[0].length]; //迷宫

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (solution[i][j] == 0) {
                    maze[i][j] = '■';
                } else {
                    maze[i][j] = '○';
                }
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        CreatMaze1 m = new CreatMaze1(11, 11);
//        MazeSolver1 solver = new MazeSolver1(m.getIntMaze());
//        solver.printSolution();
    }
}
