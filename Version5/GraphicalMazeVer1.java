package Version5;

public class GraphicalMazeVer1 {
    char[][] charMaze;
    int[][] intMaze;
    
    public GraphicalMazeVer1(int[][] intMaze){
        this.intMaze = intMaze;
        charMaze = new char[intMaze.length][intMaze[0].length];
        for (int i = 0; i < intMaze.length; i++) {
            for (int j = 0; j < intMaze[0].length; j++) {
                if (intMaze[i][j] == 1) {
                    charMaze[i][j] = '■';
                } else {
                    charMaze[i][j] = '○';
                }
            }
        }
    }
    public GraphicalMazeVer1(char[][] charMaze){
        this.charMaze = charMaze;
        intMaze = new int[charMaze.length][charMaze[0].length];
        for (int i = 0; i < charMaze.length; i++) {
            for (int j = 0; j < charMaze[0].length; j++) {
                if (charMaze[i][j] == '■') {
                    intMaze[i][j] = 1;
                } else {
                    intMaze[i][j] = 0;
                }
            }
        }
    }
    
    public int[][] getIntMaze(){
        return intMaze;
    }
    public char[][] getCharMaze(){
        return charMaze;
    }
    
}
