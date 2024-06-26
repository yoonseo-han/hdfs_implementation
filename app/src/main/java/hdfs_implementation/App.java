/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hdfs_implementation;

import java.io.*;
import java.net.*;

import main.java.hdfs_implementation.hdfs.client.HDFSClient;
import main.java.hdfs_implementation.hdfs.server.HDFSServer;

public class App {
    public static void main(String[] args) {
        new Thread(() -> {
            HDFSServer server = new HDFSServer();
            try {
                server.start(6666);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000); // Ensure server starts before client
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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