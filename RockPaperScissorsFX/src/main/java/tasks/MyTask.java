package tasks;

import controllers.Controller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class MyTask extends Task<Void> {
    // ссылка на основной контроллер игры
    private Controller controller;
    private PrintWriter out;
    private BufferedReader in;

    private int playerNumber = -1;

    public MyTask(Controller controller, Socket socket, List<Button> buttons) {
        try {
            this.controller = controller;
            if (socket != null) {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
            for (Button b : buttons) {
                b.addEventHandler(ActionEvent.ACTION, event -> sendMessage(String.valueOf(buttons.indexOf(b) + 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void call() {
        // бесконечный цикл
        while (true) {
            try {
                String response = in.readLine();
                if (response != null) {
                    System.out.println(response);
                    if (response.startsWith("you are player")) {
                        this.playerNumber = response.charAt(response.length() - 1) - '0';
                        Platform.runLater(()->this.controller.updateLabel("Make a move"));
                    } else if (response.endsWith("won!")) {
                        if (playerNumber == response.charAt(0)-'0') {
                            Platform.runLater(()->this.controller.updateLabel("You won. Make a move"));
                        } else {
                            Platform.runLater(()->this.controller.updateLabel("You lost. Make a move"));
                        }
                    } else if (response.equals("Tie!")) {
                        Platform.runLater(()->this.controller.updateLabel("Tie. Make a move"));
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void sendMessage(String message) {
        this.out.println(message);
    }
}