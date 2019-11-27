package com.server.handler;

import com.base.client.impl.RecordClientImpl;
import com.base.client.impl.UserClientImpl;
import com.model.child.Dustbin;
import com.model.child.Record;
import com.model.child.Region;
import com.model.child.User;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class AppHandler  implements Runnable{
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private volatile boolean flag = true;

    // Constructor
    public AppHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){

        System.out.println("App Connected");
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String request = "";
            while(flag){
                request = input.readLine();
                System.out.println(request);
                switch (request){
                    case "User[risith,abcd]":
                        checkUser(request);
                        break;
                    case "GetCurrentLevels":
                        sendCurrentLevels(request);
                        break;
                    case "end":
                        System.out.println("closed..");
                        flag = false;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

    private void sendCurrentLevels(String request) throws SQLException, ClassNotFoundException {
        ObservableList<Record> currentLevelList = RecordClientImpl.getInstance().getCurrentLevels();
        for(Record record: currentLevelList){
            System.out.println(record);
            output.println(record.getDustbinId()+","+record.getLevel());
            output.flush();
        }
        System.out.println("------------------------");
        output.println("over");
        output.flush();
    }

    public void checkUser(String request) throws SQLException, ClassNotFoundException {
        User user = UserClientImpl.getInstance().getUser("risith", "abcd");
        if(user == null){
            output.println("false");
        }else{
            output.println("true");
        }
        output.flush();
    }
}
