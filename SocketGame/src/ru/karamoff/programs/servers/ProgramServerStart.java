package ru.karamoff.programs.servers;


import ru.karamoff.servers.GameServer;

public class ProgramServerStart {
    public static void main(String[] args) {
        // создаем экземпляр сервера
        GameServer server = new GameServer();
        // размещаем сокет на порту 55555
        server.start(55555);
    }
}