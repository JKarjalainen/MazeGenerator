package com.example.astar.maze;

import com.example.astar.DrawingCanvas;

import java.util.ArrayList;

public class MazeGenerator extends Thread {

    public MazeCanvas canvas;
    Grid grid;
    Cell current;
    ArrayList<Cell> stack;
    Cell next;

    public MazeGenerator(MazeCanvas c, int size) {
        grid = new Grid(size);
        stack = new ArrayList<>();
        current = grid.get(0);
        canvas = c;
    }

    public MazeGenerator(int size) {
        grid = new Grid(size);
        stack = new ArrayList<>();
        current = grid.get(0);
    }

    public void run() {
        int a = 0;
        do {
            current.setVisited(true);
            next = current.getNeighbour(grid);
            if(canvas != null) {
                canvas.clear();
                canvas.drawGrid(grid);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if(next != null) {
                next.setVisited(true);

                stack.add(current);

                grid.removeWall(current, next);

                current = next;
            } else if(!stack.isEmpty()) {
                current = stack.remove(0);

            }
            System.out.println(++a);
        } while(!stack.isEmpty() || next != null);

        //System.out.println("END");


        //return grid;
    }
    public Grid getGrid() {
        return grid;
    }
}
