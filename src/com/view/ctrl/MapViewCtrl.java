package com.view.ctrl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MapViewCtrl implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://maps.google.com");
    }
}
