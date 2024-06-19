/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codageapplication;

import java.util.ArrayList;

/**
 *
 * @author rakoto
 */
public class Codage {
    public static boolean isBinary(String message) {
    for (char c : message.toCharArray()) {
        if (c != '0' && c != '1') {
            return false;
        }
    }
    return true;
    }
    public static boolean isNumber(String message) {
    for (char c : message.toCharArray()) {
        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
            return false;
        }
    }
    return true;
    }
    public static void divideToBloc(ArrayList<String> entry,String message,int blocNumber){
      for(int i=0;i<message.length();i=i+blocNumber){
           String tmp="";
           for (int j=i;j<=i+blocNumber-1;j++){
               if(j!=message.length())
                  tmp+=message.charAt(j);
           }
           entry.add(tmp);
       }
  }
      public static String getParity(String line,String parity){
      int b=0;
      for (int x=0;x<line.length();x++){
          int a=Integer.valueOf(line.charAt(x)).intValue();
          b+=a;
      }
      if(parity.equals("Parité paire")){
        if(b%2==0)
            return "0";
        else
            return "1";
      }
      else{
        if(b%2==0)
            return "1";
        else
            return "0";
      }
  }
       public static String change(String test){
      if(test.equals("0"))
          return "1";
      else
          return "0";
  }
}
