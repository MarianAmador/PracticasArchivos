import java.io.*;

public class P2Mat {
    public static void main(String[] args) {
        try {
            FileInputStream amat = new FileInputStream("src/a.mat");
            FileInputStream bmat = new FileInputStream("src/b.mat");

            int filasA = amat.read();
            int filasB = bmat.read();

            int columnA = amat.read();
            int columnaB = bmat.read();

            double[][] matA = readMatrixData(amat, filasA, columnA);
            double[][] matB = readMatrixData(bmat, filasB, columnaB);

            amat.close();
            bmat.close();


            if (columnA != filasB) {
                System.out.println("La columnas A y las filas B, deben ser igual. NO son multiplicables");
                return;
            }

            double[][] matCEs = multiplyMatrices(matA, matB);

            FileOutputStream arcC = new FileOutputStream("c.mat");
            writeMatrixDimensions(arcC, matCEs.length, matCEs[0].length);
            writeMatrixData(arcC, matCEs);
            arcC.close();


            System.out.println("Matriz A:");
            printMatrix(matA);

            System.out.println("Matriz B:");
            printMatrix(matB);

            System.out.println("Resultado de la multiplicación (Matriz C):");
            printMatrix(matCEs);

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }

    private static double[][] readMatrixData(InputStream stream, int filas, int columnas) throws IOException {
        double[][] matrix = new double[filas][columnas];
        DataInputStream datos = new DataInputStream(stream);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrix[i][j] = datos.readDouble();
            }
        }
        return matrix;
    }

    private static void writeMatrixDimensions(OutputStream stream, int filas, int columnas) throws IOException {
        DataOutputStream dataStream = new DataOutputStream(stream);
        dataStream.writeInt(filas);
        dataStream.writeInt(columnas);
    }

    private static void writeMatrixData(OutputStream stream, double[][] matrix) throws IOException {
        DataOutputStream datos= new DataOutputStream(stream);

        for (double[] fila : matrix) {
            for (double value : fila) {
                datos.writeDouble(value);
            }
        }
    }

    private static double[][] multiplyMatrices(double[][] A, double[][] B) {
        int filA = A.length;
        int columnA = A[0].length;
        int columnaB = B[0].length;

        double[][] result = new double[filA][columnaB];

        for (int i = 0; i < filA; i++) {
            for (int j = 0; j < columnaB; j++) {
                for (int k = 0; k < columnA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] fila : matrix) {
            for (double vale : fila) {
                System.out.print(vale + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
