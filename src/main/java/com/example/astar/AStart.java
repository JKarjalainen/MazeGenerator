package com.example.astar;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AStart extends Thread {
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();
    private DrawingCanvas canvas;
    int[][] maze;
    public AStart(int[][] maze) {
        this.maze = maze;
    }

    public AStart(int[][] maze, DrawingCanvas canvas) {
        this.maze = maze;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Node startNode = new Node(new Position(0, 0));
        Node endNode = new Node(new Position(maze.length - 1, maze.length - 1));
        startNode.setG(0);
        openList.add(startNode);

        while(!openList.isEmpty()) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Node currentNode = openList.get(0);

            for(Node n : openList) {
                currentNode = currentNode.getF() < n.getF() ? currentNode : n;
            }


            openList.remove(currentNode);
            closedList.add(currentNode);

            if(maze[currentNode.getPos().getY()][currentNode.getPos().getX()] != 0) continue;
            canvas.draw(currentNode, Color.RED);
            if(currentNode.getPos().equals(endNode.getPos())) {
                ArrayList<Node> path = new ArrayList<>();
                path = getPath(currentNode);
                canvas.drawPath(path);
                canvas.draw(startNode, Color.BLUE);
                canvas.draw(endNode, Color.BLUE);
                canvas.drawMaze(maze);
                return;
            }


            for(Node neighbour : currentNode.getNeighbours(maze.length, maze[0].length)) {
                int si = currentNode.getNeighbours(maze.length, maze[0].length).size();
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
            canvas.drawMaze(maze);
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
