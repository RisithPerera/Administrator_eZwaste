package com.server.handler;

import com.base.client.impl.RecordClientImpl;
import com.model.child.Record;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NodeHandler implements Runnable{
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    private volatile boolean flag = true;
    // Constructor
    public NodeHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {Thread.sleep(1000);} catch (InterruptedException e){}

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            while(flag) {
                if (input.available() > 0) {
                    String dataLine = input.readLine();
                    System.out.println(dataLine);
                    if(dataLine.equals("end")){
                        System.out.println(">> Gateway Closed");
                        flag = false;
                        break;
                    }
                    String data[] = dataLine.split(",");
                    Record record = new Record();
                    record.setRecordDT(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    record.setDustbinId(Integer.parseInt(data[0]));
                    record.setLevel(Double.parseDouble(data[1]));
                    RecordClientImpl.getInstance().add(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Wrong data!!!!");
        } catch (SQLException  e) {
            e.printStackTrace();
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
