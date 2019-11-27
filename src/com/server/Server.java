package com.server;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server extends Thread {

    private static boolean SERVER_RUN = true;
    private static final int GATE_PORT = 3030;
    private static final int APP_PORT = 4040;

    private ServerSocket listener;
    private Socket socket;

    @Override
    public void run() {
        int count = 0;
        String text[] = {"STOP","LOCK","UNLOCK","RESTART"};
        Random r = new Random();

        try{
            listener = new ServerSocket(GATE_PORT);
            while(SERVER_RUN){
                try{
                    socket = listener.accept();
                    socket.setKeepAlive(true);

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(in.readLine());

                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    out.write(count+","+text[r.nextInt(3)]);
                    out.flush();

                    count++;
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopServer() {
        SERVER_RUN = false;
    }
}