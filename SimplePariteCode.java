/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codageapplication;

import static codageapplication.CodageHamming.encode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author rakoto
 */
public class SimplePariteCode {
  public static String encode(String message,String parity,TextField input){
        String result=message+Codage.getParity(message,parity);
        input.setText(result);
        return result;
    }
    public static String decode(String message,String parity,TextField input){
        String result=verify(message,parity,input);
        input.setText(result);
        System.out.println(result);
        return result;
    }
    public static String verify(String message,String parity,TextField input){
        String result=encode(message.substring(0,message.length()-1),parity,input);
        if (message.substring(message.length()-1,message.length()).equals(result.substring(message.length()-1,message.length())))
            return message.substring(0,message.length()-1);
        else
        {AlertBox.display("Erreur", "Erreur lors de la transmission");
            return null;}
    }

}
