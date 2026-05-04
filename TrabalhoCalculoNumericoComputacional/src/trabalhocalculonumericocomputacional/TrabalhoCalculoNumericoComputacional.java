/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalhocalculonumericocomputacional;

import static java.io.IO.println;
import metodos.Gauss;

/**
 *
 * @author guifr
 */
public class TrabalhoCalculoNumericoComputacional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] A = {{1, -3, 2},{-2, 8, -1},{4, -6, 5}};
        double[] B = {11, -15, 29};
        double[] X;
        
        Gauss gauss = new Gauss();
        Gauss.ResultadoGauss resultado = gauss.EliminacaoGauss(A, B);
        
        A = resultado.matrizA;
        B = resultado.matrizB;
        X = resultado.matrizX;
        
        for(int i=0; i<B.length; i++){
            System.out.printf("\n");
            for(int j=0; j<B.length; j++){
                System.out.printf("%f ", A[i][j]);
            }
           
        }
        System.out.printf("\n");
        for(int i=0;i<B.length;i++){
            System.out.printf("%f ", B[i]);
        }
        System.out.printf("\n");
        for(int i=0;i<B.length;i++){
            System.out.printf("%f ", X[i]);
        }
    }
    
}
