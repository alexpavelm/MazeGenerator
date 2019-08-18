import java.util.Random;

class Maze {

    static private int[][] directions = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
    int[][] map;
    private Random rand;
    int size;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    Maze(int size) {
        map = new int[size * 2 + 1][size * 2 + 1];
        this.size = size;
        rand = new Random();
    }

    void generate() {
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
        setEnds();

    }

    private void setEnds() {
        map[1][0] = 2;
        map[size * 2 - 1][size * 2] = 2;
    }

    void print() {
        for (int i = 0; i < size * 2 + 1; i++) {
            for (int j = 0; j < size * 2 + 1; j++) {
                if (map[i][j] == 4) {
                    System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                } else if (map[i][j] == 2 || map[i][j] == 3) {
                    System.out.print(" ");
                } else {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_RESET);

                }
            }
            System.out.println();
        }
    }

    private void generateWalls(int size) {
        for (int i = 0; i < size * 2 + 1; i += 2) {
            for (int j = 0; j < size * 2 + 1; j++) {
                map[i][j] = 1;
                map[j][i] = 1;
            }
        }
    }

    private void removeWall(Coordinates cell, Coordinates neighbour) {
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

    private boolean cellVisited(Coordinates cell) {
        return map[cell.x][cell.y] != 0;
    }

    private boolean cellAvailable(Coordinates neighbour) {
        return (neighbour.x < size * 2 + 1) && (neighbour.y < size * 2 + 1) && neighbour.x > 0 && neighbour.y > 0;
    }

    private Coordinates randomNeighbour(Coordinates cp) {
        Coordinates neighbour = getNextPosition(cp, directions[rand.nextInt(4)]);
        if (cellAvailable(neighbour)) {
            return neighbour;
        } else {
            return randomNeighbour(cp);
        }
    }

    Coordinates getNextPosition(Coordinates cp, int[] direction) {
        return new Coordinates(cp.x + direction[0], cp.y + direction[1]);
    }


}
