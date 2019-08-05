package Lesson2.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {

    @FXML
    TextField msgField;
    @FXML
    TextArea chatArea;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passField;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    String nick;

    final String ADDRESS = "localhost";
    final int PORT = 8181;
    private boolean isAutorized;

    private void setAutorized(boolean isAutorized) {
        this.isAutorized = isAutorized;
        if (!isAutorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(true);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);

        }
    }

    void connect() {
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream((socket.getOutputStream()));
            new Thread(() -> {
                try {
                    auth();
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                }   finally {
                    close();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
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

    private void read() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.equalsIgnoreCase("/serverclosed")) {
                break;
            }
            chatArea.appendText(str + "\n");
        }
    }

    private void auth() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/authOK")) {
                setAutorized(true);
                break;
            } else {
                chatArea.appendText("str" + "\n");
            }
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(ActionEvent actionEvent) {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
