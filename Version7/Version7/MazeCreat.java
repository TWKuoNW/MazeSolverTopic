package Version7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeCreat {
    private int row; // 寬度
    private int col; // 長度
    private char wall = '■'; // 牆壁
    private char blank = '○'; // 空地
    private char[][] maze; // 迷宫陣列
    private boolean[][] visit; // 用來標記是否被訪問過

    Node start = new Node(0, 0); // 開始節點
    Node exit = new Node(row - 1, col - 1); // 出口
    Node cur; // 當前格
    Node next; // 下一格
    Stack<Node> path = new Stack<Node>();
    int[][] adj = { { 0, 2 }, { 0, -2 }, { 2, 0 }, { -2, 0 } }; // 用來計算鄰接格

    public MazeCreat(int row, int col) { // 建構式
        this.row = row;
        this.col = col;
        init();
        makeMaze();
    }

    class Node { // 巢狀類別定義節點
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }
    }

    private void init() {// 初始化迷宮參數
        maze = new char[row][col];
        visit = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = wall;
                visit[i][j] = false;
            }
        }
        visit[start.x][start.y] = true;
        maze[start.x][start.y] = blank;
        cur = start; // 將當前格標記為開始格
    }

    void makeMaze() {
        path.push(cur); // 將當前格推入堆疊中
        while (!path.empty()) {
            Node[] adjs = notVisitedAdj(cur); // 尋找未被訪問的鄰接格
            if (adjs.length == 0) {
                cur = path.pop(); // 如果該格子沒有可訪問的鄰接格，則跳回上一個格子
                continue;
            }
            next = adjs[new Random().nextInt(adjs.length)]; // 隨機選取一個鄰接格
            int x = next.x;
            int y = next.y;

            if (visit[x][y]) {// 如果該節點被訪問過，則回到上一步繼續尋找
                cur = path.pop();
            } else {// 否則將當前格壓入棧，標記當前格為已訪問，並且在迷宮地圖上移除障礙物
                path.push(next);
                visit[x][y] = true;
                maze[x][y] = blank;
                maze[(cur.x + x) / 2][(cur.y + y) / 2] = blank; // 移除當前格與下一個之間的牆壁
                cur = next; // 當前格等於下一格
            }
        }
    }

    /*
     * 判斷節點是否都被訪問過
     * private boolean allVisited(Node[] ns) {
     * for (Node n : ns) {
     * if (!visit[n.x][n.y]) {
     * return false;
     * }
     * }
     * return true;
     * }
     */

    private Node[] notVisitedAdj(Node node) {// 尋找可訪問的鄰接格
        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < adj.length; i++) {
            int x = node.x + adj[i][0];
            int y = node.y + adj[i][1];
            if (x >= 0 && x < row && y >= 0 && y < col) {
                if (!visit[x][y]) {
                    list.add(new Node(x, y));
                }
            }
        }
        Node[] a = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public char[][] getMaze() { // 獲取迷宮
        return maze;
    }

    public int[][] getIntMaze() { // 獲取整數類型迷宮
        GraphicalMaze gm = new GraphicalMaze(maze);
        return gm.getIntMaze();
    }

    public void printMaze() { // 列印迷宮
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}