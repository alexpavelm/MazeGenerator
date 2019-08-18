import java.util.ArrayList;
import java.util.List;

class Solver {
    private final static int VISITED = 3;
    private Maze maze;
    private ArrayList<Coordinates> path;

    static private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    void solve(Maze maze) {
        this.maze = maze;
        path = new ArrayList<>();
        explore(new Coordinates(0, 1));
        showPath();
    }

    private boolean explore(Coordinates cell) {
        if (isVisitedOrUnvailable(cell)) {
            return false;
        }
        maze.map[cell.y][cell.x] = VISITED;
        path.add(cell);
        if ((cell.x == maze.size * 2) && (cell.y == maze.size * 2 - 1)) {
            return true;
        }

        for (int[] direction : directions) {
            if (explore(maze.getNextPosition(cell, direction))) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    private boolean isVisitedOrUnvailable(Coordinates cell) {
        if (!((cell.x < maze.size * 2 + 1) && (cell.y < maze.size * 2 + 1) && cell.x >= 0 && cell.y >= 0)) {
            return true;
        }
        return maze.map[cell.y][cell.x] != 2;
    }

    private void showPath() {
        for (Coordinates c : path) {
            maze.map[c.y][c.x] = 4;
        }
        maze.print();
    }


}
