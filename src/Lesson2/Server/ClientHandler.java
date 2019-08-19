package Lesson2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private Server server;
    private AuthService authService;

    private DataInputStream in;
    private DataOutputStream out;
    private String nick;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.authService = new AuthServiceImpl();
            new Thread(() -> {
                try {
                    autorization();
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getNick() {
        return nick;
    }


    private void read() {
        while (true) {
            try {
                String str = in.readUTF();
                if (str.startsWith("/")) {
                    if (str.equalsIgnoreCase("/end")) {
                        sendMsg("/serverclosed");
                        break;
                    }
                    if (str.startsWith("/w")) {
                        String[] tokens = str.split(" ", 3);
                        server.privateMsg(this, tokens[1], tokens[2]);
                    }
                    if (str.startsWith("/ch")) {
                        String[] tokens = str.split(" ", 2);
                        authService.changeNick(nick, tokens[1]);

                        server.broadcast(nick + " теперь пишет под именем: " + tokens[1]);
                        nick = tokens[1];
                    }

                } else {
                    server.broadcast(nick + " :" + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void autorization() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                String nick = authService.getNick(tokens[1], tokens[2]);
                if (nick != null) {
                    sendMsg("/authOK");
                    server.subscribe(this);
                    this.nick = nick;
                    break;
                } else {
                    sendMsg("wrong login/password");
                }
            }
        }
    }


    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
