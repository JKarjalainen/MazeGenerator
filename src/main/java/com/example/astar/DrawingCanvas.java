package com.example.astar;

import com.example.astar.maze.Cell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawingCanvas extends Canvas {

    private GraphicsContext gc;
    private int size;

    public DrawingCanvas(int w, int h, int[][] maze) {
        super(w, h);
        this.gc = this.getGraphicsContext2D();
        double wid = getWidth();
        size = (int )this.getWidth() / maze.length;
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

    public void drawMaze(int[][] maze) {
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == 1)
                    draw(new Node(new Position(j, i), null), Color.BLACK);
            }
        }
    }
}
