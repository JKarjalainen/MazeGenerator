package com.example.astar.maze;

import java.util.ArrayList;

public class Grid extends ArrayList<Cell> {
    public Grid(int size) {
        super();
        // Create grid
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                add(new Cell(i, j));
            }
        }
    }

    public void removeWall(Cell current, Cell next) {
        int x = current.getX() - next.getX();

        if(x > 0) {
            current.removeWall(0);
            next.removeWall(2);
        } else if(x < 0) {
            current.removeWall(2);
            next.removeWall(0);
        }
        int y = current.getY() - next.getY();
        if(y > 0) {
            current.removeWall(1);
            next.removeWall(3);
        } else if(y < 0) {
            current.removeWall(3);
            next.removeWall(1);
        }

    }

    public Cell get(int x, int y) {
        return stream().anyMatch(c -> c.getX() == x && c.getY() == y) ? stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().get() : null;
    }
}
