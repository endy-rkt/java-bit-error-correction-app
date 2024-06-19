package codageapplication;
import java.util.Arrays;

public class DecodageHamming {
    public static String print="";
    public static int[] decode(int[] encodedData) {
        int r = 0;
        int k = encodedData.length - r;
        while (Math.pow(2, r) < k + r) {
            r++;
        }
        int[] syndromes = new int[r];
        for (int i = 0; i < r; i++) {
            int pow = (int) Math.pow(2, i);
            syndromes[i] = calculateParity(encodedData, pow);
        }
        int errorPos = 0;
        for (int i = 0; i < syndromes.length; i++) {
            errorPos += syndromes[i] * (int) Math.pow(2, i);
        }
        if (errorPos != 0) {
            encodedData[errorPos - 1] = (encodedData[errorPos - 1] + 1) % 2;
            print="Erreur corrigée en position " + errorPos;
            System.out.println("Erreur corrigée en position " + errorPos);
        } else {
            System.out.println("Pas d'erreur détectée");
        }
        int[] decodedData = new int[k];
        int j = 0;
        for (int i = 0; i < encodedData.length; i++) {
            if (!isPowerOfTwo(i + 1)) {
                decodedData[j] = encodedData[i];
                j++;
            }
        }
        System.out.println("d");
        int decodedDataLength=encodedData.length-syndromes.length;
        System.out.println(decodedDataLength);
        if (decodedDataLength==5 || decodedDataLength==6 || decodedDataLength==7 || decodedDataLength>11){
            decodedData = Arrays.copyOfRange(decodedData, 0, decodedData.length - 1);
            return decodedData;
        }
        else{
            return decodedData;
        }
        //return decodedData;
        
    }
    private static boolean isPowerOfTwo(int x) {
        return (x & -x) == x;
    }
    private static int calculateParity(int[] encodedData, int pow) {
        int parity = 0;
        for (int i = pow - 1; i < encodedData.length; i += pow * 2) {
            for (int j = i; j < i + pow; j++) {
                if (j >= encodedData.length) {
                    break;
                }
                parity ^= encodedData[j];
            }
        }
        return parity;
    }
}
