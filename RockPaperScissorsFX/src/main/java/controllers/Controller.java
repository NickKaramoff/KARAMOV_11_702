package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tasks.MyTask;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    @FXML
    private Label labelInfo;

    @FXML
    private Button buttonScissors;

    @FXML
    private Button buttonRock;

    @FXML
    private Button buttonPaper;

    @FXML
    public void initialize() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 55555);

            ArrayList<Button> buttons = new ArrayList<>();
            buttons.add(buttonRock);
            buttons.add(buttonScissors);
            buttons.add(buttonPaper);

            MyTask task = new MyTask(this, clientSocket, buttons);
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateLabel(String message) {
        this.labelInfo.setText(message);
    }
}
