package com.server.handler;

import com.base.client.impl.RecordClientImpl;
import com.base.list.BufferQueue;
import com.base.list.ListConnection;
import com.model.child.Record;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordBufferHandler extends Thread {

    private static final int DUSTBIN = 50;

    private BufferQueue bufferQueue;

    @Override
    public synchronized void run() {
        bufferQueue = ListConnection.getInstance().getBufferQueue();
        while (true){
            try{Thread.sleep(10000);}catch (InterruptedException e){}
            try {
                while(!bufferQueue.isEmpty()){
                    String dataLine = bufferQueue.remove();
                    String data[] = dataLine.split(",");
                    for(int i = 0; i<DUSTBIN;i++){
                        Record record = new Record();
                        record.setRecordDT(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                        record.setDustbinId(Integer.parseInt(data[2*i+2]));
                        record.setLevel(Double.parseDouble(data[2*i+3]));
                        RecordClientImpl.getInstance().add(record);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
