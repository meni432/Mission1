/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

/**
 *
 * @author meni
 */
public class EX1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    /**
     * @param A array of double numbers
     * @return minimus value of the array
     */
    public static double min(double[] A){
        double min = A[0];
        for (int i = 1; i < A.length; i++){
            if (A[i] < min){
                min = A[i];
            }
        }
        return min;
    }
    
    /**
     * @param A array of double numbers
     * @return maximum value of the array
     */
    public static double max(double[] A){
        double max = A[0];
        for (int i = 1; i< A.length; i++){
            if (A[i] > max){
                max = A[i];
            }
        }
        return max;
    }
    
}
