package TopicCode.Version4;

public class MazeRecordVer4 {

    private char[][][] mazeSet; //迷宮地圖集
    private int[] mazeTimeSet;  //迷宮時間集
    private int count;          //計數器
    private int amount;         //紀錄數量

    public MazeRecordVer4(int amount) { //Constructor並輸入要記錄的數量
        mazeSet = new char[amount][][]; //Initialization 
        mazeTimeSet = new int[amount];  
        this.amount = amount;           //給值
        count = 0;                      //Initialization 
    }

    public void addMaze(char[][] maze, int time) {      //新增迷宮(迷宮陣列,時間)
        if (count <= amount) {                          //判斷是否超過要紀錄總數
            mazeSet[count] = maze;                          
            mazeTimeSet[count] = time;                  //紀錄地圖即用時    
            System.out.println("Saved successfully");
        } else {
            System.out.println("Index is full");
        }
        count++;//計數器+1

    }
    
    public void printMazeData() {   //印出紀錄的迷宮資料
        for (int i = 0; i < amount; i++) {
            System.out.println(i + 1 + "Time = " + mazeTimeSet[i]);
            for (int j = 0; j < mazeSet[i].length; j++) {
                for (int k = 0; k < mazeSet[i][j].length; k++) {
                    System.out.print(mazeSet[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }
    
    public char[][][] getMazeData(){    //取出紀錄的迷宮資料
        return mazeSet;
    }
    
    public int[] getTimeData(){         //取出紀錄的時間資料
        return mazeTimeSet;
    }
}
