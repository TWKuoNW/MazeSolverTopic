package Version7;

public class MazeSave {

    private MazeData[] mazeData;
    private int count; // 計數器
    private int amount; // 紀錄數量

    public MazeSave(int amount) { // Constructor
        this.amount = amount;
        mazeData = new MazeData[amount];
        count = 0;
    }

    // 新增迷宮Method
    public void addMaze(char[][] maze, int time) { // 新增迷宮(迷宮陣列,時間)
        if (count < amount) { // 判斷是否超過要紀錄總數
            mazeData[count] = new MazeData(maze, time); // 紀錄地圖即用時
            // System.out.println("Saved successfully");
        } else {
            System.out.println("Index is full");
        }
        count++;// 計數器+1

    }

    // 印出紀錄的迷宮資料Method
    public void printMazeData() {
        for (int i = 0; i < amount; i++) {
            System.out.println((i + 1) + " Time = " + mazeData[i].time);
            for (int j = 0; j < mazeData[i].maze.length; j++) {
                for (int k = 0; k < mazeData[i].maze[j].length; k++) {
                    System.out.print(mazeData[i].maze[j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    // 取得紀錄的迷宮集
    public MazeData[] getMazeData() {
        return mazeData;
    }

    // 確認資料齊全性的Method
    public boolean checkFunction() {
        if (amount == mazeData.length) {
            System.out.println("Saved successfully");
            return true;
        }
        System.out.println("Error");
        return false;
    }
}
