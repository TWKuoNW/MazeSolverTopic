package TopicCode.Version4;

public class Run {

    public static void main(String args[]) {
        int mazeAmount = 100; //迷宮總數
        int time;
        MazeRecordVer4 mazeRecord = new MazeRecordVer4(mazeAmount);
        for (int i = 1; i <= mazeAmount; i++) {
            MazeCreatVer4 mazeCreat = new MazeCreatVer4(11, 11); // 新增迷宮

            long startTime = System.nanoTime(); //用ns紀錄時間
            MazeSolverVer4 mazeSolver = new MazeSolverVer4(mazeCreat.getIntMaze()); // 解決迷宮
            long endTime = System.nanoTime();

            //mazeCreat.getMaze();    //取得新建的地圖
            //mazeSolver.printSolution();   //印解決後地圖
            
            time = (int) (endTime - startTime); // 紀錄時間

            mazeRecord.addMaze(mazeCreat.getMaze(), time); // 儲存地圖與時間
        }
        System.out.println("=================");
        mazeRecord.printMazeData();
//        mazeRecord.getMazeData();
//        mazeRecord.getTimeData();
    }
}
