package Test;

public class Run {
    public static void main(String args[]){
        CreatMaze1 m = new CreatMaze1(11, 11);
        MazeSolver1 solver = new MazeSolver1(m.getIntMaze());
        solver.printSolution();
    }
}
