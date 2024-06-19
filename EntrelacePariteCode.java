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
public class EntrelacePariteCode {
        public static String encode(String message,int blocNumber,String parity,TextField input){
       ArrayList<String> entry= new ArrayList<>();
       ArrayList<String> result= new ArrayList<>();
       String finalResult="";
       Codage.divideToBloc(entry,message,blocNumber);
       placeBottomParity(entry,parity);
       toResult(entry,result,parity);
       for (int x=0;x<result.size();x++){
          //System.out.println(result.get(x));
          finalResult+=result.get(x);
      }
       input.setText(finalResult);
       return finalResult;
    }
  
  public static void placeBottomParity(ArrayList<String> entry,String parity){
      int b=0;
      for (int x=0;x<entry.size();x++){
          int a=Integer.valueOf(entry.get(x)).intValue();
          b+=a;
      }
      String tmp="";
      tmp=tmp.valueOf(b);
      String bottomParity="";
      for (int i=0;i<tmp.length();i++){
          if (parity.equals("Parité paire")){
              if((int)tmp.charAt(i)%2==0){
              bottomParity+='0';
          }else bottomParity+='1';
          }else{
              if((int)tmp.charAt(i)%2==0){
              bottomParity+='1';
          }else bottomParity+='0'; 
          }
      }
      entry.add(bottomParity);
  }

  public static void toResult(ArrayList<String> entry,ArrayList<String> result,String parity){
      for (int x=0;x<entry.size();x++){
          result.add(entry.get(x)+Codage.getParity(entry.get(x),parity));
      }
  }
  public static String decode(String message,int blocNumber,String parity,TextField input){
      String resultat="";
      message=verify(message,blocNumber,parity);
      ArrayList<String> entry= new ArrayList<>();
       Codage.divideToBloc(entry,message,blocNumber+1);
       for (int x=0;x<entry.size()-1;x++){
           String tmp=entry.get(x);
           resultat+=tmp.substring(0,tmp.length()-1);
      }
       input.setText(resultat);
      return resultat;
  }
  public static String verify(String message,int blocNumber,String parity){
      int len=message.length();
      ArrayList<String> entry= new ArrayList<>();
      ArrayList<String> result= new ArrayList<>();
      ArrayList<Integer> rowError= new ArrayList<>();
      ArrayList<Integer> colummnError= new ArrayList<>();
      String finalResult="";
      Codage.divideToBloc(entry,message,blocNumber+1);
      for (int x=0;x<entry.size();x++){
          String tmp=entry.get(x);
          String firstBit=tmp.substring(0, tmp.length()-1);
          String lastBit=tmp.substring(tmp.length()-1, tmp.length());
          if (!Codage.getParity(firstBit,parity).equals(lastBit)){
              if (rowError.size()==0)
                  rowError.add(x);
              else
                  AlertBox.display("Erreur", "Erreur depassant 1");
          }    
      }
      int b=0;
      for (int x=0;x<entry.size()-1;x++){
          int a=Integer.valueOf(entry.get(x)).intValue();
          b+=a;
      }
      String tmp="";
      tmp=tmp.valueOf(b);
      String bottomParity="";
      for (int i=0;i<tmp.length();i++){
           if (parity.equals("Parité paire")){
              if((int)tmp.charAt(i)%2==0){
              bottomParity+='0';
          }else bottomParity+='1';
          }else{
              if((int)tmp.charAt(i)%2==0){
              bottomParity+='1';
          }else bottomParity+='0'; 
          }
      }
      String lastVerif=entry.get(entry.size()-1);
      for(int i=0;i<bottomParity.length();i++){
          if(!(lastVerif.substring(i, i+1).equals(bottomParity.substring(i, i+1)))){
              if (colummnError.size()==0)
                  colummnError.add(i);
              else
                  AlertBox.display("Erreur", "Erreur depassant 1");
          }
      }
   
      if(rowError.size()!=0 && colummnError.size()!=0){
          AlertBox.display("Erreur", "Erreur pour bit de control en ligne "+rowError.get(0)+" et colonne "+colummnError.get(0));
          System.out.println("Erreur pour bit de control en ligne "+rowError.get(0)+" et colonne "+colummnError.get(0));
          if ((rowError.get(0)==0 && colummnError.get(0)==0)){
              message=Codage.change(message.substring(0,1))+message.substring(1,len);
          }
          else{
               int k=colummnError.get(0)+rowError.get(0)*(blocNumber+1);
           System.out.println(k);
          message=message.substring(0, k)+Codage.change(message.substring(k,k+1))+message.substring(k+1, len);
          }
          return message;
      }
      else if(rowError.size()==0 && colummnError.size()!=0){
          int k=message.length()/(blocNumber+1);
          k=(k-1)*4+colummnError.get(0);
          AlertBox.display("Erreur","Erreur pour bit de control en colonne "+colummnError.get(0));
          System.out.println("Erreur pour bit de control en colonne "+colummnError.get(0));
          message=message.substring(0, k)+Codage.change(message.substring(k,k+1))+message.substring(k+1, len);
          return message;
      }else if (rowError.size()!=0 && colummnError.size()==0){
          AlertBox.display("Erreur", "Erreur pour bit de control en ligne "+rowError.get(0));
          System.out.println("Erreur pour bit de control en ligne "+rowError.get(0));
          message=message.substring(0, rowError.get(0))+Codage.change(message.substring(rowError.get(0), rowError.get(0)+1))+message.substring(rowError.get(0)+1, len);
          return message;
      }
      else{
          return message;
      }
  }
  }

  

