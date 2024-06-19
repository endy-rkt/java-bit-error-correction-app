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
public class HammingCode  {
    public static void encode(String message,String result,TextField input){
        result="";
        int[] tableau = new int[message.length()];
            for (int i = 0; i < message.length(); i++) {
               tableau[i] = message.charAt(i) - '0';
            }
            System.out.println(message);
            int[] endcodedData = CodageHamming.encode(tableau);
            for (int i : endcodedData) {
                result+=i;
            }
            input.setText(result);
    }
    public static void decode (String message,String result,String verification,TextField input){
            result="";
            verification=DecodageHamming.print;
            int[] tableau = new int[message.length()];
            for (int i = 0; i < message.length(); i++) {
               tableau[i] = message.charAt(i) - '0';
            }
            System.out.println(message);
            int[] endcodedData = DecodageHamming.decode(tableau);
            for (int i : endcodedData) {
                result+=i;
            }
            int r = 0;
            int k = result.length();
            while (Math.pow(2, r) < k + r + 1) {
            r++;
        }
            System.out.println(k);
            System.out.println(r);
            result = result.substring(0,k-r+1);
            input.setText(result);
            
           if (!(DecodageHamming.print).equals("") ){
              AlertBox.display("Resultat", DecodageHamming.print); 
           
            }
    }
}