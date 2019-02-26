package ru.karamoff.programs.clients;

import ru.karamoff.clients.SocketClient;

import java.util.Scanner;

public class ProgramClientStart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocketClient client = new SocketClient();
        client.startConnection("127.0.0.1", 55555);
        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }
}
