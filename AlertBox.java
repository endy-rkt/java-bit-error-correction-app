/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codageapplication;

/**
 *
 * @author rakoto
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author rakoto
 */
public class AlertBox  {
    public static void display(String title,String message){
        Stage window=new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title );
        window.setMinWidth(350);
        window.setMinHeight(150);
        
        Label label=new Label();
        label.setText(message);
        label.setStyle("-fx-text-fill: crimson;\n" +"-fx-font-size:10pt;\n" +"-fx-font-weight:bold;");
        
        VBox layout=new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color:yellowgreen;");
        
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
}
