package Version6;

//MazeData[] mazeData
public class MazeSortVer5 {
    MazeData[] mazeData;

    public MazeSortVer5(MazeData[] mazeData) { // constructor
        this.mazeData = mazeData;
        mazeSort();
    }

    public void mazeSort() {
        // ====================Sort=======================
        Bubble(mazeData);
        // ================================================
    }

    public void printResult() {
        for (int i = 0; i < mazeData.length - 1 ; i++) {
            System.out.println("迷宮" + (i + 1) + " 系統攻略用時:" + mazeData[i].time + "ns");
            for (int j = 0; j < mazeData[i].maze.length; j++) {
                for (int k = 0; k < mazeData[i].maze[j].length; k++) {
                    System.out.print(mazeData[i].maze[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        // System.out.println("length" + mazeData.length);
    }

    public void Bubble(MazeData[] mazeData) {// big
        for (int j = 0; j < mazeData.length - 1; j++) {
            for (int i = mazeData.length - 1; i >= j + 1; i--) {
                if (mazeData[i].time < mazeData[i - 1].time) {
                    MazeData maze = mazeData[i];
                    mazeData[i] = mazeData[i - 1];
                    mazeData[i - 1] = maze;
                }

            }
        }
    }

    public MazeData[] getSortReslut_MazeData(){
        return mazeData;
    }
}
