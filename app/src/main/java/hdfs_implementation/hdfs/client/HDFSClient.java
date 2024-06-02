package main.java.hdfs_implementation.hdfs.client;

import java.io.*;
import java.net.*;

public class HDFSClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        HDFSClient client = new HDFSClient();
        try {
            client.startConnection("127.0.0.1", 6666);
            System.out.println(client.sendMessage("Hello Server"));
            System.out.println(client.sendMessage("exit"));
            client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}