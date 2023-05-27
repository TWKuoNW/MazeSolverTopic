package Test;

public class Run {

    public static void main(String args[]) {
        RecordMaze1 rm = new RecordMaze1(100);
        for (int i = 1; i <= 100; i++) {
            CreatMaze1 m = new CreatMaze1(11, 11);
            long startTime = System.nanoTime();
            MazeSolver1 solver = new MazeSolver1(m.getIntMaze());
            long endTime = System.nanoTime();
            solver.printSolution();
            rm.addMaze(m.getMaze(), (endTime - startTime));
        }

    }
}
