import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Maze maze = new Maze(s.nextInt());
        maze.generate();
        maze.print();
    }
}
