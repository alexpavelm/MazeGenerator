import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Maze maze = new Maze(s.nextInt());
        maze.generate();
        maze.print();
        System.out.println();
        if (s.next().equals("solve")) {
            Solver solver = new Solver();
            solver.solve(maze);
        }
    }
}
