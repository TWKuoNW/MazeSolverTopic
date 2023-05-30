package Version5;

public class Run {

    public static void main(String args[]) {
        int mazeAmount = 100; //迷宮總數
        int time;
        long startTime;
        long endTime;
        MazeRecordVer5 mazeRecord = new MazeRecordVer5(mazeAmount);
        for (int i = 1; i <= mazeAmount; i++) {
            MazeCreatVer5 mazeCreat = new MazeCreatVer5(11, 11); // 新增迷宮

            startTime = System.nanoTime(); //用ns紀錄時間
            MazeSolverVer5 mazeSolver = new MazeSolverVer5(mazeCreat.getIntMaze()); // 解決迷宮
            endTime = System.nanoTime();

//            mazeCreat.getMaze();    //取得新建的地圖
//            mazeSolver.printSolution();   //印解決後地圖
            time = (int) (endTime - startTime); // 紀錄時間

            mazeRecord.addMaze(mazeCreat.getMaze(), time); // 儲存地圖與時間
        }
        System.out.println("===========================");

        //這個回傳是MazeData[]，可以理解有很多個"MazeData"，每個"MazeData"裡面都有"maze"和"time"。
        MazeData[] mazeData = mazeRecord.getMazeData();
        for (int i = 0; i < mazeData.length; i++) {
            System.out.println("紀錄" + (i + 1) + ":");
            System.out.println("用時:" + mazeData[i].time + "ns");
            System.out.println("迷宮:");

            for (int j = 0; j < mazeData[i].maze.length; j++) {//印迷宮
                for (int k = 0; k < mazeData[i].maze[j].length; k++) {
                    System.out.print(mazeData[i].maze[j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}
