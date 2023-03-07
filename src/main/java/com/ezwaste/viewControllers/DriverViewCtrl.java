
package com.ezwaste.viewControllers;

import com.ezwaste.model.child.Driver;
import com.ezwaste.base.client.impl.DriverClientImpl;
import com.ezwaste.manifest.Data;
import com.ezwaste.manifest.Message;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class DriverViewCtrl implements Initializable {

    @FXML
    private TableView<Driver> driverTable;
    
    @FXML
    private TableColumn<Driver, String> idCol,  nameCol, addressCol, contactsCol, pointsCol;

    @FXML
    private TextField searchDriverText;
   
    private ObservableList<Driver> driverList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("rrrrrrrr");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactsCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("reputation"));

        driverList = DriverClientImpl.getInstance().getAll();
        driverTable.getItems().setAll(driverList);

        searchDriverText.textProperty().addListener(
            new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldVal, Object newVal) {
                    searchDriver((String)oldVal, (String)newVal);
                }
            }
        );
        
        setTableMenu();
    }    

    private void searchDriver(String oldText, String newText) {
        if ( oldText != null && (newText.length() < oldText.length()) ) {
            driverTable.getItems().setAll(driverList);
        }
         
        String[] parts = newText.toUpperCase().split(" ");

        ObservableList<Driver> subentries = FXCollections.observableArrayList();
        for(Driver entry: driverTable.getItems()){
            boolean match = true;
            for ( String part: parts ) {
                if ( ! entry.toString().toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
            
            if ( match ) {
                subentries.add(entry);
            }
        }
        driverTable.getItems().setAll(subentries);
    }   
    
    private void setTableMenu() {
        driverTable.setRowFactory((TableView<Driver> tableView) -> {
            TableRow<Driver> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem viewMenu = new MenuItem("View Route");
            MenuItem deleteMenu = new MenuItem("Delete Driver");

            /*
            viewMenu.setOnAction((ActionEvent event) -> {                         
                try{    
                    Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
                    //CustomerOrderViewCtrl ctrl = (CustomerOrderViewCtrl) MainCtrl.getInstance().showContent(String.format(View.PATH, View.RESERVATION_VIEW));
                    //ctrl.updateTableDataByCustomer(selectedDriver);
                } catch (IOException ex) {
                    Logger.getLogger(DriverViewCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            */

            deleteMenu.setOnAction((ActionEvent event) -> {                         
                try {
                    Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
                    if (DriverClientImpl.getInstance().delete(selectedDriver.getId())) {
                        MessageBoxViewCtrl.display(Message.TITLE,String.format(Message.DELETE, Data.DRIVER));
                    }else{
                        MessageBoxViewCtrl.display(Message.TITLE,String.format(Message.UNSUCESS, Data.DRIVER));
                    }
                    driverTable.getItems().setAll(DriverClientImpl.getInstance().getAll());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });

            contextMenu.getItems().addAll(viewMenu,deleteMenu);

            row.contextMenuProperty().bind(
                Bindings.when(row.emptyProperty())
                        .then((ContextMenu)null)
                        .otherwise(contextMenu)
                );
                return row ;
            });
    }
    
}



   