package com.view.ctrl;

import com.base.client.impl.RecordClientImpl;
import com.model.child.Record;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeViewCtrl implements Initializable {

    //--------------------------- FXML Attributes ----------------------------//
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label currentTimeText;
    @FXML
    private Label hValueLabel, tValueLabel, mValueLabel;
    @FXML
    private Label pumpStatusText, fanStatusText;
    @FXML
    private Label userTypeText, userNameText, loginTimeText, loginDateText;
    @FXML
    private LineChart<String, Double> lineChart1, lineChart2, lineChart3;
    private Stage primaryStage;
    private Timeline timer;
    private ObservableList<Record> currentLevelList;
    private XYChart.Series<String, Double> dataSeries1, dataSeries2, dataSeries3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateGraphs();
    }

    @FXML
    void autoSolveEvent(ActionEvent event) {
        System.out.println("Locked.");
    }

    //---------------------- Utility Functions ------------------------------//
    private void updateGraphs() {
        dataSeries1 = new XYChart.Series<>();
        dataSeries2 = new XYChart.Series<>();
        dataSeries3 = new XYChart.Series<>();

        lineChart1.getData().addAll(dataSeries1);
        lineChart2.getData().addAll(dataSeries2);
        lineChart3.getData().addAll(dataSeries3);

        dataSeries1.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #3B377E");
        dataSeries2.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #729b2c");
        dataSeries3.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #912b2b");

        //Update user interface every 500ms
        timer = new Timeline(new KeyFrame(Duration.millis(2000), (ActionEvent event) -> {
            try {
                currentLevelList = RecordClientImpl.getInstance().getCurrentLevels();
                dataSeries1.getData().clear();
                dataSeries2.getData().clear();
                dataSeries3.getData().clear();


                Record r = currentLevelList.get(currentLevelList.size() - 1);


                int count = 1;
                double sum = 0;

                for (Record record : currentLevelList) {
                    sum+= record.getLevel();
                    dataSeries1.getData().add(new XYChart.Data(""+record.getDustbinId(),record.getLevel()));
                    //dataSeries2.getData().add(new XYChart.Data(""+record.getDustbinId(),record.getLevel()));
                    //dataSeries3.getData().add(new XYChart.Data(""+record.getDustbinId(),record.getLevel()));
                    count++;
                }

                hValueLabel.setText("Average Level: " + sum/currentLevelList.size());
                tValueLabel.setText("Average Level: --");
                mValueLabel.setText("Average Level: --");

                for (XYChart.Data<String, Double> data : dataSeries1.getData()) {
                    data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color:#3B377E, #3B377E;");
                }

                for (XYChart.Data<String, Double> data : dataSeries2.getData()) {
                    data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color:#e2adff, #e2adff;");
                }

                for (XYChart.Data<String, Double> data : dataSeries3.getData()) {
                    data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color:#729b2c, #729b2c;");
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
