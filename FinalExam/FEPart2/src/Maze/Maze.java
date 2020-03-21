package Maze;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    class Node {
        int x;
        int y;
        int distance;
        int symbol;

        public Node(int x, int y, int distance, int symbol) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distance=" + distance +
                    ", symbol=" + symbol +
                    '}';
        }
    }

    int r;
    int c;
    int count;
    char[][] symbols;
    int[][] passable;
    boolean visited[][];
    int row[] = { -1, 1, 0, 0 };
    int col[] = { 0, 0, -1, 1 };

    public Maze(int r, int c) {
        this.r = r;
        this.c = c;
        count = 0;
        symbols = new char[][]{{'+', '|', '|', '*', '+'}, {'+', '+', '+', '|', '+'}, {'*', '*', '-', '-', '+'}};
        //symbols = new char[][]{{'-', '|'}, {'*', '+'}};
        //symbols = new char[][]{{'+', '*', '+'}, {'+', '*', '+'}};
        passable = new int[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                char symbol = symbols[i][j];
                if(symbol == '*'){
                    passable[i][j] = 0;
                    visited[i][j] = true;
                }else if(symbol == '+'){
                    passable[i][j] = 1;
                }else if(symbol == '-'){
                    passable[i][j] = 2;
                }else if(symbol == '|'){
                    passable[i][j] = 3;
                }
            }
        }

        passable[0][0] = 1;
    }

    private boolean isValid(int[][] passable, boolean[][] visited, int x, int y){
        return (x >= 0 && x < r && y >= 0 && y < c && passable[x][y] != 0 && !visited[x][y]);
    }

    private int getMinWays(int i, int j, int x, int y){
        Queue<Node> q = new LinkedList<>();

        visited[i][j] = true;
        q.add(new Node(i, j, 1, passable[i][j]));
        int min_dist = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node node = q.poll();

            i = node.x;
            j = node.y;
            int dist = node.distance;
            int symbol = node.symbol;

            if (i == x && j == y) {
                min_dist = dist;
                break;
            }

            for(int k = 0; k < 4; k++){
                if(k <= 1 && symbol == 2) continue;
                if(k >= 2 && symbol == 3) continue;

                if (isValid(passable, visited, i + row[k], j + col[k])){
                    visited[i + row[k]][j + col[k]] = true;
                    q.add(new Node(i + row[k], j + col[k], dist + 1, passable[i + row[k]][j + col[k]]));
                }
            }
        }

        if(min_dist == Integer.MAX_VALUE) return -1;
        return min_dist;
    }

    public static void main(String[] args) {
        Maze maze01 = new Maze(3, 5);
//        Maze maze02 = new Maze(2, 2);
//        Maze maze03 = new Maze(2, 3);
        System.out.println(maze01.getMinWays(0, 0, maze01.r - 1, maze01.c - 1));

//        System.out.println("\npassable array");
//        for(int i = 0; i < maze.r; i++){
//            for(int j = 0; j < maze.c; j++){
//                System.out.print(maze.passable[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nvisited array");
//        for(int i = 0; i < maze.r; i++){
//            for(int j = 0; j < maze.c; j++){
//                System.out.print(maze.visited[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
}
