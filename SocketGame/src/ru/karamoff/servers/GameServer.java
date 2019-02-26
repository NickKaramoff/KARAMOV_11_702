package ru.karamoff.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameServer {
    // список клиентов
    private List<ClientHandler> clients;

    private byte[] turns; // 1 - камень, 2 - ножницы, 3 - бумага, 0 - не сделал ход

    private static byte[][] victory = new byte[][]{
            {-1, 0, 1},
            {1, -1, 0},
            {0, 1, -1}
    };

    public GameServer() {
        // Список для работы с многопоточностью
        clients = new CopyOnWriteArrayList<>();
        turns = new byte[2];
    }

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // запускаем бесконечный цикл
        while (true) {
            try {
                // запускаем обработчик сообщений для каждого подключаемого клиента
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        // связь с одним клиентом
        private Socket clientSocket;
        // информация, поступающая от клиента
        private BufferedReader in;

        ClientHandler(Socket socket) {
            try {
                this.clientSocket = socket;

                if (clients.size() == 2) {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Sorry, ony two players allowed");
                    clientSocket.close();
                    out.close();

                } else {
                    clients.add(this);
                    System.out.println("New epic gamer " + socket.getPort());

                    tellOne(this, "you are player " + clients.indexOf(this));

                    if (clients.size() == 2) {
                        preGame();
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

        }

        public void run() {
            try {
                // получем входной поток для конкретного клиента
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("> "+inputLine);
                    if (".".equals(inputLine)) {
                        // бегаем по всем клиентам и обовещаем их о событии
                        tellAll("Epic gamer " + this.clientSocket.getPort() + " quit the game");
                        break;
                    } else {
                        if (clients.size() == 2) {
                            int idx = clients.indexOf(this);
                            turns[idx] = Byte.parseByte(inputLine);

                            if (turns[0] != 0 && turns[1] != 0) {
                                int win = getWinner();
                                if (win != -1) {
                                    tellAll(win + " won!");
                                } else {
                                    tellAll("Tie!");
                                }
                                turns = new byte[2];
                                preGame();
                            }
                        } else {
                            tellOne(this, "We don't have enough players yet");
                        }
                    }
                }
                in.close();
                clientSocket.close();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        private void tellAll(String message) throws IOException {
            for (ClientHandler client : clients) {
                tellOne(client, message);
            }
        }

        private void tellOne(ClientHandler handler, String message) throws IOException {
            PrintWriter out = new PrintWriter(handler.clientSocket.getOutputStream(), true);
            out.println(message);
        }

        private void preGame() throws IOException {
            tellAll("Make your choices!\n" +
                    "1 is for rock\n" +
                    "2 is for scissors\n" +
                    "3 is for paper");
        }

        private int getWinner() {
            return victory[turns[0]-1][turns[1]-1];
        }
    }
}