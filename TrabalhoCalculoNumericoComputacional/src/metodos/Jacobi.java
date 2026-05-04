/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos;

import java.util.Arrays;

/**
 *
 * @author guifr
 */
public class Jacobi{
    public int MAX_ITERATIONS;
    public double[][] A; //
    public double[] B; //
    public double[] X;
    public double[][] C; //concatenacao de A e B, matriz aumentada
    public double EPSILON;

    public Jacobi(double [][] A, double [] B, double X[], int MAX_ITERATIONS, double EPSILON){ 
        this.A = A;
        this.B = B;
        this.X = X;
        this.MAX_ITERATIONS = MAX_ITERATIONS;
        this.EPSILON = EPSILON;
    }

    public void concatenarMatrizAB(double[][] A, double[] B) {
        int linhas = A.length;
        int colunasA = A[0].length;

        C = new double[linhas][colunasA + 1];

        for (int i = 0; i < linhas; i++) {
            System.arraycopy(A[i], 0, C[i], 0, colunasA);

            C[i][colunasA] = B[i];
        }
    }
    
    public boolean transformToDominant(int r, boolean[] V, int[] R)
    {
        int n = C.length;
        if (r == C.length) {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                System.arraycopy(C[R[i]], 0, T[i], 0, n + 1);
            }

            C = T;

            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;

            for (int j = 0; j < n; j++)
               sum += Math.abs(C[i][j]);

            if (2 * Math.abs(C[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;

              if (transformToDominant(r + 1, V, R))
                    return true;

              V[i] = false;
            }
        }

        return false;
    }


    public boolean makeDominant()
    {
        concatenarMatrizAB(A, B);
        boolean[] visited = new boolean[C.length];
        int[] rows = new int[C.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }
    
    public double[] solve() {
        concatenarMatrizAB(A, B);
        int n = C.length;
        int iterations = 0;
        double[] P = X.clone();
        double erroDaRodada = 0;

        while (iterations < MAX_ITERATIONS) {
            for (int i = 0; i < n; i++) {
                double sum = C[i][n];

                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= C[i][j] * P[j];
                    }
                }
                X[i] = sum / C[i][i]; 
            }

            iterations++;

            boolean convergiu = true;
            erroDaRodada = 0;

            for (int i = 0; i < n; i++) {
                double erroAtual = Math.abs(X[i] - P[i]);
                if (erroAtual > erroDaRodada) {
                    erroDaRodada = erroAtual;
                }
                if (erroAtual > EPSILON) {
                    convergiu = false;
                }
            }

            if (convergiu) break;

            P = X.clone();
        }

        double[] resultadoFinal = new double[n + 2];
        resultadoFinal[0] = iterations;
        System.arraycopy(X, 0, resultadoFinal, 1, n);
        resultadoFinal[n + 1] = erroDaRodada;

        return resultadoFinal;
    }
}
