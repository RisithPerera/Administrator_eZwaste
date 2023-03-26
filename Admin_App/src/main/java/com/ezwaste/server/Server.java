package com.ezwaste.server;

import com.ezwaste.base.client.impl.RecordClientImpl;
import com.ezwaste.base.list.BufferQueue;
import com.ezwaste.base.list.ListConnection;
import com.ezwaste.model.child.Record;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Server extends Thread {

    private static boolean SERVER_RUN = true;
    private static final int GATE_PORT = 3030;
    private static final int APP_PORT = 4040;

    private ServerSocket listener;
    private Socket socket;
    private BufferQueue bufferQueue;

    @Override
    public synchronized void run() {
        int count = 0;
        String text[] = {"STOP","LOCK","UNLOCK","RESTART"};
        Random r = new Random();
        bufferQueue = ListConnection.getInstance().getBufferQueue();
        //new RecordBufferHandler().start();
        try{
            listener = new ServerSocket(GATE_PORT);
            while(SERVER_RUN){
                try{
                    socket = listener.accept();
                    socket.setKeepAlive(true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //bufferQueue.add();
                    String data[] = in.readLine().split(",");
                    System.out.println("New"+data.length);
                    try {
                        for(int i = 0; i<50;i++){
                            Record record = new Record();
                            record.setRecordDT(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                            record.setDustbinId(Integer.parseInt(data[2*i+2]));
                            record.setLevel(Double.parseDouble(data[2*i+3]));
                            System.out.println(record);
                            RecordClientImpl.getInstance().add(record);
                        }
                    } catch (SQLException e) {
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
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