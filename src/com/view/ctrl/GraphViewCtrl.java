package com.view.ctrl;

import com.base.client.impl.RecordClientImpl;
import com.model.child.Record;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GraphViewCtrl implements Initializable {
    @FXML
    private BarChart<String, Double> barChart;

    private Timeline timer;
    private ObservableList<Record> currentLevelList;
    private XYChart.Series<String, Double> dataSeries;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSeries = new XYChart.Series<>();
        barChart.getData().addAll(dataSeries);

        //Update user interface every 500ms
        timer = new Timeline(new KeyFrame(Duration.millis(2000), (ActionEvent event) -> {
            try {
                currentLevelList = RecordClientImpl.getInstance().getCurrentLevels();
                dataSeries.getData().clear();
                for(Record record : currentLevelList) {
                    dataSeries.getData().add(new XYChart.Data("Dustbin: "+record.getDustbinId(),record.getLevel()));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }
}
