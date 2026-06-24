public class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int K = r - l + 1;
        int size = 2 * K;

        long[] baseVec = new long[size];
        for (int v2 = 0; v2 < K; v2++) {
            // v1 < v2 (Up transition)
            baseVec[v2] = v2; 
            // v1 > v2 (Down transition)
            baseVec[K + v2] = (K - 1) - v2; 
        }

        long[][] T = new long[size][size];
        
        // Construct the transition matrix T
        for (int u = 0; u < K; u++) {
            for (int v = 0; v < u; v++) {
                T[u][K + v] = 1;
            }
            for (int v = u + 1; v < K; v++) {
                T[K + u][v] = 1;
            }
        }

        // Raise transition matrix to the power of (n - 2)
        long[][] T_pow = matrixPower(T, n - 2);

        // Multiply T_pow by baseVec to get the final state frequencies
        long[] finalVec = multiplyMatrixVector(T_pow, baseVec);

        // Sum up all possibilities
        long totalWays = 0;
        for (long val : finalVec) {
            totalWays = (totalWays + val) % MOD;
        }

        return (int) totalWays;
    }

    private long[][] matrixPower(long[][] matrix, int p) {
        int n = matrix.length;
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        
        long[][] base = matrix;
        while (p > 0) {
            if ((p & 1) == 1) {
                result = multiplyMatrices(result, base);
            }
            base = multiplyMatrices(base, base);
            p >>= 1;
        }
        return result;
    }

    private long[][] multiplyMatrices(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] multiplyMatrixVector(long[][] M, long[] V) {
        int n = M.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] = (res[i] + M[i][j] * V[j]) % MOD;
            }
        }
        return res;
    }
}