/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ezwaste.manifest;

/**
 *
 * @author risit
 */
public interface View {

    public static String PATH = "/views/%s.fxml";
    
    public static final String LOGIN = "login";    
    public static final String MAIN = "main";
    public static final String MESSAGEBOX = "messageBox";
    
    public static final String HOME_VIEW = "homeView";
    public static final String MAP_VIEW = "mapView";
    public static final String DRIVERS_VIEW = "driverView";
    public static final String DRIVERS_ADD = "driversAdd";
    public static final String DUSTBIN_ADD = "dustbinAdd";
    public static final String GRAPH_VIEW = "graphView";
    public static final String ABOUT_VIEW = "aboutView";


    public static final String IMAGE_ICON = "/image/Logo.png";
    public static final String IMAGE_ABOUT = "/image/About.png";

}
