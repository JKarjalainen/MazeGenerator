package com.example.astar.maze;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {
    private int x;
    private int y;

    private boolean visited;
    // LEFT UP RIGHT DOWN
    private ArrayList<Boolean> walls;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        walls = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            walls.add(true);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Boolean> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Boolean> walls) {
        this.walls = walls;
    }

    public void removeWall(int i) {
        walls.set(i, false);
    }

    public Cell getNeighbour(Grid maze) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        Cell top = maze.get(x, y - 1);
        Cell right = maze.get(x + 1, y);
        Cell bottom = maze.get(x, y + 1);
        Cell left = maze.get(x - 1, y);

        if(top != null && !top.isVisited()) {
            neighbours.add(top);
        }
        if(right != null && !right.isVisited()) {
            neighbours.add(right);
        }
        if(bottom != null && !bottom.isVisited()) {
            neighbours.add(bottom);
        }
        if(left != null && !left.isVisited()) {
            neighbours.add(left);
        }

        if(!neighbours.isEmpty()) {
            RandomIntGenerator r = RandomIntGenerator.getInstance();
            return neighbours.get(r.getRandomIntBetween(0, neighbours.size()));
        }

        return null;
    }
}
