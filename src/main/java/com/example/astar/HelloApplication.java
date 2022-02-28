package com.example.astar;

import com.example.astar.maze.Cell;
import com.example.astar.maze.Grid;
import com.example.astar.maze.MazeCanvas;
import com.example.astar.maze.MazeGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    public static int size = 100;
    private int canvasSize = 1000;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {



        BorderPane root = new BorderPane();
        //DrawingCanvas canvas = new DrawingCanvas(400, 400, maze);
        MazeCanvas canvas = new MazeCanvas(canvasSize, canvasSize, size);
        MazeGenerator mazeGenerator = new MazeGenerator(size);


        root.setCenter(canvas);

        Scene scene = new Scene(root, canvasSize, canvasSize);
        stage.setScene(scene);
        stage.show();

        mazeGenerator.start();
        mazeGenerator.join();

        Grid grid = mazeGenerator.getGrid();

       AStar aStar = new AStar(grid, canvas);
       aStar.start();

        /*AStart aStart = new AStart(maze, canvas);


        aStart.start();*/

    }

    public static void main(String[] args) {
        launch();
    }
}