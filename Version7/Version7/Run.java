package Version7;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Run {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int time;
        long startTime;
        long endTime; // 紀錄時間

        System.out.print("請輸入要產生多少組迷宮:");
        int inputMazeAmount = sc.nextInt();

        System.out.print("請輸入迷宮大小:");
        int a = sc.nextInt();
        int b = sc.nextInt();

        inputMazeAmount = inputMazeAmount + 1; // 讓後續輸出值保持清晰
        MazeSave mazeSave = new MazeSave(inputMazeAmount); // 初始化迷宮儲存器

        for (int i = 1; i <= inputMazeAmount; i++) {
            MazeCreat mazeCreat = new MazeCreat(a, b); // 新增迷宮

            startTime = System.nanoTime(); // 用ns紀錄時間
            MazeSolver mazeSolver = new MazeSolver(mazeCreat.getIntMaze()); // 解決迷宮
            endTime = System.nanoTime();

            // mazeCreat.getMaze(); //印創建的地圖
            // mazeSolver.printSolution(); //印解決後地圖

            time = (int) (endTime - startTime); // 紀錄時間

            mazeSave.addMaze(mazeCreat.getMaze(), time); // 用迷宮儲存器，儲存地圖與時間
        }
        System.out.println("===========================");

        if (mazeSave.checkFunction()) {// 確認資料齊全性
            MazeData[] mazeData = mazeSave.getMazeData(); // 這個回傳是MazeData[]，可以理解有很多個"MazeData"，每個"MazeData"裡面都有"maze"和"time"。
            MazeSort mazeSort = new MazeSort(mazeData); // 啟動排序器
            // mazeSort.printResult();//列印排序結果

            // ---------------刪除舊資料---------------
            DeleteFolder deleteFolder = new DeleteFolder();
            String folderPath = "Maze";
            File f = new File(folderPath);

            if (f.exists() && f.isDirectory()) {
                deleteFolder.deleteFolder(f);
                System.out.println("資料夾刪除成功");
            } else {
                System.out.println("資料夾不存在或不是一個資料夾");
            }
            // ---------------刪除舊資料---------------
            // ---------------將排序後迷宮資料存成.txt---------------
            MazeData[] getReslut = mazeSort.getReslut();
            int[][] saveAry;
            for (int i = 0; i < getReslut.length - 1; i++) {
                GraphicalMaze graphicalMaze = new GraphicalMaze(getReslut[i].maze);
                saveAry = graphicalMaze.intMaze;

                folderPath = "Maze";
                String filename = (i + 1) + "output.txt";

                try {
                    Path folder = Paths.get(folderPath);// 建立資料夾
                    if (!Files.exists(folder)) {
                        Files.createDirectories(folder);
                    }

                    String filePath = folderPath + "/" + filename;// 建立文件路徑
                    Path file = Paths.get(filePath);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {// 寫入文件
                        for (int[] row : saveAry) {
                            for (int num : row) {
                                writer.write(String.valueOf(num));
                                writer.write(" "); // 在數字之間加入空格
                            }
                            writer.newLine(); // 每行結束換行
                        }
                        System.out.println("成功將迷宮儲存成文件：" + filePath);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // ---------------將排序後迷宮資料存成.txt---------------
            // ---------------執行迷宮遊戲---------------
            try {
                // 建立 ProcessBuilder 物件
                ProcessBuilder pb = new ProcessBuilder("python",
                        "C:\\Users\\s1042\\Desktop\\Version7\\PCode\\MazeGame.py");

                // 啟動子進程
                Process process = pb.start();

                // 獲取子進程的輸出流
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                // 讀取輸出
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // 等待子進程結束
                int exitCode = process.waitFor();

                // 檢查結束狀態
                if (exitCode == 0) {
                    //System.out.println("Python script executed successfully.");
                } else {
                    System.out.println("Python script execution failed with exit code: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            // ---------------執行迷宮遊戲---------------
        }
    }
}
