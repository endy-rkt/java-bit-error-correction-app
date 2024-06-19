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
public class CodageHamming{
    public static int[] encode(int[] data) {
        int r = 0;
        int k = data.length;
        while (Math.pow(2, r) < k + r + 1) {
            r++;
        }
        int[] encodedData = new int[k + r];
        int j = 1;
        for (int i = 0; i < encodedData.length; i++) {
            if (isPowerOfTwo(i + 1)) {
                encodedData[i] = 0;
            } else {
                encodedData[i] = data[j - 1];
                j++;
            }
        }
        for (int i = 0; i < r; i++) {
            int pow = (int) Math.pow(2, i);
            encodedData[pow - 1] = calculateParity(encodedData, pow);
        }
        return encodedData;
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

