package com.example.astar;

import com.example.astar.maze.Cell;
import com.example.astar.maze.Grid;
import com.example.astar.maze.MazeCanvas;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AStar extends Thread {
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();
    private MazeCanvas canvas;
    Grid maze;
    public AStar(Grid maze) {
        this.maze = maze;
    }

    public AStar(Grid maze, MazeCanvas canvas) {
        this.maze = maze;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Node startNode = new Node(new Position(0, 0));
        Node endNode = new Node(new Position(maze.get(maze.size() - 1).getX(), maze.get(maze.size() - 1).getX()));
        startNode.setG(0);
        openList.add(startNode);
        int a = 0;
        while(!openList.isEmpty()) {
            System.out.println(++a);
            /*try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            Node currentNode = openList.get(0);

            for(Node n : openList) {
                currentNode = currentNode.getF() < n.getF() ? currentNode : n;
            }


            openList.remove(currentNode);
            closedList.add(currentNode);

            //if(maze[currentNode.getPos().getY()][currentNode.getPos().getX()] != 0) continue;
           /* Platform.runLater(() -> {
                try {
                    final Node = new Node(currentNode.getPos());
                    canvas.draw(currentNode.clone(), Color.RED);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });*/
            //canvas.draw(currentNode, Color.RED);

            if(currentNode.getPos().equals(endNode.getPos())) {
                ArrayList<Node> path = new ArrayList<>();
                path = getPath(currentNode);

                canvas.drawPath(path);
                canvas.draw(startNode, Color.BLUE);
                canvas.draw(endNode, Color.BLUE);
                canvas.drawGrid(maze);

                return;
            }


            for(Node neighbour : currentNode.getNeighbours(HelloApplication.size, HelloApplication.size, maze)) {
                var si = currentNode.getNeighbours(HelloApplication.size, HelloApplication.size, maze);
                int h = neighbour.getH(endNode);
                int tenativeG = currentNode.getG() + 1;
                if(openList.contains(neighbour)) {
                    neighbour = openList.get(openList.indexOf(neighbour));
                }
                if(closedList.contains(neighbour)) continue;
                if(neighbour.getG() > tenativeG) {
                    neighbour.setG(tenativeG);
                    neighbour.setF(tenativeG + h);
                    neighbour.setParent(currentNode);
                    if(!openList.contains(neighbour)) {
                        openList.add(neighbour);
                    }
                }
            }
            canvas.draw(startNode, Color.BLUE);
            canvas.draw(endNode, Color.BLUE);
            canvas.drawGrid(maze);
        }
        System.out.println("Loop ended");
    }

    public ArrayList<Node> getPath(Node current) {
        ArrayList<Node> path = new ArrayList<>();
        path.add(current);
        while(current != null) {
            current = current.getParent();
            if(current == null) break;
            path.add(current);
        }
        return path;
    }

    public ArrayList<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(ArrayList<Node> openList) {
        this.openList = openList;
    }

    public ArrayList<Node> getClosedList() {
        return closedList;
    }

    public void setClosedList(ArrayList<Node> closedList) {
        this.closedList = closedList;
    }
}
