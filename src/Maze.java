import java.util.Random;

public class Maze {

    static private int[][] directions = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
    private int[][] map;
    private Random rand;
    private int size;

    public Maze(int size) {
        map = new int[size * 2 + 1][size * 2 + 1];
        this.size = size;
        rand = new Random();
    }

    public void generate() {
        int cellsLeft = size * size - 1;

        generateWalls(size);
        Coordinates currentPosition = new Coordinates(rand.nextInt(size) * 2 + 1,
                rand.nextInt(size) * 2 + 1);

        while (cellsLeft > 0) {
            Coordinates neighbour = randomNeighbour(currentPosition);
            if (!cellVisited(neighbour)) {
                removeWall(currentPosition, neighbour);
                cellsLeft--;
            }
            currentPosition = neighbour;
        }
    }

    public void print() {
        for (int i = 0; i < size * 2 + 1; i++) {
            for (int j = 0; j < size * 2 + 1; j++) {
                if (map[i][j] == 2 || map[i][j] == 3) {
                    System.out.print(" ");
                } else {
                    System.out.print("@");

                }
            }
            System.out.println();
        }
    }

    public void generateWalls(int size) {
        for (int i = 0; i < size * 2 + 1; i += 2) {
            for (int j = 0; j < size * 2 + 1; j++) {
                map[i][j] = 1;
                map[j][i] = 1;
            }
        }
    }

    public void removeWall(Coordinates cell, Coordinates neighbour) {
        if (cell.x - neighbour.x == -2) {
            map[cell.x + 1][cell.y] = 2;
        } else if (cell.x - neighbour.x == 2) {
            map[cell.x - 1][cell.y] = 2;
        } else if (cell.y - neighbour.y == -2) {
            map[cell.x][cell.y + 1] = 2;
        } else if (cell.y - neighbour.y == 2) {
            map[cell.x][cell.y - 1] = 2;
        }
        map[cell.x][cell.y] = 2;
        map[neighbour.x][neighbour.y] = 2;
    }

    public boolean cellVisited(Coordinates cell) {
        return map[cell.x][cell.y] != 0;
    }

    public boolean cellAvailable(Coordinates neighbour) {
        return (neighbour.x < size * 2 + 1) && (neighbour.y < size * 2 + 1) && neighbour.x > 0 && neighbour.y > 0;
    }

    public Coordinates randomNeighbour(Coordinates cp) {
        Coordinates neighbour = getNextPosition(cp, directions[rand.nextInt(4)]);
        if (cellAvailable(neighbour)) {
            return neighbour;
        } else {
            return randomNeighbour(cp);
        }
    }

    public Coordinates getNextPosition(Coordinates cp, int[] direction) {
        return new Coordinates(cp.x + direction[0], cp.y + direction[1]);
    }


}
