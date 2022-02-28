package com.example.astar;

import com.example.astar.maze.Cell;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class Node implements Cloneable {
    private Position pos;
    private int g = Integer.MAX_VALUE - 100;
    private int h = 0;
    private int f = 0;
    private Node parent = null;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(Position pos) {
        this.pos = pos;
    }

    public Node(Position pos, Node parent) {
        this.pos = pos;
        this.parent = parent;

    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH(Node endPoint) {
        int xDiff = (pos.getX() - endPoint.getPos().getX());
        int yDiff = (pos.getY() - endPoint.getPos().getY());
        xDiff = (int)Math.sqrt(xDiff * xDiff);
        yDiff = (int)Math.sqrt(yDiff * yDiff);

        return (xDiff + yDiff);
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public ArrayList<Node> getNeighbours(int maxSizeY, int maxSizeX)  {
        ArrayList<Node> returns = new ArrayList<>();
        returns.add(new Node(new Position(pos.getX() - 1, pos.getY())));
        returns.add(new Node(new Position(pos.getX() + 1, pos.getY())));
        returns.add(new Node(new Position(pos.getX(), pos.getY() - 1)));
        returns.add(new Node(new Position(pos.getX(), pos.getY() + 1)));
        returns.removeIf(x -> x.getPos().getX() < 0 || x.getPos().getY() < 0 || x.getPos().getX() >= maxSizeX || x.getPos().getY() >= maxSizeY);
        return returns;
    }
    public ArrayList<Node> getNeighbours(int maxSizeY, int maxSizeX, ArrayList<Cell> cells)  {
        ArrayList<Node> returns = new ArrayList<>();
        Position left = new Position(pos.getX() - 1, pos.getY());
        Position right = new Position(pos.getX() + 1, pos.getY());
        Position down = new Position(pos.getX(), pos.getY() + 1);
        Position up = new Position(pos.getX(), pos.getY() - 1);
        Cell leftCell = null;
        Cell rightCell = null;
        Cell downCell = null;
        Cell upCell = null;

        if(left.getX() >= 0)
            leftCell = cells.stream().filter(x -> x.getX() == left.getX() && x.getY() == left.getY()).findFirst().get();
        if(right.getX() < maxSizeX)
            rightCell = cells.stream().filter(x -> x.getX() == right.getX() && x.getY() == right.getY()).findFirst().get();
        if(down.getY() < maxSizeY)
            downCell = cells.stream().filter(x -> x.getX() == down.getX() && x.getY() == down.getY()).findFirst().get();
        if(up.getY() >= 0)
            upCell = cells.stream().filter(x -> x.getX() == up.getX() && x.getY() == up.getY()).findFirst().get();

        if(leftCell != null && !leftCell.getWalls().get(2))
            returns.add(new Node(left));
        if(rightCell != null && !rightCell.getWalls().get(0))
            returns.add(new Node(right));
        if(downCell != null && !downCell.getWalls().get(1))
            returns.add(new Node(down));
        if(upCell != null && !upCell.getWalls().get(3))
            returns.add(new Node(up));

        returns.removeIf(x -> x.getPos().getX() < 0 || x.getPos().getY() < 0 || x.getPos().getX() >= maxSizeX || x.getPos().getY() >= maxSizeY);
        return returns;
    }

    @Override
    public boolean equals(Object o) {
        return pos.equals(((Node) o).getPos());
    }
    @Override
    public Node clone() throws CloneNotSupportedException
    {
        return (Node)super.clone();
    }
}
