package com.example.astar.maze;

import com.example.astar.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MazeCanvas  extends Canvas {
    private GraphicsContext gc;
    private int size;

    public MazeCanvas(int w, int h, int size) {
        super(w, h);
        this.gc = this.getGraphicsContext2D();
        this.size = (int )this.getWidth() / size;
    }

    public void draw(Node node, Color c) {
        gc.setFill(c);
        gc.fillRect(node.getPos().getX() * size, node.getPos().getY() * size, size, size);
    }


    public void clear() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawPath(ArrayList<Node> path) {
        for(Node n : path) {
            draw(n, Color.GREEN);
        }
    }

    public void drawGrid(ArrayList<Cell> maze) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(25 / (size * 4));

        for (Cell c : maze) {
            if(c.getWalls().get(0)) {
                gc.strokeLine(c.getX() * size, c.getY() * size, c.getX() * size, c.getY() * size + size);
            }
            if(c.getWalls().get(1)) {
                gc.strokeLine(c.getX() * size, c.getY() * size, c.getX() * size + size, c.getY() * size);
            }
            if(c.getWalls().get(2)) {
                gc.strokeLine(c.getX() * size + size, c.getY() * size, c.getX() * size + size, c.getY() * size + size);
            }
            if(c.getWalls().get(3)) {
                gc.strokeLine(c.getX() * size, c.getY() * size + size, c.getX() * size + size, c.getY() * size + size);
            }
        }
    }
}
