/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos;

import javax.swing.JOptionPane;

public class Gauss {

    // classe de apoio para o retorno para compilar a resposta em 1 lugar só
    //já que é um método static preciso nem instanciar
    public static class ResultadoGauss {
        public double[][] matrizA;
        public double[] matrizX;
        public double[] matrizB;

        public ResultadoGauss(double[][] a, double[] x, double[] b) {
            this.matrizA = a;
            this.matrizX = x;
            this.matrizB = b;
        }
    }

    //o método eliminação que retorna tipo ResultadoGauss para enviar de volta matrizA, matrizX, matrizB
    public ResultadoGauss EliminacaoGauss(double[][] A, double[] b) {
        int n = b.length;
        double EPSILON = 1e-15;

        // triangularização
        for (int p = 0; p < n; p++) {

            // pivoteamento parcial, linha com maior valor pra ser pivo
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }

            //troca de linha
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double t = b[p]; b[p] = b[max]; b[max] = t;

            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matriz é singular ou indefinida (divisão por zero)");
            }

            //eliminação dos elementos abaixo
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // substituicao regressiva
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        // resposta armazena matrizA, matrizB e matrizX
        return new ResultadoGauss(A, x, b);
    }
}