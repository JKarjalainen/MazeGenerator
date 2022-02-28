package com.example.astar;

import com.example.snake.Direction;

import java.util.ArrayList;

public class AStar {
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();


    public ArrayList<Node> run(int[][] maze) {
        Node startNode = new Node(new Position(0, 0));
        Node endNode = new Node(new Position(maze.length - 1, maze.length - 1));

        openList.add(startNode);

        while(!openList.isEmpty()) {

            Node currentNode = openList.get(0);

            for(Node n : openList) {
                currentNode = currentNode.getF() < n.getF() ? currentNode : n;
            }


            openList.remove(currentNode);
            closedList.add(currentNode);

            if(maze[currentNode.getPos().getY()][currentNode.getPos().getX()] != 0) continue;
            if(currentNode.getPos().equals(endNode.getPos())) {
                ArrayList<Node> path = new ArrayList<>();
                path = getPath(currentNode);
                return path;
            }


            for(Node neighbour : currentNode.getNeighbours(maze.length, maze[0].length)) {
                int si = currentNode.getNeighbours(maze.length, maze[0].length).size();
                int tenativeG = currentNode.getG() + 1;
                if(neighbour.getG() < tenativeG) {
                    neighbour.setG(tenativeG);
                    neighbour.setF(tenativeG + 1);
                    neighbour.setParent(currentNode);
                    if(!openList.contains(neighbour)) {
                        openList.add(neighbour);
                    }
                }
            }
        }
        return null;
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

    public static ArrayList<Direction> pathToMoves(ArrayList<Node> path) {
        ArrayList<Direction> moves = new ArrayList<>();
        for(Node n : path) {
            moves.add(Direction.RIGHT);
        }
        return moves;
    }


}
