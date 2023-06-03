package Version6;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Run {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // init Scanner
        int inputMazeAmount; // 迷宮總數
        int mazeAmount;
        int time;
        long startTime;
        long endTime;
        MazeRecordVer5 mazeRecord;

        System.out.print("請輸入要產生多少組迷宮:");
        inputMazeAmount = sc.nextInt();
        mazeAmount = inputMazeAmount + 1;
        mazeRecord = new MazeRecordVer5(mazeAmount);

        for (int i = 1; i <= mazeAmount; i++) {
            MazeCreatVer5 mazeCreat = new MazeCreatVer5(11,11); // 新增迷宮

            startTime = System.nanoTime(); // 用ns紀錄時間
            MazeSolverVer5 mazeSolver = new MazeSolverVer5(mazeCreat.getIntMaze()); // 解決迷宮
            endTime = System.nanoTime();

            // mazeCreat.getMaze(); //取得新建的地圖
            // mazeSolver.printSolution(); //印解決後地圖
            time = (int) (endTime - startTime); // 紀錄時間

            mazeRecord.addMaze(mazeCreat.getMaze(), time); // 儲存地圖與時間
        }
        System.out.println("===========================");

        // 這個回傳是MazeData[]，可以理解有很多個"MazeData"，每個"MazeData"裡面都有"maze"和"time"。
        if (mazeRecord.checkFunction()) {
            MazeData[] mazeData = mazeRecord.getMazeData();
            MazeSortVer5 mazeSort = new MazeSortVer5(mazeData);
            mazeSort.printResult();
            // test block
            MazeData[] getSortReslut_MazeData = mazeSort.getSortReslut_MazeData();
            int[][] saveAry;
            for (int i = 0; i < getSortReslut_MazeData.length; i++) {
                GraphicalMazeVer2 gM = new GraphicalMazeVer2(getSortReslut_MazeData[i].maze);
                saveAry = gM.intMaze;

                // ------------------------------

                String folderPath = "Maze";
                String filename = i + "output.txt";

                try {
                    // 建立資料夾
                    Path folder = Paths.get(folderPath);
                    if (!Files.exists(folder)) {
                        Files.createDirectories(folder);
                    }
        
                    // 建立文件路徑
                    String filePath = folderPath + "/" + filename;
                    Path file = Paths.get(filePath);
        
                    // 寫入文件
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
                        for (int[] row : saveAry) {
                            for (int num : row) {
                                writer.write(String.valueOf(num));
                                writer.write(" "); // 在數字之間加入空格
                            }
                            writer.newLine(); // 每行結束換行
                        }
                        System.out.println("成功將數組存儲為文件：" + filePath);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // ------------------------------
            // test block
        }

    }
}
