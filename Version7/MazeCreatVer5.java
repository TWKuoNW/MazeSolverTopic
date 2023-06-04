package Version7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeCreatVer5 {
    //declarate block
    int row;                                    //長
    int col;                                    //寬
    char wall = '■';                            //牆壁
    char blank = '○';                           //空地
    char[][] maze;                              //迷宫
    boolean[][] visit;                          //用來標記是否被訪問過

    Node start = new Node(0, 0);                //開始節點
    Node exit = new Node(row - 1, col - 1);     //出口
    Node cur;                                   //當前格
    Node next;                                  //下一格
    Stack<Node> path = new Stack<Node>();       
    int[][] adj = {
        {0, 2}, {0, -2}, {2, 0}, {-2, 0}
    };                                          //用來計算鄰接格

    public MazeCreatVer5(int row, int col) {
        this.row = row;
        this.col = col;
        init();
        makeMaze();
    }
    
    class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }
    }

    /**
     * 初始化，初始化迷宮參數
     */
    private void init() {
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
        cur = start;                                           //將當前格標記為開始格
    }

    void makeMaze() {
        path.push(cur);                                        //將當前格壓入棧
        while (!path.empty()) {
            Node[] adjs = notVisitedAdj(cur);                  //尋找未被訪問的鄰接格
            if (adjs.length == 0) {
                cur = path.pop();                              //如果該格子沒有可訪問的鄰接格，則跳回上一個格子
                continue;
            }
            next = adjs[new Random().nextInt(adjs.length)];    //隨機選取一個鄰接格
            int x = next.x;
            int y = next.y;
            //如果該節點被訪問過，則回到上一步繼續尋找
            if (visit[x][y]) {
                cur = path.pop();
            } else                                              //否則將當前格壓入棧，標記當前格為已訪問，並且在迷宮地圖上移除障礙物
            {
                path.push(next);
                visit[x][y] = true;
                maze[x][y] = blank;
                maze[(cur.x + x) / 2][(cur.y + y) / 2] = blank; //移除當前格與下一個之間的牆壁
                cur = next;                                     //當前格等於下一格
            }
        }
    }

    /**
     * 判断节点是否都被访问
     *
     * @param ns
     * @return
     */
    private boolean allVisited(Node[] ns) {
        for (Node n : ns) {
            if (!visit[n.x][n.y]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 尋找可訪問的鄰接格，這裡可以優化，不用list
     *
     * @param node
     * @return
     */
    private Node[] notVisitedAdj(Node node) {
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

    public char[][] getMaze() {
        return maze;
    }

    public int[][] getIntMaze() {
        GraphicalMazeVer2 gm = new GraphicalMazeVer2(maze);
        return gm.getIntMaze();
    }
    
    void printMaze() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }  

    public static void main(String args[]) {
//        CreatMaze1 cm = new CreatMaze1(11, 11);
    }
}