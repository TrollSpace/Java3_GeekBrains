package Lesson2.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class StartServer {
    public static void main(String[] args) {
        new Server();
    }
}

public class Server {
    private List<ClientHandler> clients;

    public Server() {
        AuthService authService = new AuthServiceImpl();
        clients = new CopyOnWriteArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;


        try {
            authService.connect();
            //Если проект большой с большим колличеством сообщений, то стартовать логирование лучше тут.
            serverSocket = new ServerSocket(8181);
            System.out.println("Server starting");


            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client connect");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.disconnect();
        }

    }

    void broadcast(String message) {
        //При небольшом колличестве - целесообразней лог открывать и записывать здесь.
        try (FileOutputStream fos = new FileOutputStream("history.txt", true)) {
            byte[] buffer = (message + "\n").getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMsg(message);
        }
    }

    void privateMsg(ClientHandler sender, String recipient, String message) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getNick().equals(recipient)) {
                clientHandler.sendMsg(message);
                break;
            } else {
                sender.sendMsg(recipient + " no chat");
            }
        }
    }

    void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
