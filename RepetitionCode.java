/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codageapplication;

import static codageapplication.CodageHamming.encode;
import java.util.ArrayList;
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
public class RepetitionCode {    
    public static String encode(String message,int repetitionNumber,TextField input){
        String finalResult="";
        ArrayList<String> result= new ArrayList<>();
        for (int i=0;i<message.length();i++){
            finalResult="";
            for (int j=0;j<repetitionNumber;j++){
                finalResult+=message.substring(i,i+1);
            }
            result.add(finalResult);
        }
        finalResult="";
        for (int x=0;x<result.size();x++){
          finalResult+=result.get(x);
        }
        System.out.println(finalResult);
        input.setText(finalResult);
        return finalResult;
    }
    public static String decode(String message,int repetitionNumber,TextField input){
        input.setText(verify(message,repetitionNumber));
        return verify(message,repetitionNumber);}
    public static String verify(String message,int repetitionNumber){
        String finalResult="";
        if(message.length()%repetitionNumber!=0){
            AlertBox.display("Erreur","Bit manquant ou en surplut" );
            System.out.println("Bit manquant ou en surplut");
        }
        else{
            ArrayList<String> entry= new ArrayList<>();
            Codage.divideToBloc(entry,message,repetitionNumber);
            for (int x=0;x<entry.size();x++){
                finalResult+=bitPortion(entry.get(x));
            }
        }
        return finalResult;
    }
    public static String bitPortion(String message){
        int zeroNb=0;
        int oneNb=0;
        for(int i=0;i<message.length();i++){
            if(message.subSequence(i, i+1).equals("0"))
                zeroNb++;
            else
                oneNb++;
        }
        if(zeroNb<oneNb)
            return "1";
        else if(zeroNb>oneNb)
            return "0";
        else{
            AlertBox.display("Erreur", "Erreur lors du transmission");
            return null;
        }
        
    }
}
